package id.walt.vclib.model

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import com.beust.klaxon.TypeFor
import com.nimbusds.jwt.SignedJWT
import id.walt.vclib.NestedVCs
import id.walt.vclib.adapter.*
import id.walt.vclib.nestedVCsConverter
import id.walt.vclib.schema.SchemaService
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*

@TypeFor(field = "type", adapter = VCTypeAdapter::class)
abstract class VerifiableCredential {

    @SchemaService.Required
    abstract val type: List<String>

    @field:SchemaService.JsonIgnore
    @Json(ignored = true)
    var json: String? = null

    @Json(serializeNull = false)
    abstract var id: String?

    @Json(serializeNull = false)
    abstract var issuer: String?

    @Json(serializeNull = false)
    abstract var issued: String?

    @Json(serializeNull = false)
    var issuanceDate: String? = null
        get() = validFrom
        set(value) { field = validFrom }

    @Json(serializeNull = false)
    abstract var validFrom: String?

    @Json(serializeNull = false)
    abstract var expirationDate: String?

    @Json(serializeNull = false)
    abstract var proof: Proof?

    @SchemaService.JsonIgnore
    @Json(ignored = true)
    abstract var subject: String?

    @Json(serializeNull = false)
    abstract val credentialSchema: CredentialSchema?

    @field:SchemaService.JsonIgnore
    @Json(ignored = true)
    open val dateFormat = DateTimeFormatter.ISO_INSTANT

    @field:SchemaService.JsonIgnore
    @Json(ignored = true)
    var jwt: String? = null
        set(value) {
            field = value.also {
                val jwtClaimsSet = SignedJWT.parse(value).jwtClaimsSet
                id = id ?: jwtClaimsSet.jwtid
                issuer = issuer ?: jwtClaimsSet.issuer
                issued = issued ?: jwtClaimsSet.issueTime?.let { dateFormat.format(it.toInstant()) }
                validFrom = validFrom ?: jwtClaimsSet.notBeforeTime?.let { dateFormat.format(it.toInstant()) }
                expirationDate = expirationDate ?: jwtClaimsSet.expirationTime?.let { dateFormat.format(it.toInstant()) }
                subject = subject ?: jwtClaimsSet.subject
            }
        }

    @SchemaService.JsonIgnore
    @Json(ignored = true)
    val challenge
        get() = when (this.jwt) {
            null -> this.proof?.nonce
            else -> SignedJWT.parse(this.jwt).jwtClaimsSet.getStringClaim("nonce")
        }

    abstract fun newId(id: String): String
    fun newRandomId() = newId(UUID.randomUUID().toString())

    open fun setMetaData(
        id: String? = null,
        issuer: String? = null,
        subject: String? = null,
        issued: Instant? = null,
        validFrom: Instant? = null,
        expirationDate: Instant? = null
    ) {
        this.id = id ?: newRandomId()
        this.issuer = issuer
        this.subject = subject
        this.issued = issued?.let { dateFormat.format(it) }
        this.validFrom = validFrom?.let { dateFormat.format(it) }
        this.expirationDate = expirationDate?.let { dateFormat.format(it) }
    }

    fun encode(): String = jwt ?: klaxon.toJsonString(this)

    fun encodePretty(): String {
        jwt?.let {
            return jwt!!
        }
        klaxon.toJsonString(this).apply {
            if (startsWith("[")) {
                return Klaxon().parseJsonArray(reader()).toJsonString(true)
            }
            return Klaxon().parseJsonObject(reader()).toJsonString(true)
        }
    }

    fun toMap(): Map<String, Any> = klaxon.parse(encode())!!

    companion object {
        val klaxon = Klaxon().fieldConverter(NestedVCs::class, nestedVCsConverter).fieldConverter(ListOrSingleValue::class, ListOrSingleValueConverter(false)).fieldConverter(ListOrReadSingleValue::class, ListOrSingleValueConverter(true))
        val JWT_PATTERN = "(^[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*\$)"
        val JWT_VC_CLAIM = "vc"
        val JWT_VP_CLAIM = "vp"

        fun isJWT(data: String): Boolean {
            return Regex(JWT_PATTERN).matches(data)
        }

        private fun vcJsonFromJwt(jwt: String): String {
            val claims = SignedJWT.parse(jwt).jwtClaimsSet.claims
            return when (claims.keys.contains(JWT_VP_CLAIM)) {
                true -> claims[JWT_VP_CLAIM].toString()
                false -> claims[JWT_VC_CLAIM].toString()
            }
        }

        fun fromString(data: String): VerifiableCredential {
            return if (!isJWT(data)) klaxon.parse<VerifiableCredential>(data)!!.also {
                it.json = data
            }
            else klaxon.parse<VerifiableCredential>(vcJsonFromJwt(data))!!.also {
                it.jwt = data
                it.json = klaxon.toJsonString(it)
            }
        }
    }
}

abstract class CredentialSubject {
    @Json(serializeNull = false)
    abstract var id: String?
}

abstract class AbstractVerifiableCredential<SUBJ : CredentialSubject>(@field:SchemaService.Required override val type: List<String>) :
    VerifiableCredential() {

    @Json(serializeNull = false)
    abstract var credentialSubject: SUBJ?

    @SchemaService.JsonIgnore
    @Json(ignored = true)
    override var subject: String?
        get() = credentialSubject?.id
        set(value) {
            credentialSubject?.also { it.id = value }
        }

    override fun newId(id: String) = "${type.last()}#${id}"
}

fun String.toCredential() = VerifiableCredential.fromString(this)

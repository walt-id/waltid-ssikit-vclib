package id.walt.vclib.model

import com.beust.klaxon.Json
import com.beust.klaxon.TypeFor
import com.nimbusds.jwt.SignedJWT
import id.walt.vclib.adapter.VCTypeAdapter
import id.walt.vclib.schema.SchemaService
import java.text.SimpleDateFormat
import java.util.*


@TypeFor(field = "type", adapter = VCTypeAdapter::class)
abstract class VerifiableCredential(@field:SchemaService.Required val type: List<String>) {
    @field:SchemaService.JsonIgnore
    @Json(ignored = true)
    var json: String? = null

    @field:SchemaService.JsonIgnore
    @Json(ignored = true)
    open var jwt: String? = null // the original JWT token, if credential was given in JWT format

    @Json(serializeNull = false)
    abstract var id: String?
}


@TypeFor(field = "type", adapter = VCTypeAdapter::class)
abstract class VerifiableCredential2(@field:SchemaService.Required val type: List<String>) {
    @field:SchemaService.JsonIgnore
    @Json(ignored = true)
    var json: String? = null

    @field:SchemaService.JsonIgnore
    @Json(ignored = true)
    open var jwt: String? = null // the original JWT token, if credential was given in JWT format

    @Json(serializeNull = false)
    abstract var id: String?

    @Json(ignored = true)
    abstract var internalIssuer: String?

    @Json(ignored = true)
    abstract var internalSubject: String?
}

@TypeFor(field = "type", adapter = VCTypeAdapter::class)
abstract class VerifiableCredential3<SBJ : CredentialSubject>(@field:SchemaService.Required val type: List<String>) {
    @field:SchemaService.JsonIgnore
    @Json(ignored = true)
    var json: String? = null

    @Json(serializeNull = false)
    abstract var id: String?

    @Json(serializeNull = false)
    abstract var issuer: String?

    @Json(serializeNull = false)
    abstract var issuanceDate: String?

    @Json(serializeNull = false)
    abstract var validFrom: String?

    @Json(serializeNull = false)
    abstract var expirationDate: String?

    @Json(serializeNull = false)
    abstract var credentialSchema: CredentialSchema?

    @Json(serializeNull = false)
    abstract var proof: Proof?

    @Json(serializeNull = false)
    abstract var credentialSubject: SBJ?

    open val dateFormat = SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss'Z'").also { it.timeZone = TimeZone.getTimeZone("UTC") }

    @field:SchemaService.JsonIgnore
    @Json(ignored = true)
    var jwt: String? = null
        set(value) {
            field = value.also {
                val jwtClaimsSet = SignedJWT.parse(value).jwtClaimsSet
                id = id ?: jwtClaimsSet.jwtid
                issuer = issuer ?: jwtClaimsSet.issuer
                issuanceDate = issuanceDate ?: jwtClaimsSet.issueTime?.let { dateFormat.format(it) }
                validFrom = validFrom ?: jwtClaimsSet.notBeforeTime?.let { dateFormat.format(it) }
                expirationDate = expirationDate ?: jwtClaimsSet.expirationTime?.let { dateFormat.format(it) }
                credentialSubject?.also { it.id = it.id ?: jwtClaimsSet.subject }
            }
        }

    @Json(serializeNull = false)
    val challenge = when (this.jwt) {
        null -> this.proof?.nonce
        else -> SignedJWT.parse(this.jwt).jwtClaimsSet.getStringClaim("nonce")
    }
}

abstract class CredentialSubject() {
    @Json(serializeNull = false)
    abstract var id: String?
}

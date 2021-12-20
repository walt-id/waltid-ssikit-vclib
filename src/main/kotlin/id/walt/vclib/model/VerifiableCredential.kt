package id.walt.vclib.model

import com.beust.klaxon.Json
import com.beust.klaxon.TypeFor
import id.walt.vclib.adapter.VCTypeAdapter
import id.walt.vclib.schema.SchemaService
import java.text.SimpleDateFormat
import java.util.*

@TypeFor(field = "type", adapter = VCTypeAdapter::class)
abstract class VerifiableCredential() {

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
    abstract var issuanceDate: String?

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
    open val dateFormat = SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss'Z'").also { it.timeZone = TimeZone.getTimeZone("UTC") }

    @field:SchemaService.JsonIgnore
    @Json(ignored = true)
    var jwt: String? = null
//        set(value) {
//            field = value.also {
//                val jwtClaimsSet = SignedJWT.parse(value).jwtClaimsSet
//                id = id ?: jwtClaimsSet.jwtid
//                issuer = issuer ?: jwtClaimsSet.issuer
//                issuanceDate = issuanceDate ?: jwtClaimsSet.issueTime?.let { dateFormat.format(it) }
//                validFrom = validFrom ?: jwtClaimsSet.notBeforeTime?.let { dateFormat.format(it) }
//                expirationDate = expirationDate ?: jwtClaimsSet.expirationTime?.let { dateFormat.format(it) }
//                subject = subject ?: jwtClaimsSet.subject
//            }
//        }

    @field:SchemaService.JsonIgnore
    @Json(ignored = true)
    val challenge = this.proof?.nonce
//    = when (this.jwt) {
//        null -> this.proof?.nonce
//        else -> SignedJWT.parse(this.jwt).jwtClaimsSet.getStringClaim("nonce")
//    }
}

abstract class CredentialSubject() {
    @Json(serializeNull = false)
    abstract var id: String?
}

abstract class AbstractVerifiableCredential<SUBJ : CredentialSubject>(@field:SchemaService.Required override val type: List<String>) : VerifiableCredential() {

    @Json(serializeNull = false)
    abstract var credentialSubject: SUBJ?

    @SchemaService.JsonIgnore
    @Json(ignored = true)
    override var subject: String?
        get() = credentialSubject?.id
        set(value) {
            credentialSubject?.also { it.id = value }
        }
}

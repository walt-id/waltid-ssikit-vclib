package id.walt.vclib.credentials.velocity


import com.beust.klaxon.Json
import com.fasterxml.jackson.annotation.JsonProperty
import id.walt.vclib.model.AbstractVerifiableCredential
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.model.CredentialSubject
import id.walt.vclib.model.Proof
import id.walt.vclib.registry.VerifiableCredentialMetadata

data class Email(
    @JsonProperty("@context")
    var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),

    @Json(serializeNull = false) var credentialStatus: CredentialStatus? = null,
    @Json(serializeNull = false) override var credentialSubject: EmailCredentialSubject? = null,

    @Json(serializeNull = false) override var id: String? = null,
    @Json(serializeNull = false) override var issuer: String? = null,
    @Json(serializeNull = false) override var issued: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override val credentialSchema: CredentialSchema? = null,

    @Json(serializeNull = false) override var proof: Proof? = null,

)  : AbstractVerifiableCredential<Email.EmailCredentialSubject>(Email.type) {

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "Email"),
        template = {
            Email(
                credentialStatus = CredentialStatus(
                    id = "https://credentialstatus.velocitycareerlabs.io",
                    type = "VelocityRevocationRegistry"
                ),
                credentialSubject = EmailCredentialSubject(
                    email = "adam.smith@example.com"
                )
            )
        }
    )

    data class CredentialStatus(
        var id: String, // https://credentialstatus.velocitycareerlabs.io
        var type: String // VelocityRevocationRegistry
    )

    data class EmailCredentialSubject(
        var email: String, // adam.smith@example.com
        @Json(serializeNull = false) override var id: String? = null
    ) : CredentialSubject()
}

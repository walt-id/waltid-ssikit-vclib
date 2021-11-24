package id.walt.vclib.credentials

import com.beust.klaxon.Json
import id.walt.vclib.model.Proof
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class PermanentResidentCard(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf(
        "https://www.w3.org/2018/credentials/v1",
        "https://w3id.org/citizenship/v1"
    ),
    @Json(serializeNull = false) var credentialSubject: CredentialSubject2? = null,
    @Json(serializeNull = false) var issuer: String? = null,
    @Json(serializeNull = false) var proof: Proof? = null,
) : VerifiableCredential(type) {
    data class CredentialSubject2(
        @Json(serializeNull = false) var id: String? = null, // did:ebsi:00000004321
        @Json(serializeNull = false) var type: List<String>? = null,
        @Json(serializeNull = false) var givenName: String? = null, // JOHN
        @Json(serializeNull = false) var birthDate: String? = null // 1958-08-17
    )

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "PermanentResidentCard"),
        template = {
            PermanentResidentCard(
                credentialSubject = CredentialSubject2(
                    id = "did:example:123",
                    type = listOf(
                        "PermanentResident",
                        "Person"
                    ),
                    givenName = "JOHN",
                    birthDate = "1958-08-17"
                ),
                issuer = "did:example:456"
            )
        }
    )

    @Json(serializeNull = false)
    override var id: String? = null
}

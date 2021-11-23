package id.walt.vclib.credentials

import com.beust.klaxon.Json
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.model.CredentialStatus
import id.walt.vclib.model.Proof
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class VerifiableAuthorization(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf(
        "https://www.w3.org/2018/credentials/v1"
    ),
    override var id: String?,
    var issuer: String,
    @Json(serializeNull = false) var issuanceDate: String? = null,
    @Json(serializeNull = false) var validFrom: String? = null,
    @Json(serializeNull = false) var expirationDate: String? = null,
    var credentialSubject: CredentialSubject1,
    @Json(serializeNull = false) var credentialStatus: CredentialStatus? = null,
    @Json(serializeNull = false) var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) var proof: Proof? = null,
) : VerifiableCredential(type) {
    data class CredentialSubject1(
        var id: String, // did:ebsi:00000004321
        @Json(serializeNull = false) var naturalPerson: NaturalPerson? = null,
    ) {
        data class NaturalPerson(
            var did: String // did:example:00001111
        )
    }

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableAuthorization"),
        aliases = arrayOf(listOf("VerifiableCredential", "VerifiableAuthorisation")),
        template = {
            VerifiableAuthorization(
                id = "did:ebsi-eth:00000001/credentials/1872",
                issuer = "did:ebsi:000001234",
                issuanceDate = "2020-08-24T14:13:44Z",
                credentialSubject = CredentialSubject1(
                    "did:ebsi:00000004321",
                    CredentialSubject1.NaturalPerson("did:example:00001111")
                )
            )
        }
    )
}

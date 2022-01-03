package id.walt.vclib.credentials

import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class VerifiableAuthorization(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf(
        "https://www.w3.org/2018/credentials/v1"
    ),
    override var id: String?,
    override var issuer: String?,
    @Json(serializeNull = false) override var issuanceDate: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    override var credentialSubject: VerifiableAuthorizationSubject?,
    @Json(serializeNull = false) var credentialStatus: CredentialStatus? = null,
    @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) override var proof: Proof? = null,
) : AbstractVerifiableCredential<VerifiableAuthorization.VerifiableAuthorizationSubject>(type) {
    data class VerifiableAuthorizationSubject(
        override var id: String?, // did:ebsi:00000004321
        @Json(serializeNull = false) var naturalPerson: NaturalPerson? = null,
    ) : CredentialSubject() {
        data class NaturalPerson(
            var did: String // did:example:00001111
        )
    }

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableAuthorization"),
        //aliases = arrayOf(listOf("VerifiableCredential", "VerifiableAuthorisation")),
        template = {
            VerifiableAuthorization(
                id = "did:ebsi-eth:00000001/credentials/1872",
                issuer = "did:ebsi:000001234",
                issuanceDate = "2020-08-24T14:13:44Z",
                credentialSubject = VerifiableAuthorizationSubject(
                    "did:ebsi:00000004321",
                    VerifiableAuthorizationSubject.NaturalPerson("did:example:00001111")
                )
            )
        }
    )
}

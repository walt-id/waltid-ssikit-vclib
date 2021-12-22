package id.walt.vclib.credentials

import com.beust.klaxon.Json
import id.walt.vclib.NestedVCs
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService
import id.walt.vclib.schema.SchemaService.JsonIgnore
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class VerifiablePresentation(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) override var id: String? = null,
    @Json(serializeNull = false) var holder: String? = null,
    @NestedVCs @field:JsonIgnore var verifiableCredential: List<VerifiableCredential>,
    @Json(serializeNull = false) override var proof: Proof? = null,
    @Json(serializeNull = false) override var issuanceDate: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null

) : VerifiableCredential() {
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiablePresentation"),
        template = {
            VerifiablePresentation(
                id = "id",
                holder = "did:ebsi:00000004321",
                verifiableCredential = listOf(
                    VerifiableAuthorization(
                        id = "did:ebsi-eth:00000001/credentials/1872",
                        issuer = "did:ebsi:000001234",
                        issuanceDate = "2020-08-24T14:13:44Z",
                        credentialSubject = VerifiableAuthorization.VerifiableAuthorizationSubject(
                            "did:ebsi:00000004321",
                            VerifiableAuthorization.VerifiableAuthorizationSubject.NaturalPerson("did:example:00001111")
                        ),
                        proof = Proof(
                            "EcdsaSecp256k1Signature2019",
                            "2020-08-24T14:13:44Z",
                            "assertionMethod",
                            "did:ebsi-eth:000001234#key-1",
                            "eyJhbGciOiJSUzI1NiIsImI2NCI6ZmFsc2UsImNyaXQiOlsiYjY0Il19."
                        )
                    ), PermanentResidentCard(
                        credentialSubject = PermanentResidentCard.PermanentResidentCardSubject(
                            id = "did:example:123",
                            type = listOf(
                                "PermanentResident",
                                "Person"
                            ),
                            givenName = "JOHN",
                            birthDate = "1958-08-17"
                        ),
                        issuer = "did:example:456",
                        proof = Proof(
                            "Ed25519Signature2018",
                            "2020-04-22T10:37:22Z",
                            "assertionMethod",
                            "did:example:456#key-1",
                            "eyJjcml0IjpbImI2NCJdLCJiNjQiOmZhbHNlLCJhbGciOiJFZERTQSJ9..BhWew0x-txcroGjgdtK-yBCqoetg9DD9SgV4245TmXJi-PmqFzux6Cwaph0r-mbqzlE17yLebjfqbRT275U1AA"
                        )
                    )
                )
            )
        }
    )

    override val type: List<String>
        get() = VerifiablePresentation.type

    @SchemaService.JsonIgnore
    @Json(ignored = true)
    override var issuer: String?
        get() = holder
        set(value) {
            holder = value
        }

    @SchemaService.JsonIgnore
    @Json(ignored = true)
    override var subject: String?
        get() = holder
        set(value) {
            holder = value
        }

    @SchemaService.JsonIgnore
    @Json(ignored = true)
    override val credentialSchema: CredentialSchema?
        get() = null

    override fun newId(id: String) = "VerifiablePresentation#${id}"
}

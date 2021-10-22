package id.walt.vclib.vclist

import com.beust.klaxon.Json
import com.nimbusds.jwt.SignedJWT
import id.walt.vclib.NestedVCs
import id.walt.vclib.model.Proof
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.JsonIgnore

data class VerifiablePresentation(
    @Json(name = "@context")
    var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) override var id: String? = null,
    @Json(serializeNull = false) var holder: String? = null,
    @NestedVCs var verifiableCredential: List<VerifiableCredential>,
    @Json(serializeNull = false) var proof: Proof? = null
) : VerifiableCredential(type) {
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
                        credentialSubject = VerifiableAuthorization.CredentialSubject1(
                            "did:ebsi:00000004321",
                            VerifiableAuthorization.CredentialSubject1.NaturalPerson("did:example:00001111")
                        ),
                        proof = Proof(
                            "EcdsaSecp256k1Signature2019",
                            "2020-08-24T14:13:44Z",
                            "assertionMethod",
                            "did:ebsi-eth:000001234#key-1",
                            "eyJhbGciOiJSUzI1NiIsImI2NCI6ZmFsc2UsImNyaXQiOlsiYjY0Il19."
                        )
                    ), PermanentResidentCard(
                        credentialSubject = PermanentResidentCard.CredentialSubject2(
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

    @field:JsonIgnore
    @Json(ignored = true)
    override var jwt: String? = null
        set(value) {
            field = value.also {
                val jwtClaimsSet = SignedJWT.parse(value).jwtClaimsSet
                id = id ?: jwtClaimsSet.jwtid
                holder = holder ?: jwtClaimsSet.issuer
            }
        }
}

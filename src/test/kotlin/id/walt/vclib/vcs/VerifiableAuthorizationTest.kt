package id.walt.vclib.vcs

import id.walt.vclib.Helpers.encode
import id.walt.vclib.VcLibManager
import id.walt.vclib.model.Proof
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.vclist.VerifiableAuthorization
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class VerifiableAuthorizationTest : StringSpec({
    "testing VerifiableAuthorization" {
        println("Generating VerifiableAuthorization:")
        val data1: VerifiableCredential = VerifiableAuthorization(
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
        )

        val vaJson = data1.encode()
        println(vaJson)

        println("Parsing VerifiableAuthorization JSON...")
        val vc = VcLibManager.getVerifiableCredential(vaJson)

        println("VerifiableAuthorization JSON is VerifiableAuthorization class:")

        (vc is VerifiableAuthorization) shouldBe true
    }
})

package id.walt.vclib.credentials

import id.walt.vclib.model.Proof
import id.walt.vclib.model.VerifiableCredential
import io.kotest.assertions.json.shouldNotContainJsonKey
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

private val data1 = VerifiableAuthorization(
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
)
private val data2 = PermanentResidentCard(
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

class VerifiablePresentationTest : StringSpec({
    "testing VerifiablePresentation" {
        println("Generating VerifiableAuthorization:")
        val vaJson = data1.encode()
        println(vaJson)

        println("Parsing VerifiableAuthorization JSON...")
        val vc = VerifiableCredential.fromString(vaJson)

        println("VerifiableAuthorization JSON is VerifiableAuthorization class:")

        (vc is VerifiableAuthorization) shouldBe true

        println("-----")

        println("Generating PermanentResidentCard...")
        val prcJson = data2.encode()
        println(prcJson)

        println("Generating VerifiablePresentation:")
        val vp = VerifiablePresentation(
            id = "id",
            //type = listOf("VerifiablePresentation"),
            verifiableCredential = listOf(data1, data2)
        )
        val vpJson = vp.encode()
        println(vpJson)



        println("Parsing VerifiablePresentation...")
        val myVp = VerifiableCredential.fromString(vpJson)

        println("VerifiablePresentation:")
        println(myVp)

        (myVp is VerifiablePresentation) shouldBe true

        (myVp as VerifiablePresentation).verifiableCredential.size shouldBe 2
    }

    "VerifiablePresentation null id and holder are not serialized" {
        val vp = VerifiablePresentation(verifiableCredential = listOf(data1, data2)).encode()
        vp shouldNotContainJsonKey "id"
        vp shouldNotContainJsonKey "holder"
    }
})

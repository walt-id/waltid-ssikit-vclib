package id.walt.vclib.vcs

import id.walt.vclib.Helpers.encode
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.model.CredentialStatus
import id.walt.vclib.model.Proof
import id.walt.vclib.vclist.VerifiableID
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.StringSpec
import java.io.File

class SerializeVerifiableID : StringSpec({
    "serialize verifiableID" {
        val verifiableID = VerifiableID(
            id = "identity#verifiableID#51e42fda-cb0a-4333-b6a6-35cb147e1a88",
            issuer = "did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9",
            issuanceDate = "2020-11-03T00:00:00Z",
            validFrom = "2020-11-03T00:00:00Z",
            credentialSubject = VerifiableID.CredentialSubject(
                id = "did:ebsi:22AhtW7XMssv7es4YcQTdV2MCM3c8b1VsiBfi5weHsjcCY9o",
                familyName = "DOE",
                firstName = "Jane",
                dateOfBirth = "1993-04-08",
                personalIdentifier = "0904008084H",
                nameAndFamilyNameAtBirth = "Jane DOE",
                placeOfBirth = "LILLE, FRANCE",
                currentAddress = "1 Boulevard de la Liberté, 59800 Lille",
                gender = "FEMALE"
            ),
            credentialStatus = CredentialStatus(
                id = "https://essif.europa.eu/status/identity#verifiableID#51e42fda-cb0a-4333-b6a6-35cb147e1a88",
                type = "CredentialsStatusList2020"
            ),
            credentialSchema = CredentialSchema(
                id = "https://api.preprod.ebsi.eu/trusted-schemas-registry/v1/schemas/0x312e332e362e312e342e312e3934382e342e3136392e322e312e322e3435",
                type = "JsonSchemaValidator2018"
            ),
            evidence = VerifiableID.Evidence(
                id = "https://essif.europa.eu/tsr-vid/evidence/f2aeec97-fc0d-42bf-8ca7-0548192d4231",
                type = listOf("DocumentVerification"),
                verifier = "did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9",
                evidenceDocument = listOf("Passport"),
                subjectPresence = "Physical",
                documentPresence = listOf("Physical")
            ),
            proof = Proof(
                type = "EcdsaSecp256k1Signature2019",
                created = "2021-07-26T18:40:49Z",
                proofPurpose = "assertionMethod",
                verificationMethod = "did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9#z6Mkfvb1T28UuRTY7MZR19mHgXXxQGPfWoC1kCwH6HMW8tkE",
                jws = "eyJiNjQiOmZhbHNlLCJjcml0IjpbImI2NCJdLCJhbGciOiJFUzI1NksifQ..L6Ky4HioEJvkR1oJt4-U7NELnTxB_NS9DwQq4bIUXagCQs53Co_Jl7lyKGPLZPC26O40oOvQSLc6aUQiUNPFSg"
            )
        )
        val verifiableIDJson = verifiableID.encode()

        File("src/test/resources/serialized/VerifiableID.json").readText() shouldEqualJson verifiableIDJson
    }
})

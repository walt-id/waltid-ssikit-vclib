package id.walt.vclib.vcs

import id.walt.vclib.Helpers.encode
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.model.CredentialStatus
import id.walt.vclib.model.Proof
import id.walt.vclib.vclist.Europass
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.StringSpec
import java.io.File

class SerializeEuropass : StringSpec({
    "serialize europass" {
        val europass = Europass(
            id = "education#higherEducation#51e42fda-cb0a-4333-b6a6-35cb147e1a88",
            issuer = "did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9",
            issuanceDate = "2020-11-03T00:00:00Z",
            validFrom = "2020-11-03T00:00:00Z",
            credentialSubject = Europass.CredentialSubject(
                id = "did:ebsi:22AhtW7XMssv7es4YcQTdV2MCM3c8b1VsiBfi5weHsjcCY9o",
                identifier = "0904008084H",
                givenNames = "Jane",
                familyName = "DOE",
                dateOfBirth = "1993-04-08",
                gradingScheme = Europass.CredentialSubject.GradingScheme(
                    id = "https://blockchain.univ-lille.fr/ontology#GradingScheme",
                    title = "Lower Second-Class Honours"
                ),
                learningAchievement = Europass.CredentialSubject.LearningAchievement(
                    id = "https://blockchain.univ-lille.fr/ontology#LearningAchievment",
                    title = "MASTERS LAW, ECONOMICS AND MANAGEMENT",
                    description = "MARKETING AND SALES",
                    additionalNote = listOf(
                        "DISTRIBUTION MANAGEMENT"
                    )
                ),
                awardingOpportunity = Europass.CredentialSubject.AwardingOpportunity(
                    id = "https://blockchain.univ-lille.fr/ontology#AwardingOpportunity",
                    identifier = "https://certificate-demo.bcdiploma.com/check/87ED2F2270E6C41456E94B86B9D9115B4E35BCCAD200A49B846592C14F79C86BV1Fnbllta0NZTnJkR3lDWlRmTDlSRUJEVFZISmNmYzJhUU5sZUJ5Z2FJSHpWbmZZ",
                    awardingBody = Europass.CredentialSubject.AwardingOpportunity.AwardingBody(
                        id = "did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9",
                        eidasLegalIdentifier = "Unknown",
                        registration = "0597065J",
                        preferredName = "Université de Lille",
                        homepage = "https://www.univ-lille.fr/"
                    ),
                    location = "FRANCE",
                    startedAtTime = "Unknown",
                    endedAtTime = "2020-11-03T00:00:00Z"
                ),
                learningSpecification = Europass.CredentialSubject.LearningSpecification(
                    id = "https://blockchain.univ-lille.fr/ontology#LearningSpecification",
                    iscedfCode = listOf(
                        "7"
                    ),
                    eCTSCreditPoints = 120,
                    eQFLevel = 7,
                    nqfLevel = listOf(
                        "7"
                    )
                )
            ),
            credentialStatus = CredentialStatus(
                id = "https://essif.europa.eu/status/education#higherEducation#51e42fda-cb0a-4333-b6a6-35cb147e1a88",
                type = "CredentialsStatusList2020"
            ),
            credentialSchema = CredentialSchema(
                id = "https://api.preprod.ebsi.eu/trusted-schemas-registry/v1/schemas/0x312e332e362e312e342e312e3831342e372e3138392e322e332e332e3132",
                type = "JsonSchemaValidator2018"
            ),
            proof =
            Proof(
                type = "EcdsaSecp256k1Signature2019",
                created = "2021-07-26T18:40:49Z",
                proofPurpose = "assertionMethod",
                verificationMethod = "did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9#z6Mkfvb1T28UuRTY7MZR19mHgXXxQGPfWoC1kCwH6HMW8tkE",
                jws = "eyJiNjQiOmZhbHNlLCJjcml0IjpbImI2NCJdLCJhbGciOiJFUzI1NksifQ..L6Ky4HioEJvkR1oJt4-U7NELnTxB_NS9DwQq4bIUXagCQs53Co_Jl7lyKGPLZPC26O40oOvQSLc6aUQiUNPFSg"
            )
        )
        val europassJson = europass.encode()

        File("src/test/resources/serialized/Europass.json").readText() shouldEqualJson europassJson
    }
})

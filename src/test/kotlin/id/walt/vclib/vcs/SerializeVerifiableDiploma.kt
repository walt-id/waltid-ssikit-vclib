package id.walt.vclib.vcs

import id.walt.vclib.Helpers.encode
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.model.CredentialStatus
import id.walt.vclib.model.Proof
import id.walt.vclib.vclist.Europass
import id.walt.vclib.vclist.VerifiableDiploma
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.StringSpec
import java.io.File

class SerializeVerifiableDiploma : StringSpec({
    "serialize verifiable diploma" {
        val verifiableDiploma = VerifiableDiploma(
            credentialSubject = VerifiableDiploma.CredentialSubject(
                identifier = "0904008084H",
                givenNames = "Jane",
                familyName = "DOE",
                dateOfBirth = "1993-04-08T00:00:00Z",
                gradingScheme = VerifiableDiploma.CredentialSubject.GradingScheme(
                    id = "https://blockchain.univ-lille.fr/ontology#GradingScheme"
                ),
                learningAchievement = VerifiableDiploma.CredentialSubject.LearningAchievement(
                    id = "https://blockchain.univ-lille.fr/ontology#LearningAchievment",
                    title = "MASTERS LAW, ECONOMICS AND MANAGEMENT"
                ),
                awardingOpportunity = VerifiableDiploma.CredentialSubject.AwardingOpportunity(
                    id = "https://blockchain.univ-lille.fr/ontology#AwardingOpportunity",
                    identifier = "https://certificate-demo.bcdiploma.com/check/87ED2F2270E6C41456E94B86B9D9115B4E35BCCAD200A49B846592C14F79C86BV1Fnbllta0NZTnJkR3lDWlRmTDlSRUJEVFZISmNmYzJhUU5sZUJ5Z2FJSHpWbmZZ",
                    awardingBody = VerifiableDiploma.CredentialSubject.AwardingOpportunity.AwardingBody(
                        id = "did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9",
                        eidasLegalIdentifier = "Unknown",
                        registration = "0597065J",
                        preferredName = "Université de Lille"
                    )
                ),
                learningSpecification = VerifiableDiploma.CredentialSubject.LearningSpecification(
                    id = "https://blockchain.univ-lille.fr/ontology#LearningSpecification",
                    iscedfCode = listOf(
                        "7"
                    ),
                    nqfLevel = listOf(
                        "7"
                    )
                )
            ),
            credentialSchema = CredentialSchema(
                id = "https://api.preprod.ebsi.eu/trusted-schemas-registry/v1/schemas/0x312e332e362e312e342e312e3831342e372e3138392e322e332e332e3132",
                type = "JsonSchemaValidator2018"
            )
        )
        val verifiableDiplomaJson = verifiableDiploma.encode()

        verifiableDiplomaJson shouldEqualJson File("src/test/resources/serialized/VerifiableDiploma.json").readText()
    }
})

package id.walt.vclib.vclist


import com.beust.klaxon.Json
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.model.CredentialStatus
import id.walt.vclib.model.Proof
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata

data class Europass(
    @Json(name = "@context") var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) var id: String? = null, // education#higherEducation#51e42fda-cb0a-4333-b6a6-35cb147e1a88
    @Json(serializeNull = false) var issuer: String? = null, // did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9
    @Json(serializeNull = false) var issuanceDate: String? = null, // 2020-11-03T00:00:00Z
    @Json(serializeNull = false) var validFrom: String? = null, // 2020-11-03T00:00:00Z
    @Json(serializeNull = false) var expirationDate: String? = null,
    @Json(serializeNull = false) var credentialSubject: CredentialSubject? = null,
    @Json(serializeNull = false) var credentialStatus: CredentialStatus? = null,
    @Json(serializeNull = false) var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) var evidence: Evidence? = null,
    @Json(serializeNull = false) var proof: Proof? = null
) : VerifiableCredential(type) {
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableAttestation", "Europass"),
        template = {
            Europass(
                id = "education#higherEducation#51e42fda-cb0a-4333-b6a6-35cb147e1a88",
                issuer = "did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9",
                issuanceDate = "2020-11-03T00:00:00Z",
                validFrom = "2020-11-03T00:00:00Z",
                credentialSubject = CredentialSubject(
                    id = "did:ebsi:22AhtW7XMssv7es4YcQTdV2MCM3c8b1VsiBfi5weHsjcCY9o",
                    identifier = "0904008084H",
                    givenNames = "Jane",
                    familyName = "DOE",
                    dateOfBirth = "1993-04-08T00:00:00Z",
                    gradingScheme = CredentialSubject.GradingScheme(
                        id = "https://blockchain.univ-lille.fr/ontology#GradingScheme",
                        title = "Lower Second-Class Honours"
                    ),
                    learningAchievement = CredentialSubject.LearningAchievement(
                        id = "https://blockchain.univ-lille.fr/ontology#LearningAchievment",
                        title = "MASTERS LAW, ECONOMICS AND MANAGEMENT",
                        description = "MARKETING AND SALES",
                        additionalNote = listOf(
                            "DISTRIBUTION MANAGEMENT"
                        )
                    ),
                    awardingOpportunity = CredentialSubject.AwardingOpportunity(
                        id = "https://blockchain.univ-lille.fr/ontology#AwardingOpportunity",
                        identifier = "https://certificate-demo.bcdiploma.com/check/87ED2F2270E6C41456E94B86B9D9115B4E35BCCAD200A49B846592C14F79C86BV1Fnbllta0NZTnJkR3lDWlRmTDlSRUJEVFZISmNmYzJhUU5sZUJ5Z2FJSHpWbmZZ",
                        awardingBody = CredentialSubject.AwardingOpportunity.AwardingBody(
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
                    learningSpecification = CredentialSubject.LearningSpecification(
                        id = "https://blockchain.univ-lille.fr/ontology#LearningSpecification",
                        iscedfCode = listOf(
                            "7"
                        ),
                        ectsCreditPoints = 120,
                        eqfLevel = 7,
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
                )
            )
        }
    );

    data class Evidence(
        @Json(serializeNull = false) var id: String? = null,
        @Json(serializeNull = false) var type: List<String?>? = null,
        @Json(serializeNull = false) var verifier: String? = null,
        @Json(serializeNull = false) var evidenceDocument: List<String?>? = null,
        @Json(serializeNull = false) var subjectPresence: String? = null,
        @Json(serializeNull = false) var documentPresence: List<String?>? = null
    )

    data class CredentialSubject(
        @Json(serializeNull = false) var id: String? = null, // did:ebsi:22AhtW7XMssv7es4YcQTdV2MCM3c8b1VsiBfi5weHsjcCY9o
        @Json(serializeNull = false) var identifier: String? = null, // 0904008084H
        @Json(serializeNull = false) var givenNames: String? = null, // Jane
        @Json(serializeNull = false) var familyName: String? = null, // DOE
        @Json(serializeNull = false) var dateOfBirth: String? = null, // 1993-04-08T00:00:00Z
        @Json(serializeNull = false) var gradingScheme: GradingScheme? = null,
        @Json(serializeNull = false) var learningAchievement: LearningAchievement? = null,
        @Json(serializeNull = false) var awardingOpportunity: AwardingOpportunity? = null,
        @Json(serializeNull = false) var learningSpecification: LearningSpecification? = null
    ) {
        data class GradingScheme(
            var id: String, // https://blockchain.univ-lille.fr/ontology#GradingScheme
            @Json(serializeNull = false) var title: String? = null, // Lower Second-Class Honours
            @Json(serializeNull = false) var description: String? = null
        )

        data class LearningAchievement(
            var id: String, // https://blockchain.univ-lille.fr/ontology#LearningAchievment
            var title: String, // MASTERS LAW, ECONOMICS AND MANAGEMENT
            @Json(serializeNull = false) var description: String? = null, // MARKETING AND SALES
            @Json(serializeNull = false) var additionalNote: List<String>? = null
        )

        data class AwardingOpportunity(
            var id: String, // https://blockchain.univ-lille.fr/ontology#AwardingOpportunity
            var identifier: String, // https://certificate-demo.bcdiploma.com/check/87ED2F2270E6C41456E94B86B9D9115B4E35BCCAD200A49B846592C14F79C86BV1Fnbllta0NZTnJkR3lDWlRmTDlSRUJEVFZISmNmYzJhUU5sZUJ5Z2FJSHpWbmZZ
            var awardingBody: AwardingBody,
            @Json(serializeNull = false) var location: String? = null, // FRANCE
            @Json(serializeNull = false) var startedAtTime: String? = null, // Unknown
            @Json(serializeNull = false) var endedAtTime: String? = null // 2020-11-03T00:00:00Z
        ) {
            data class AwardingBody(
                var id: String, // did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9
                var eidasLegalIdentifier: String, // Unknown
                var registration: String, // 0597065J
                var preferredName: String, // Université de Lille
                @Json(serializeNull = false) var homepage: String? = null // https://www.univ-lille.fr/
            )
        }

        data class LearningSpecification(
            var id: String, // https://blockchain.univ-lille.fr/ontology#LearningSpecification
            var iscedfCode: List<String>,
            @Json(serializeNull = false) var ectsCreditPoints: Int? = null, // 120
            @Json(serializeNull = false) var eqfLevel: Int? = null, // 7
            var nqfLevel: List<String>
        )
    }
}

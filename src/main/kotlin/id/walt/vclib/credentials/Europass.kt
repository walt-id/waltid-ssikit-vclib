package id.walt.vclib.credentials


import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class Europass(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf(
        "https://www.w3.org/2018/credentials/v1"
    ),
    @Json(serializeNull = false) override var id: String? = null, // education#higherEducation#51e42fda-cb0a-4333-b6a6-35cb147e1a88
    @Json(serializeNull = false) override var issuer: String? = null, // did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9
    @Json(serializeNull = false) override var issuanceDate: String? = null, // 2020-11-03T00:00:00Z
    @Json(serializeNull = false) override var validFrom: String? = null, // 2020-11-03T00:00:00Z
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override var credentialSubject: EuropassSubject? = null,
    @Json(serializeNull = false) var credentialStatus: CredentialStatus? = null,
    @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) var evidence: Evidence? = null,
    //@Json(serializeNull = false) var proof: List<Proof>? = null
    @Json(serializeNull = false) override var proof: Proof? = null
) : AbstractVerifiableCredential<Europass.EuropassSubject>(type) {
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableAttestation", "Europass"),
        template = {
            Europass(
                id = "education#higherEducation#51e42fda-cb0a-4333-b6a6-35cb147e1a88",
                issuer = "did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9",
                issuanceDate = "2020-11-03T00:00:00Z",
                validFrom = "2020-11-03T00:00:00Z",
                credentialSubject = EuropassSubject(
                    id = "did:ebsi:22AhtW7XMssv7es4YcQTdV2MCM3c8b1VsiBfi5weHsjcCY9o",
                    identifier = "0904008084H",
                    givenNames = "Jane",
                    familyName = "DOE",
                    dateOfBirth = "1993-04-08",
                    gradingScheme = EuropassSubject.GradingScheme(
                        id = "https://leaston.bcdiploma.com/law-economics-management#GradingScheme",
                        title = "Lower Second-Class Honours"
                    ),
                    learningAchievement = EuropassSubject.LearningAchievement(
                        id = "https://leaston.bcdiploma.com/law-economics-management#LearningAchievment",
                        title = "MASTERS LAW, ECONOMICS AND MANAGEMENT",
                        description = "MARKETING AND SALES",
                        additionalNote = listOf(
                            "DISTRIBUTION MANAGEMENT"
                        )
                    ),
                    awardingOpportunity = EuropassSubject.AwardingOpportunity(
                        id = "https://leaston.bcdiploma.com/law-economics-management#AwardingOpportunity",
                        identifier = "https://certificate-demo.bcdiploma.com/check/87ED2F2270E6C41456E94B86B9D9115B4E35BCCAD200A49B846592C14F79C86BV1Fnbllta0NZTnJkR3lDWlRmTDlSRUJEVFZISmNmYzJhUU5sZUJ5Z2FJSHpWbmZZ",
                        awardingBody = EuropassSubject.AwardingOpportunity.AwardingBody(
                            id = "did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9",
                            eidasLegalIdentifier = "Unknown",
                            registration = "0597065J",
                            preferredName = "Leaston University",
                            homepage = "https://leaston.bcdiploma.com/"
                        ),
                        location = "FRANCE",
                        startedAtTime = "Unknown",
                        endedAtTime = "2020-11-03T00:00:00Z"
                    ),
                    learningSpecification = EuropassSubject.LearningSpecification(
                        id = "https://leaston.bcdiploma.com/law-economics-management#LearningSpecification",
                        ISCEDFCode = listOf(
                            "7"
                        ),
                        ECTSCreditPoints = 120,
                        EQFLevel = 7,
                        NQFLevel = listOf(
                            "7"
                        )
                    )
                ),
                credentialStatus = CredentialStatus(
                    id = "https://essif.europa.eu/status/education#higherEducation#51e42fda-cb0a-4333-b6a6-35cb147e1a88",
                    type = "CredentialsStatusList2020"
                ),
                credentialSchema = CredentialSchema(
                    id = "https://essif.europa.eu/trusted-schemas-registry/v1/schemas/to_be_obtained_after_registration_of_the_schema",
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

    data class EuropassSubject(
        @Json(serializeNull = false) override var id: String? = null, // did:ebsi:22AhtW7XMssv7es4YcQTdV2MCM3c8b1VsiBfi5weHsjcCY9o
        @Json(serializeNull = false) var identifier: String? = null, // 0904008084H
        @Json(serializeNull = false) var givenNames: String? = null, // Jane
        @Json(serializeNull = false) var familyName: String? = null, // DOE
        @Json(serializeNull = false) var dateOfBirth: String? = null, // 1993-04-08
        @Json(serializeNull = false) var gradingScheme: GradingScheme? = null,
        @Json(serializeNull = false) var learningAchievement: LearningAchievement? = null,
        @Json(serializeNull = false) var awardingOpportunity: AwardingOpportunity? = null,
        @Json(serializeNull = false) var learningSpecification: LearningSpecification? = null
    ) : CredentialSubject() {
        data class GradingScheme(
            var id: String, // https://leaston.bcdiploma.com/law-economics-management#GradingScheme
            @Json(serializeNull = false) var title: String? = null, // Lower Second-Class Honours
            @Json(serializeNull = false) var description: String? = null
        )

        data class LearningAchievement(
            var id: String, // https://leaston.bcdiploma.com/law-economics-management#LearningAchievment
            var title: String, // MASTERS LAW, ECONOMICS AND MANAGEMENT
            @Json(serializeNull = false) var description: String? = null, // MARKETING AND SALES
            @Json(serializeNull = false) var additionalNote: List<String>? = null
        )

        data class AwardingOpportunity(
            var id: String, // https://leaston.bcdiploma.com/law-economics-management#AwardingOpportunity
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
                var preferredName: String, // Leaston University
                @Json(serializeNull = false) var homepage: String? = null // https://leaston.bcdiploma.com/
            )
        }

        data class LearningSpecification(
            var id: String, // https://leaston.bcdiploma.com/law-economics-management#LearningSpecification
            @Json(name = "ISCEDFCode") var ISCEDFCode: List<String>,
            @Json(name = "ECTSCreditPoints") var ECTSCreditPoints: Int? = null, // 120
            @Json(name = "EQFLevel") var EQFLevel: Int? = null, // 7
            @Json(name = "NQFLevel") var NQFLevel: List<String>
        )
    }
}

/*
@Serializable
class Europass(
    @SerialName("@context") override var context: List<String>,
    override var id: String,
    override var type: List<String>,
    override var issuer: String,
    override var issuanceDate: String,
    override var validFrom: String,
    @field:Nullable override var expirationDate: String? = null,
    override var credentialSubject: EuropassCredentialSubject,
    override var credentialStatus: CredentialStatus,
    override var credentialSchema: CredentialSchema,
    @field:Nullable override var evidence: Evidence? = null,
    override var proof: Proof
) : VerifiableCredential<EuropassCredentialSubject>() {
    override fun encode() = Json { prettyPrint = true }.encodeToString(this)
}

@Serializable
data class EuropassCredentialSubject(
    var id: String,
    var identifier: String,
    var givenNames: String,
    var familyName: String,
    var dateOfBirth: String,
    var gradingScheme: GradingScheme,
    var learningAchievement: LearningAchievement,
    var awardingOpportunity: AwardingOpportunity,
    var learningSpecification: LearningSpecification
) : CredentialSubject

@Serializable
data class GradingScheme(
    var id: String,
    @field:Nullable var title: String? = null,
    @field:Nullable var description: String? = null,
)

@Serializable
data class LearningAchievement(
    var id: String,
    var title: String,
    @field:Nullable var description: String? = null,
    @field:Nullable var additionalNote: List<String>? = null
)

@Serializable
data class AwardingOpportunity(
    var id: String,
    var identifier: String,
    var awardingBody: Organisation,
    @field:Nullable var location: String? = null,
    @field:Nullable var startedAtTime: String? = null,
    @field:Nullable var endedAtTime: String? = null
)

@Serializable
data class Organisation(
    var id: String,
    var eidasLegalIdentifier: String,
    var registration: String,
    var preferredName: String,
    @field:Nullable var homepage: String? = null
)

@Serializable
data class LearningSpecification(
    var id: String,
    @SerialName("ISCEDFCode") var iscedfCode: List<String>,
    @SerialName("ECTSCreditPoints") @field:Nullable var ectsCreditPoints: Int? = null,
    @SerialName("EQFLevel") @field:Nullable var eqfLevel: Int? = null,
    @SerialName("NQFLevel") var nqfLevel: List<String>
)
 */

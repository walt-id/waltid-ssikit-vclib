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
    @Json(serializeNull = false) override var id: String? = null,
    @Json(serializeNull = false) override var issuer: String? = null,
    @Json(serializeNull = false) override var issuanceDate: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override var credentialSubject: EuropassSubject? = null,
    @Json(serializeNull = false) var credentialStatus: CredentialStatus? = null,
    @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) var evidence: Evidence? = null,
    @Json(serializeNull = false) override var proof: Proof? = null
) : AbstractVerifiableCredential<Europass.EuropassSubject>(type) {
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableAttestation", "Europass"),
        template = {
            Europass(
                id = "urn:credential:5a4d5412-27e3-4540-a5e5-f1aa4d55b20c",
                issuer = "did:epass:org:1",
                issuanceDate = "2020-07-20T13:58:53+02:00",
                validFrom = "2019-09-20T00:00:00+02:00",
                credentialSubject = EuropassSubject(
                    id = "did:epass:person:1",
                    identifier = EuropassSubject.Identifier("Student identification number", "99009900"),
                    achieved = listOf(
                        EuropassSubject.Achieved(
                            id = "urn:epass:learningAchievement:1",
                            title = "Master of Science in Civil Engineering",
                            wasDerivedFrom = EuropassSubject.Achieved.WasDerivedFrom(
                                id = "urn:epass:assessment:1",
                                title = "Overall Diploma Assessment",
                                grade = "excellent (5)",
                                assessedBy = listOf("did:ebsi:zsSgDXeYPhZ3AuKhTFneDf1"),
                                specifiedBy = EuropassSubject.Achieved.WasDerivedFrom.SpecifiedBy(
                                    id = "urn:epass:asssessmentspec:1",
                                    title = "Overall Diploma Assessment",
                                    gradingScheme = EuropassSubject.Achieved.WasDerivedFrom.SpecifiedBy.GradingScheme(
                                        id = "urn:epass:scoringschemespec:1",
                                        title = "General grading scheme in Croatia",
                                        definition = "The Croatian national grading scheme consists of five grades with numerical equivalents: izvrstan – 5 (outstanding); vrlo dobar – 4 (very good); dobar – 3 (good); dovoljan – 2 (sufficient); nedovoljan – 1 (insufficient - fail). The minimum passing grade is dovoljan – 2."
                                    )
                                )
                            ),
                            wasInfluencedBy = EuropassSubject.Achieved.WasInfluencedBy(
                                id = "urn:epass:learningAchievement:1",
                                identifier = listOf(
                                    EuropassSubject.Identifier(
                                        schemeID = "Activity ID",
                                        value = "GAB701"
                                    )
                                ),
                                title = "Master of Science in Civil Engineering",
                                workload = "PT60H",
                                startedAtTime = "2017-09-04T00:00:00+02:00",
                                endedAtTime = "2018-01-14T00:00:00+01:00",
                                directedBy = listOf("did:ebsi:zsSgDXeYPhZ3AuKhTFneDf1"),
                                location = listOf("urn:epass:location:4"),
                                specifiedBy = EuropassSubject.Achieved.WasInfluencedBy.SpecifiedBy(
                                    id = "urn:epass:learningactivityspec:1",
                                    title = "Applied mathematics",
                                    learningActivityType = listOf("http://data.europa.eu/snb/learning-activity/fd33e234ae"),
                                    workload = "PT60H",
                                    language = listOf("http://publications.europa.eu/resource/authority/language/HRV"),
                                ),
                            ),
                            wasAwardedBy = EuropassSubject.Achieved.WasAwardedBy(""),
                            hasPart = EuropassSubject.Achieved.HasPart(""),
                            entitlesTo = EuropassSubject.Achieved.EntitlesTo(""),
                            specifiedBy = EuropassSubject.Achieved.SpecifiedBy("")
                        ),
                    )
                ),
                credentialStatus = CredentialStatus(
                    id = "https://essif.europa.eu/status/education#higherEducation#51e42fda-cb0a-4333-b6a6-35cb147e1a88",
                    type = "TrustedCredentialStatus2021"
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
        @Json(serializeNull = false) override var id: String? = null,
        @Json(serializeNull = false) var identifier: Identifier? = null,
        @Json(serializeNull = false) var achieved: List<Achieved>? = null,
        @Json(serializeNull = false) var givenNames: String? = null,
        @Json(serializeNull = false) var familyName: String? = null,
        @Json(serializeNull = false) var dateOfBirth: String? = null,
        @Json(serializeNull = false) var learningAchievement: LearningAchievement? = null,
        @Json(serializeNull = false) var awardingOpportunity: AwardingOpportunity? = null,
        @Json(serializeNull = false) var learningSpecification: LearningSpecification? = null
    ) : CredentialSubject() {

        data class Identifier(
            var schemeID: String,
            var value: String
        )

        data class Achieved(
            var id: String,
            var title: String,
            var wasDerivedFrom: WasDerivedFrom,
            var wasInfluencedBy: WasInfluencedBy,
            var wasAwardedBy: WasAwardedBy,
            var hasPart: HasPart,
            var entitlesTo: EntitlesTo,
            var specifiedBy: SpecifiedBy,
        ) {
            data class WasDerivedFrom(
                var id: String,
                var title: String,
                var grade: String,
                var assessedBy: List<String>,
                var specifiedBy: SpecifiedBy
            ) {
                data class SpecifiedBy(
                    var id: String,
                    var title: String,
                    var gradingScheme: GradingScheme?
                ) {
                    data class GradingScheme(
                        var id: String,
                        var title: String? = null,
                        var definition: String? = null
                    )
                }
            }

            data class WasInfluencedBy(
                var id: String,
                var identifier: List<Identifier>,
                var title: String,
                var workload: String,
                var startedAtTime: String,
                var endedAtTime: String,
                var directedBy: List<String>,
                var location: List<String>,
                var specifiedBy: SpecifiedBy
            ) {
                data class SpecifiedBy(
                    var id: String,
                    var title: String,
                    var learningActivityType: List<String>,
                    var workload: String,
                    var language: List<String>,
                )
            }

            data class WasAwardedBy(
                var id: String,
            )

            data class HasPart(
                var id: String,
            )

            data class EntitlesTo(
                var id: String,
            )

            data class SpecifiedBy(
                var id: String,
            )
        }

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

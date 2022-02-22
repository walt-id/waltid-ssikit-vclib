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
    @Json(serializeNull = false) override var issued: String? = null,
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
                issued = "2020-07-20T13:58:53+02:00",
                validFrom = "2019-09-20T00:00:00+02:00",
                credentialSubject = EuropassSubject(
                    id = "did:epass:person:1",
                    identifier = EuropassSubject.Identifier("Student identification number", "99009900"),
                    achieved = listOf(
                        EuropassSubject.Achieved(
                            id = "urn:epass:learningAchievement:1",
                            title = "Master of Science in Civil Engineering",
                            wasDerivedFrom = listOf(EuropassSubject.Achieved.WasDerivedFrom(
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
                                ))
                            ),
                            wasInfluencedBy = listOf(EuropassSubject.Achieved.WasInfluencedBy(
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
                            )),
                            wasAwardedBy = EuropassSubject.Achieved.WasAwardedBy(
                                id = "urn:epass:awardingProcess:1",
                                awardingBody = listOf("did:ebsi:zsSgDXeYPhZ3AuKhTFneDf1"),
                                awardingDate = "2019-09-20T00:00:00+02:00"
                            ),
                            hasPart = EuropassSubject.Achieved.HasPart(
                                learningAchievements = listOf(
                                    EuropassSubject.Achieved.HasPart.LearningAchievement(
                                        id = "",
                                        identifier = listOf(
                                            EuropassSubject.Identifier(
                                                schemeID = "Achievement ID",
                                                value = "GAB701"
                                            )
                                        ),
                                        title = "",
                                        wasDerivedFrom = listOf(
                                            EuropassSubject.Achieved.WasDerivedFrom(
                                                id = "urn:epass:asssessmentspec:2",
                                                title = "Applied mathematics",
                                                grade = "good (3)",
                                                assessedBy = listOf("did:ebsi:zsSgDXeYPhZ3AuKhTFneDf1"),
                                                specifiedBy = EuropassSubject.Achieved.WasDerivedFrom.SpecifiedBy(
                                                    id = "urn:epass:asssessmentspec:2",
                                                    title = "Applied mathematics",
                                                    gradingScheme = EuropassSubject.Achieved.WasDerivedFrom.SpecifiedBy.GradingScheme(
                                                        id = "urn:epass:scoringschemespec:2",
                                                        title = "General grading scheme in Croatia",
                                                        definition = "The Croatian national grading scheme consists of five grades with numerical equivalents: izvrstan – 5 (outstanding); vrlo dobar – 4 (very good); dobar – 3 (good); dovoljan – 2 (sufficient); nedovoljan – 1 (insufficient - fail). The minimum passing grade is dovoljan – 2."
                                                    )
                                                )
                                            ),
                                        ),
                                        wasInfluencedBy = listOf(
                                            EuropassSubject.Achieved.WasInfluencedBy(
                                                id = "urn:epass:activity:1",
                                                identifier = listOf(
                                                    EuropassSubject.Identifier(
                                                        schemeID = "Activity ID",
                                                        value = "GAB701"
                                                    )
                                                ),
                                                title = "Applied mathematics",
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
                                                    language = listOf("http://publications.europa.eu/resource/authority/language/HRV")
                                                )
                                            )
                                        ),
                                        wasAwardedBy = EuropassSubject.Achieved.WasAwardedBy(
                                            id = "urn:epass:awardingProcess:2",
                                            awardingBody = listOf("did:ebsi:zsSgDXeYPhZ3AuKhTFneDf1"),
                                            awardingDate = "2019-09-20T00:00:00+02:00"
                                        ),
                                        specifiedBy = listOf(
                                            EuropassSubject.Achieved.HasPart.LearningAchievement.SpecifiedBy(
                                                id = "urn:epass:qualification:1",
                                                title = "Applied mathematics",
                                                volumeOfLearning = "PT60H",
                                                eCTSCreditPoints = 5,
                                                maximumDuration = "P6M",
                                                isPartialQualification = true,
                                                eqflLevel = "http://data.europa.eu/snb/eqf/4",
                                                nqflLevel = "http://data.europa.eu/snb/qdr/c_49672c5a",
                                            )
                                        )
                                    ),
                                ),
                            ),
                            entitlesTo = EuropassSubject.Achieved.EntitlesTo(
                                id = "urn:epass:entitlement:1",
                                title = "Postgraduate doctoral study",
                                definition = "Competences the student acquires after the completion of Graduate university study are sufficient conditions for attending the programme of Postgraduate doctoral study at the Faculty of Civil Engineering, Architecture and Geodesy in Split, as well as for attending the same or similar programmes and Postgraduate specialist studies at other faculties of Civil Engineering in Croatia. The acquired learning outcomes enable the student to attend other postgraduate study programmes in the field of technical sciences. ",
                                issuedDate = "2019-09-20",
                                specifiedBy = EuropassSubject.Achieved.EntitlesTo.SpecifiedBy(
                                    id = "urn:epass:entitlementspec:1",
                                    title = "Postgraduate doctoral study",
                                    entitlementType = "http://data.europa.eu/snb/entitlement/64aad92881",
                                    status = "http://data.europa.eu/snb/entitlement-status/5b8d6b34fb",
                                    limitOrganisation = listOf("did:ebsi:zsSgDXeYPhZ3AuKhTFneDf1"),
                                    limitJurisdiction = listOf("http://publications.europa.eu/resource/authority/country/HRV")
                                )
                            ),
                            specifiedBy = listOf(EuropassSubject.Achieved.SpecifiedBy(
                                id = "urn:epass:qualification:20",
                                title = "Master of Science in Civil Engineering",
                                volumeOfLearning = "PT1440H",
                                eCTSCreditPoints = 120,
                                maximumDuration = "P21M",
                                entryRequirementsNote = "The minimum educational requirement for enrolment into graduate university programmes is the completion of an undergraduate university programme. The university can allow students who have completed a professional programme to also enrol graduate university programmes, but they are allowed to set special requirements in these cases.\n" +
                                        "The minimum educational requirement for enrolment into specialist graduate professional programmes is the completion of an undergraduate university programme or a professional programme (first cycle).",
                                isPartialQualification = false,
                                eqflLevel = "http://data.europa.eu/snb/eqf/5",
                                nqflLevel = "http://data.europa.eu/snb/qdr/c_dcc9aca1"
                            )
                        )),
                    )
                ),
                credentialStatus = CredentialStatus(
                    id = "https://essif.europa.eu/status/education#higherEducation#51e42fda-cb0a-4333-b6a6-35cb147e1a88",
                    type = "TrustedCredentialStatus2021"
                ),
                credentialSchema = CredentialSchema(
                    id = "https://raw.githubusercontent.com/walt-id/waltid-ssikit-vclib/master/src/test/resources/schemas/Europass.json",
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
            @Json(serializeNull = false) var additionalNote: List<String>? = null,
            @Json(serializeNull = false) var identifier: List<Identifier>? = null,
            @Json(serializeNull = false) var wasDerivedFrom: List<WasDerivedFrom>? = null,
            @Json(serializeNull = false) var wasInfluencedBy: List<WasInfluencedBy>? = null,
            @Json(serializeNull = false) var wasAwardedBy: WasAwardedBy? = null,
            @Json(serializeNull = false) var hasPart: HasPart? = null,
            @Json(serializeNull = false) var entitlesTo: EntitlesTo? = null,
            @Json(serializeNull = false) var specifiedBy: List<SpecifiedBy>? = null,
        ) {
            data class WasDerivedFrom(
                var id: String,
                var title: String,
                var grade: String,
                @Json(serializeNull = false) var assessedBy: List<String>? = null,
                @Json(serializeNull = false) var hasPart: List<WasDerivedFrom>? = null,
                @Json(serializeNull = false) var specifiedBy: SpecifiedBy? = null
            ) {
                data class SpecifiedBy(
                    var id: String,
                    var title: String,
                    @Json(serializeNull = false) var gradingScheme: GradingScheme? = null
                ) {
                    data class GradingScheme(
                        var id: String,
                        @Json(serializeNull = false) var title: String? = null,
                        @Json(serializeNull = false) var definition: String? = null
                    )
                }
            }

            data class WasInfluencedBy(
                var id: String,
                @Json(serializeNull = false) var identifier: List<Identifier>? = null,
                var title: String,
                @Json(serializeNull = false) var definition: String? = null,
                @Json(serializeNull = false) var workload: String? = null,
                @Json(serializeNull = false) var startedAtTime: String? = null,
                @Json(serializeNull = false) var endedAtTime: String? = null,
                @Json(serializeNull = false) var directedBy: List<String>? = null,
                @Json(serializeNull = false) var location: List<String>? = null,
                @Json(serializeNull = false) var specifiedBy: SpecifiedBy? = null
            ) {
                data class SpecifiedBy(
                    var id: String,
                    @Json(serializeNull = false) var title: String? = null,
                    @Json(serializeNull = false) var identifier: List<Identifier>? = null,
                    @Json(serializeNull = false) var learningActivityType: List<String>? = null,
                    @Json(serializeNull = false) var workload: String? = null,
                    @Json(serializeNull = false) var language: List<String>? = null,
                    @Json(serializeNull = false) var alternativeLabel: List<String>? = null,
                    @Json(serializeNull = false) var definition: String? = null,
                    @Json(serializeNull = false) var additionalNote: List<String>? = null,
                    @Json(serializeNull = false) var homePage: String? = null,
                    @Json(serializeNull = false) var suplementaryDocument: List<String>? = null,
                    @Json(serializeNull = false) var mode: List<String>? = null,
                    @Json(serializeNull = false) var teaches: List<Achieved.SpecifiedBy>? = null,
                    @Json(serializeNull = false) var hasPart: List<SpecifiedBy>? = null,
                    @Json(serializeNull = false) var specialisationOf: List<SpecifiedBy>? = null
                )
            }

            data class WasAwardedBy(
                var id: String,
                var awardingBody: List<String>,
                @Json(serializeNull = false) var awardingDate: String? = null,
                @Json(serializeNull = false) var awardingLocation: List<String>? = null
            )

            data class HasPart(
                var learningAchievements: List<LearningAchievement>
            ) {
                data class LearningAchievement(
                    var id: String,
                    var identifier: List<Identifier>,
                    var title: String,
                    var wasDerivedFrom: List<WasDerivedFrom>,
                    var wasInfluencedBy: List<WasInfluencedBy>,
                    var wasAwardedBy: WasAwardedBy,
                    var specifiedBy: List<SpecifiedBy>
                ) {

                    data class SpecifiedBy(
                        var id: String,
                        var title: String,
                        var volumeOfLearning: String,
                        var eCTSCreditPoints: Int,
                        var maximumDuration: String,
                        var isPartialQualification: Boolean,
                        var eqflLevel: String,
                        var nqflLevel: String,
                    )
                }
            }

            data class EntitlesTo(
                var id: String,
                var title: String,
                var definition: String,
                var issuedDate: String,
                var specifiedBy: SpecifiedBy,
            ) {
                data class SpecifiedBy(
                    var id: String,
                    var title: String,
                    var entitlementType: String,
                    var status: String,
                    var limitOrganisation: List<String>,
                    var limitJurisdiction: List<String>,
                )
            }

            data class SpecifiedBy(
                var id: String,
                @Json(serializeNull = false) var identifier: Identifier? = null,
                @Json(serializeNull = false) var title: String? = null,
                @Json(serializeNull = false) var volumeOfLearning: String? = null,
                @Json(serializeNull = false) var eCTSCreditPoints: Int? = null,
                @Json(serializeNull = false) var maximumDuration: String? = null,
                @Json(serializeNull = false) var entryRequirementsNote: String? = null,
                @Json(serializeNull = false) var isPartialQualification: Boolean? = null,
                @Json(serializeNull = false) var eqflLevel: String? = null,
                @Json(serializeNull = false) var nqflLevel: String? = null,
                @Json(serializeNull = false) var iSCEDFCode: List<String>? = null,
                @Json(serializeNull = false) var learningOutcome: List<LearningOutcome>? = null,
                @Json(serializeNull = false) var learningOpportunityType: List<String>? = null,
                @Json(serializeNull = false) var alternativeLabel: List<String>? = null,
                @Json(serializeNull = false) var definition: String? = null,
                @Json(serializeNull = false) var learningOutcomeDescription: String? = null,
                @Json(serializeNull = false) var additionalNote: List<String>? = null,
                @Json(serializeNull = false) var homePage: String? = null,
                @Json(serializeNull = false) var suplementaryDocument: List<String>? = null,
                @Json(serializeNull = false) var educationSubject: List<String>? = null,
                @Json(serializeNull = false) var creditPoints: Int? = null,
                @Json(serializeNull = false) var educationLevel: List<String>? = null,
                @Json(serializeNull = false) var language: List<String>? = null,
                @Json(serializeNull = false) var mode: List<String>? = null,
                @Json(serializeNull = false) var learningSetting: String? = null,
                @Json(serializeNull = false) var targetGroup: List<String>? = null,
                @Json(serializeNull = false) var learningActivitySpecification: WasInfluencedBy.SpecifiedBy? = null,
                @Json(serializeNull = false) var assessmentSpecification: WasDerivedFrom.SpecifiedBy? = null,
                @Json(serializeNull = false) var entitlementSpecification: List<EntitlesTo.SpecifiedBy>? = null,
                @Json(serializeNull = false) var awardingOpportunity: List<AwardingOpportunity>? = null,
                @Json(serializeNull = false) var hasPart: List<SpecifiedBy>? = null,
                @Json(serializeNull = false) var specialisationOf: List<SpecifiedBy>? = null
            ) {
                data class LearningOutcome(
                    var id: String,
                    var name: String,
                    @Json(serializeNull = false) var identifier: Identifier? = null,
                    @Json(serializeNull = false) var definition: String? = null,
                    @Json(serializeNull = false) var learningOutcomeType: String? = null,
                    @Json(serializeNull = false) var reusabilityLevel: String? = null,
                    @Json(serializeNull = false) var relatedSkill: List<String>? = null,
                    @Json(serializeNull = false) var relatedESCOSkill: List<String>? = null
                )

                data class AwardingOpportunity(
                    var id: String,
                    @Json(serializeNull = false) var identifier: Identifier? = null,
                    @Json(serializeNull = false) var awardingBody: List<String>? = null,
                    @Json(serializeNull = false) var location: String? = null,
                    @Json(serializeNull = false) var startedAtTime: String? = null,
                    @Json(serializeNull = false) var endedAtTime: String? = null
                )
            }
        }

        data class LearningAchievement(
            var id: String,
            var title: String,
            @Json(serializeNull = false) var description: String? = null,
            @Json(serializeNull = false) var additionalNote: List<String>? = null
        )

        data class AwardingOpportunity(
            var id: String,
            var identifier: String,
            var awardingBody: AwardingBody,
            @Json(serializeNull = false) var location: String? = null,
            @Json(serializeNull = false) var startedAtTime: String? = null,
            @Json(serializeNull = false) var endedAtTime: String? = null
        ) {
            data class AwardingBody(
                var id: String,
                var eidasLegalIdentifier: String,
                var registration: String,
                var preferredName: String,
                @Json(serializeNull = false) var homepage: String? = null
            )
        }

        data class LearningSpecification(
            var id: String,
            @Json(name = "ISCEDFCode") var ISCEDFCode: List<String>,
            @Json(name = "ECTSCreditPoints") var ECTSCreditPoints: Int? = null,
            @Json(name = "EQFLevel") var EQFLevel: Int? = null,
            @Json(name = "NQFLevel") var NQFLevel: List<String>
        )
    }
}

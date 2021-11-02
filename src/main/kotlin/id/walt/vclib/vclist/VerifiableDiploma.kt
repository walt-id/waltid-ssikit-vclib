package id.walt.vclib.vclist

import com.beust.klaxon.Json
import com.nimbusds.jwt.SignedJWT
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.model.CredentialStatus
import id.walt.vclib.model.Proof
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.JsonIgnore
import java.text.SimpleDateFormat
import java.util.*

private val dateFormat = SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss'Z'").also { it.timeZone = TimeZone.getTimeZone("UTC") }

data class VerifiableDiploma(
    @Json(name = "@context") var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) override var id: String? = null, // education#higherEducation#51e42fda-cb0a-4333-b6a6-35cb147e1a88
    @Json(serializeNull = false) var issuer: String? = null, // did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9
    @Json(serializeNull = false) var issuanceDate: String? = null, // 2020-11-03T00:00:00Z
    @Json(serializeNull = false) var validFrom: String? = null, // 2020-11-03T00:00:00Z
    @Json(serializeNull = false) var expirationDate: String? = null,
    var credentialSubject: CredentialSubject? = null,
    @Json(serializeNull = false) var credentialStatus: CredentialStatus? = null,
    var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) var evidence: Evidence? = null,
    @Json(serializeNull = false) var proof: Proof? = null
) : VerifiableCredential(type) {
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableAttestation", "VerifiableDiploma"),
        template = {
            VerifiableDiploma(
                id = "education#higherEducation#${UUID.randomUUID()}",
                issuer = "did:ebsi:2A9BZ9SUe6BatacSpvs1V5CdjHvLpQ7bEsi2Jb6LdHKnQxaN",
                issuanceDate = "2021-08-31T00:00:00Z",
                validFrom = "2021-08-31T00:00:00Z",
                credentialSubject = CredentialSubject(
                    id = "did:ebsi:2AEMAqXWKYMu1JHPAgGcga4dxu7ThgfgN95VyJBJGZbSJUtp",
                    identifier = "0904008084H",
                    givenNames = "Jane",
                    familyName = "DOE",
                    dateOfBirth = "1993-04-08",
                    gradingScheme = CredentialSubject.GradingScheme(
                        id = "https://leaston.bcdiploma.com/law-economics-management#GradingScheme",
                        title = "Lower Second-Class Honours"
                    ),
                    learningAchievement = CredentialSubject.LearningAchievement(
                        id = "https://leaston.bcdiploma.com/law-economics-management#LearningAchievment",
                        title = "MASTERS LAW, ECONOMICS AND MANAGEMENT",
                        description = "MARKETING AND SALES",
                        additionalNote = listOf("DISTRIBUTION MANAGEMENT")
                    ),
                    awardingOpportunity = CredentialSubject.AwardingOpportunity(
                        id = "https://leaston.bcdiploma.com/law-economics-management#AwardingOpportunity",
                        identifier = "https://certificate-demo.bcdiploma.com/check/87ED2F2270E6C41456E94B86B9D9115B4E35BCCAD200A49B846592C14F79C86BV1Fnbllta0NZTnJkR3lDWlRmTDlSRUJEVFZISmNmYzJhUU5sZUJ5Z2FJSHpWbmZZ",
                        awardingBody = CredentialSubject.AwardingOpportunity.AwardingBody(
                            id = "did:ebsi:2A9BZ9SUe6BatacSpvs1V5CdjHvLpQ7bEsi2Jb6LdHKnQxaN",
                            // Some issuer do not support eidasLegalIdentifier yet
                            // eidasLegalIdentifier = "Unknown",
                            registration = "0597065J",
                            preferredName = "Leaston University",
                            homepage = "https://leaston.bcdiploma.com/"
                        ),
                        location = "FRANCE",
                        startedAtTime = "2019-09-02T00:00:00Z",
                        endedAtTime = "2020-06-26T00:00:00Z"
                    ),
                    learningSpecification = CredentialSubject.LearningSpecification(
                        id = "https://leaston.bcdiploma.com/law-economics-management#LearningSpecification",
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
                //  EBSI does not support credentialStatus yet
                //  credentialStatus = CredentialStatus(
                //      id = "https://essif.europa.eu/status/education#higherEducation#51e42fda-cb0a-4333-b6a6-35cb147e1a88",
                //      type = "CredentialsStatusList2020"
                //  ),
                credentialSchema = CredentialSchema(
                    id = "https://api.preprod.ebsi.eu/trusted-schemas-registry/v1/schemas/0xbf78fc08a7a9f28f5479f58dea269d3657f54f13ca37d380cd4e92237fb691dd",
                    type = "JsonSchemaValidator2018"
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
                issuer = issuer ?: jwtClaimsSet.issuer
                issuanceDate = issuanceDate ?: jwtClaimsSet.issueTime?.let { dateFormat.format(it) }
                validFrom = validFrom ?: jwtClaimsSet.notBeforeTime?.let { dateFormat.format(it) }
                expirationDate = expirationDate ?: jwtClaimsSet.expirationTime?.let { dateFormat.format(it) }
                credentialSubject?.also { it.id = it.id ?: jwtClaimsSet.subject }
            }
        }

    data class CredentialSubject(
        @Json(serializeNull = false) var id: String? = null,
        var identifier: String? = null,
        var givenNames: String? = null,
        var familyName: String? = null,
        var dateOfBirth: String? = null,
        var gradingScheme: GradingScheme? = null,
        var learningAchievement: LearningAchievement? = null,
        var awardingOpportunity: AwardingOpportunity? = null,
        var learningSpecification: LearningSpecification? = null
    ) {
        data class GradingScheme(
            var id: String,
            @Json(serializeNull = false) var title: String? = null,
            @Json(serializeNull = false) var description: String? = null
        )

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
                @Json(serializeNull = false) var eidasLegalIdentifier: String? = null,
                var registration: String,
                var preferredName: String,
                @Json(serializeNull = false) var homepage: String? = null
            )
        }

        data class LearningSpecification(
            var id: String,
            var iscedfCode: List<String>,
            @Json(serializeNull = false) var ectsCreditPoints: Int? = null,
            @Json(serializeNull = false) var eqfLevel: Int? = null,
            var nqfLevel: List<String>
        )
    }

    data class Evidence(
        @Json(serializeNull = false) var id: String? = null,
        @Json(serializeNull = false) var type: List<String?>? = null,
        @Json(serializeNull = false) var verifier: String? = null,
        @Json(serializeNull = false) var evidenceDocument: List<String?>? = null,
        @Json(serializeNull = false) var subjectPresence: String? = null,
        @Json(serializeNull = false) var documentPresence: List<String?>? = null
    )
}

package id.walt.vclib.credentials

import com.beust.klaxon.Json
import com.nimbusds.jwt.SignedJWT
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.JsonIgnore
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

data class VerifiableDiploma(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) override var id: String? = null, // education#higherEducation#51e42fda-cb0a-4333-b6a6-35cb147e1a88
    @Json(serializeNull = false) override var issuer: String? = null, // did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9
    @Json(serializeNull = false) override var issuanceDate: String? = null, // 2020-11-03T00:00:00Z
    @Json(serializeNull = false) override var validFrom: String? = null, // 2020-11-03T00:00:00Z
    @Json(serializeNull = false) override var expirationDate: String? = null,
    override var credentialSubject: VerifiableDiplomaSubject? = null,
    @Json(serializeNull = false) var credentialStatus: CredentialStatus? = null,
    override var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) var evidence: Evidence? = null,
    @Json(serializeNull = false) override var proof: Proof? = null
) : AbstractVerifiableCredential<VerifiableDiploma.VerifiableDiplomaSubject>(type) {
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableAttestation", "VerifiableDiploma"),
        template = {
            VerifiableDiploma(
                id = "education#higherEducation#392ac7f6-399a-437b-a268-4691ead8f176",
                issuer = "did:ebsi:2A9BZ9SUe6BatacSpvs1V5CdjHvLpQ7bEsi2Jb6LdHKnQxaN",
                issuanceDate = "2021-08-31T00:00:00Z",
                expirationDate = "2022-08-31T00:00:00Z",
                validFrom = "2021-08-31T00:00:00Z",
                credentialSubject = VerifiableDiplomaSubject(
                    id = "did:ebsi:2AEMAqXWKYMu1JHPAgGcga4dxu7ThgfgN95VyJBJGZbSJUtp",
                    identifier = "0904008084H",
                    givenNames = "Jane",
                    familyName = "DOE",
                    dateOfBirth = "1993-04-08",
                    gradingScheme = VerifiableDiplomaSubject.GradingScheme(
                        id = "https://leaston.bcdiploma.com/law-economics-management#GradingScheme",
                        title = "Lower Second-Class Honours"
                    ),
                    learningAchievement = VerifiableDiplomaSubject.LearningAchievement(
                        id = "https://leaston.bcdiploma.com/law-economics-management#LearningAchievment",
                        title = "MASTERS LAW, ECONOMICS AND MANAGEMENT",
                        description = "MARKETING AND SALES",
                        additionalNote = listOf("DISTRIBUTION MANAGEMENT")
                    ),
                    awardingOpportunity = VerifiableDiplomaSubject.AwardingOpportunity(
                        id = "https://leaston.bcdiploma.com/law-economics-management#AwardingOpportunity",
                        identifier = "https://certificate-demo.bcdiploma.com/check/87ED2F2270E6C41456E94B86B9D9115B4E35BCCAD200A49B846592C14F79C86BV1Fnbllta0NZTnJkR3lDWlRmTDlSRUJEVFZISmNmYzJhUU5sZUJ5Z2FJSHpWbmZZ",
                        awardingBody = VerifiableDiplomaSubject.AwardingOpportunity.AwardingBody(
                            id = "did:ebsi:2A9BZ9SUe6BatacSpvs1V5CdjHvLpQ7bEsi2Jb6LdHKnQxaN",
                            eidasLegalIdentifier = "Unknown",
                            registration = "0597065J",
                            preferredName = "Leaston University",
                            homepage = "https://leaston.bcdiploma.com/"
                        ),
                        location = "FRANCE",
                        startedAtTime = "2019-09-02T00:00:00Z",
                        endedAtTime = "2020-06-26T00:00:00Z"
                    ),
                    learningSpecification = VerifiableDiplomaSubject.LearningSpecification(
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
                credentialSchema = CredentialSchema(
                    id = "https://api.preprod.ebsi.eu/trusted-schemas-registry/v1/schemas/0xbf78fc08a7a9f28f5479f58dea269d3657f54f13ca37d380cd4e92237fb691dd",
                    type = "JsonSchemaValidator2018"
                ),
                credentialStatus = CredentialStatus(
                    id = "https://essif.europa.eu/status/education#higherEducation#392ac7f6-399a-437b-a268-4691ead8f176",
                    type = "CredentialStatusList2020"
                ),
                evidence = Evidence(
                    id = "https://essif.europa.eu/tsr-va/evidence/f2aeec97-fc0d-42bf-8ca7-0548192d5678",
                    type = listOf("DocumentVerification"),
                    verifier = "did:ebsi:2962fb784df61baa267c8132497539f8c674b37c1244a7a",
                    evidenceDocument = listOf("Passport"),
                    subjectPresence = "Physical",
                    documentPresence = listOf("Physical")
                )
            )
        }
    )

    data class VerifiableDiplomaSubject(
        @Json(serializeNull = false) override var id: String? = null,
        var identifier: String? = null,
        var givenNames: String? = null,
        var familyName: String? = null,
        var dateOfBirth: String? = null,
        var gradingScheme: GradingScheme? = null,
        var learningAchievement: LearningAchievement? = null,
        var awardingOpportunity: AwardingOpportunity? = null,
        var learningSpecification: LearningSpecification? = null
    ) : CredentialSubject() {
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

    override fun newId(id: String) = "education#higherEducation#${id}"

    override fun setMetaData(
        id: String?,
        issuer: String?,
        subject: String?,
        issuanceDate: Instant?,
        validFrom: Instant?,
        expirationDate: Instant?
    ) {
        super.setMetaData(id, issuer, subject, issuanceDate, validFrom, expirationDate)
        if (issuer != null)
            this.credentialSubject?.awardingOpportunity?.awardingBody?.also { it.id = issuer!! }
    }
}

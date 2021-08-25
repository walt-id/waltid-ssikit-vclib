package id.walt.vclib.vclist


import com.beust.klaxon.Json
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.model.CredentialStatus
import id.walt.vclib.model.Proof
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata

data class VerifiableDiploma(
    @Json(name = "@context") var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) var id: String? = null, // education#higherEducation#51e42fda-cb0a-4333-b6a6-35cb147e1a88
    @Json(serializeNull = false) var issuer: String? = null, // did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9
    @Json(serializeNull = false) var issuanceDate: String? = null, // 2020-11-03T00:00:00Z
    var validFrom: String? = null, // 2020-11-03T00:00:00Z
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
                credentialSchema = CredentialSchema(
                    id = "https://api.preprod.ebsi.eu/trusted-schemas-registry/v1/schemas/0xbf78fc08a7a9f28f5479f58dea269d3657f54f13ca37d380cd4e92237fb691dd",
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
                var eidasLegalIdentifier: String,
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
}

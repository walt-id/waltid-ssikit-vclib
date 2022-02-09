package id.walt.vclib.credentials

import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class VerifiableAttestation(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String>,
    override var id: String?, // education#higherEducation#3fea53a4-0432-4910-ac9c-69ah8da3c37f
    override var issuer: String?, // did:ebsi:2757945549477fc571663bee12042873fe555b674bd294a3
    @Json(serializeNull = false) override var issued: String? = null, // 2019-06-22T14:11:44Z
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null, // 2019-06-22T14:11:44Z
    @Json(serializeNull = false) override var credentialSubject: VerifiableAttestationSubject? = null,
    @Json(serializeNull = false) var credentialStatus: CredentialStatus? = null,
    @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) var evidence: List<Evidence>? = null,
    @Json(serializeNull = false) override var proof: Proof? = null
) : AbstractVerifiableCredential<VerifiableAttestation.VerifiableAttestationSubject>(type) {
    data class VerifiableAttestationSubject(
        override var id: String? // id123
    ) : CredentialSubject()

    data class Evidence(
        var id: String, // https://essif.europa.eu/tsr-va/evidence/f2aeec97-fc0d-42bf-8ca7-0548192d5678
        var type: List<String>,
        var verifier: String, // did:ebsi:2962fb784df61baa267c8132497539f8c674b37c1244a7a
        var evidenceDocument: String, // Passport
        var subjectPresence: String, // Physical
        var documentPresence: String // Physical
    )

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableAttestation"),
        template = {
            VerifiableAttestation(
                context = listOf(
                    "https://www.w3.org/2018/credentials/v1",
                ),
                id = "education#higherEducation#3fea53a4-0432-4910-ac9c-69ah8da3c37f",
                issuer = "did:ebsi:2757945549477fc571663bee12042873fe555b674bd294a3",
                issued = "2019-06-22T14:11:44Z",
                expirationDate = "2022-06-22T14:11:44Z",
                validFrom = "2019-06-22T14:11:44Z",
                credentialSubject = VerifiableAttestationSubject(
                    id = "id123"
                ),
                credentialStatus = CredentialStatus(
                    id = "https://essif.europa.eu/status/identity#verifiableID#1dee355d-0432-4910-ac9c-70d89e8d674e",
                    type = "CredentialStatusList2020"
                ),
                credentialSchema = CredentialSchema(
                    id = "https://raw.githubusercontent.com/walt-id/waltid-ssikit-vclib/master/src/test/resources/schemas/VerifiableAttestation.json",
                    type = "JsonSchemaValidator2018"
                ),
                evidence = listOf(
                    Evidence(
                        id = "https://essif.europa.eu/tsr-va/evidence/f2aeec97-fc0d-42bf-8ca7-0548192d5678",
                        type = listOf("DocumentVerification"),
                        verifier = "did:ebsi:2962fb784df61baa267c8132497539f8c674b37c1244a7a",
                        evidenceDocument = "Passport",
                        subjectPresence = "Physical",
                        documentPresence = "Physical"
                    )
                )
            )
        }
    )
}

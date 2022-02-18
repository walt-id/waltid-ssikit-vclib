package id.walt.vclib.credentials

import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class ProofOfResidence(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String>,
    override var id: String?,
    override var issuer: String?,
    var title: String = "Proof of Residence",
    @Json(serializeNull = false) override var issued: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var credentialSubject: ProofOfResidenceSubject? = null,
    @Json(serializeNull = false) var credentialStatus: CredentialStatus? = null,
    @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) var evidence: List<Evidence>? = null,
    @Json(serializeNull = false) override var proof: Proof? = null
) : AbstractVerifiableCredential<ProofOfResidence.ProofOfResidenceSubject>(type) {
    data class ProofOfResidenceSubject(
        @Json(serializeNull = false) override var id: String? = null,
        @Json(serializeNull = false) var familyName: String? = null,
        @Json(serializeNull = false) var firstNames: String? = null,
        @Json(serializeNull = false) var gender: String? = null,
        @Json(serializeNull = false) var dateOfBirth: String? = null,
        val address: Address,
        @Json(serializeNull = false) var familyStatus: String? = null,
        @Json(serializeNull = false) var identificationNumber: String? = null,
        @Json(serializeNull = false) var nationality: String? = null,
    ) : CredentialSubject()

    data class Evidence(
        var id: String,
        var type: List<String>,
        var verifier: String,
        var evidenceDocument: String, // Passport
        var subjectPresence: String, // Physical
        var documentPresence: String // Physical
    )

    data class Address(
        var streetAddress: String? = null,
        var postalCode: String? = null,
        var locality: String? = null,
        var countryName: String? = null,
    )

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableAttestation", "ProofOfResidence"),
        template = {
            ProofOfResidence(
                context = listOf(
                    "https://www.w3.org/2018/credentials/v1",
                ),
                id = "residence#3fea53a4-0432-4910-ac9c-69ah8da3c37f",
                issuer = "did:ebsi:2757945549477fc571663bee12042873fe555b674bd294a3",
                issued = "2019-06-22T14:11:44Z",
                expirationDate = "2022-06-22T14:11:44Z",
                validFrom = "2019-06-22T14:11:44Z",
                credentialSubject = ProofOfResidenceSubject(
                    id = "id123",
                    familyName = "Beron",
                    firstNames = "Domink",
                    gender = "Male",
                    dateOfBirth = "1993-04-08",
                    familyStatus = "Single",
                    address = Address(
                        streetAddress = "16 Route D' Arlon",
                        postalCode = "L-8410",
                        locality = "Steinfort",
                        countryName = "LU"
                    ),
                    identificationNumber = "123456789",
                    nationality = "AT"
                ),
                credentialStatus = CredentialStatus(
                    id = "https://essif.europa.eu/status/identity#verifiableID#1dee355d-0432-4910-ac9c-70d89e8d674e",
                    type = "CredentialStatusList2020"
                ),
                credentialSchema = CredentialSchema(
                    id = "https://raw.githubusercontent.com/walt-id/waltid-ssikit-vclib/master/src/test/resources/schemas/ProofOfResidence.json",
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

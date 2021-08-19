package id.walt.vclib.vclist

import com.beust.klaxon.Json
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.model.CredentialStatus
import id.walt.vclib.model.Proof
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata

data class VerifiableID(
    @Json(name = "@context")
    var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) var id: String? = null, // identity#verifiableID#51e42fda-cb0a-4333-b6a6-35cb147e1a88
    @Json(serializeNull = false) var issuer: String? = null, // did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9
    @Json(serializeNull = false) var issuanceDate: String? = null, // 2020-11-03T00:00:00Z
    @Json(serializeNull = false) var validFrom: String? = null, // 2020-11-03T00:00:00Z
    @Json(serializeNull = false) var expirationDate: String? = null,
    @Json(serializeNull = false) var credentialSubject: CredentialSubject? = null,
    @Json(serializeNull = false) var credentialStatus: CredentialStatus? = null,
    @Json(serializeNull = false) var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) var evidence: Evidence,
    @Json(serializeNull = false) var proof: Proof? = null
) : VerifiableCredential(type) {
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableID"),
        template = {
            VerifiableID(
                id = "identity#verifiableID#51e42fda-cb0a-4333-b6a6-35cb147e1a88",
                issuer = "did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9",
                issuanceDate = "2020-11-03T00:00:00Z",
                validFrom = "2020-11-03T00:00:00Z",
                credentialSubject = CredentialSubject(
                    id = "did:ebsi:22AhtW7XMssv7es4YcQTdV2MCM3c8b1VsiBfi5weHsjcCY9o",
                    familyName = "DOE",
                    firstName = "Jane",
                    dateOfBirth = "1993-04-08",
                    personalIdentifier = "0904008084H",
                    nameAndFamilyNameAtBirth = "Jane DOE",
                    placeOfBirth = "LILLE, FRANCE",
                    currentAddress = "1 Boulevard de la Liberté, 59800 Lille",
                    gender = "FEMALE"
                ),
                credentialStatus = CredentialStatus(
                    id = "https://essif.europa.eu/status/identity#verifiableID#51e42fda-cb0a-4333-b6a6-35cb147e1a88",
                    type = "CredentialsStatusList2020"
                ),
                credentialSchema = CredentialSchema(
                    id = "https://api.preprod.ebsi.eu/trusted-schemas-registry/v1/schemas/0x312e332e362e312e342e312e3934382e342e3136392e322e312e322e3435",
                    type = "JsonSchemaValidator2018"
                ),
                evidence = Evidence(
                    id = "https://essif.europa.eu/tsr-vid/evidence/f2aeec97-fc0d-42bf-8ca7-0548192d4231",
                    type = listOf("DocumentVerification"),
                    verifier = "did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9",
                    evidenceDocument = listOf("Passport"),
                    subjectPresence = "Physical",
                    documentPresence = listOf("Physical")
                )
            )
        }
    );

    data class Evidence(
        val id: String,
        val type: List<String?>,
        val verifier: String,
        val evidenceDocument: List<String?>,
        val subjectPresence: String,
        val documentPresence: List<String?>
    )

    data class CredentialSubject(
        @Json(serializeNull = false) var id: String? = null, // did:ebsi:22AhtW7XMssv7es4YcQTdV2MCM3c8b1VsiBfi5weHsjcCY9o
        @Json(serializeNull = false) var familyName: String? = null, // DOE
        @Json(serializeNull = false) var firstName: String? = null, // Jane
        @Json(serializeNull = false) var dateOfBirth: String? = null, // 1993-04-08
        @Json(serializeNull = false) var personalIdentifier: String? = null, // 0904008084H
        @Json(serializeNull = false) var nameAndFamilyNameAtBirth: String? = null, // Jane DOE
        @Json(serializeNull = false) var placeOfBirth: String? = null, // LILLE, FRANCE
        @Json(serializeNull = false) var currentAddress: String? = null, // 1 Boulevard de la Liberté, 59800 Lille
        @Json(serializeNull = false) var gender: String? = null, // FEMALE
    )
}
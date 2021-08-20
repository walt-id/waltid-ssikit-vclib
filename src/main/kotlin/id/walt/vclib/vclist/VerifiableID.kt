package id.walt.vclib.vclist

import com.beust.klaxon.Json
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.model.CredentialStatus
import id.walt.vclib.model.Proof
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.DateTimeFormat
import id.walt.vclib.schema.SchemaService.Nullable
import id.walt.vclib.schema.SchemaService.PropertyName

data class VerifiableID(
    @Json(name = "@context") @field:PropertyName(name = "@context") var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    var id: String? = null, // identity#verifiableID#51e42fda-cb0a-4333-b6a6-35cb147e1a88
    var issuer: String? = null, // did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9
    @field:DateTimeFormat var issuanceDate: String? = null, // 2020-11-03T00:00:00Z
    @field:DateTimeFormat var validFrom: String? = null, // 2020-11-03T00:00:00Z
    @field:DateTimeFormat @field:Nullable @Json(serializeNull = false) var expirationDate: String? = null,
    var credentialSubject: CredentialSubject? = null,
    var credentialStatus: CredentialStatus? = null,
    var credentialSchema: CredentialSchema? = null,
    var evidence: Evidence,
    var proof: Proof? = null
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
                    dateOfBirth = "1993-04-08T00:00:00Z",
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
        var id: String? = null, // did:ebsi:22AhtW7XMssv7es4YcQTdV2MCM3c8b1VsiBfi5weHsjcCY9o
        var familyName: String? = null, // DOE
        var firstName: String? = null, // Jane
        @field:DateTimeFormat var dateOfBirth: String? = null, // 1993-04-08T00:00:00Z
        var personalIdentifier: String? = null, // 0904008084H
        @field:Nullable @Json(serializeNull = false) var nameAndFamilyNameAtBirth: String? = null, // Jane DOE
        @field:Nullable @Json(serializeNull = false) var placeOfBirth: String? = null, // LILLE, FRANCE
        @field:Nullable @Json(serializeNull = false) var currentAddress: String? = null, // 1 Boulevard de la Liberté, 59800 Lille
        @field:Nullable @Json(serializeNull = false) var gender: String? = null, // FEMALE
    )
}
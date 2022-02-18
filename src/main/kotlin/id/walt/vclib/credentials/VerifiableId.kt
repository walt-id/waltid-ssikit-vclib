package id.walt.vclib.credentials

import com.beust.klaxon.Json
import com.nimbusds.jwt.SignedJWT
import id.walt.vclib.adapter.ListOrReadSingleValue
import id.walt.vclib.adapter.ListOrSingleValue
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.JsonIgnore
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class VerifiableId(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) override var id: String? = null,
    @Json(serializeNull = false) override var issuer: String? = null,
    @Json(serializeNull = false) override var issued: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    override var credentialSubject: VerifiableIdSubject? = null,
    @Json(serializeNull = false) var credentialStatus: CredentialStatus? = null,
    override var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) var evidence: List<Evidence>? = null,
    @Json(serializeNull = false) override var proof: Proof? = null
) : AbstractVerifiableCredential<VerifiableId.VerifiableIdSubject>(type) {
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableAttestation", "VerifiableId"),
        template = {
            VerifiableId(
                id = "urn:uuid:3add94f4-28ec-42a1-8704-4e4aa51006b4",
                issuer = "did:ebsi:2A9BZ9SUe6BatacSpvs1V5CdjHvLpQ7bEsi2Jb6LdHKnQxaN",
                issued = "2021-08-31T00:00:00Z",
                validFrom = "2021-08-31T00:00:00Z",
                credentialSubject = VerifiableIdSubject(
                    id = "did:ebsi:2AEMAqXWKYMu1JHPAgGcga4dxu7ThgfgN95VyJBJGZbSJUtp",
                    familyName = "DOE",
                    firstName = "Jane",
                    dateOfBirth = "1993-04-08",
                    personalIdentifier = "0904008084H",
                    nameAndFamilyNameAtBirth = "Jane DOE",
                    placeOfBirth = "LILLE, FRANCE",
                    currentAddress = listOf("1 Boulevard de la Liberté, 59800 Lille"),
                    gender = "FEMALE"
                ),
                //  EBSI does not support credentialStatus yet
                //  credentialStatus = CredentialStatus(
                //      id = "https://api.preprod.ebsi.eu/status/identity#verifiableID#51e42fda-cb0a-4333-b6a6-35cb147e1a88",
                //      type = "CredentialsStatusList2020"
                //  ),
                credentialSchema = CredentialSchema(
                    id = "https://api.preprod.ebsi.eu/trusted-schemas-registry/v1/schemas/0xb77f8516a965631b4f197ad54c65a9e2f9936ebfb76bae4906d33744dbcc60ba",
                    type = "FullJsonSchemaValidator2021"
                ),
                evidence = listOf(Evidence(
                    // EBSI does not support evidence id yet
                    // id = "https://leaston.bcdiploma.com/law-economics-management#V_ID_evidence",
                    type = listOf("DocumentVerification"),
                    verifier = "did:ebsi:2A9BZ9SUe6BatacSpvs1V5CdjHvLpQ7bEsi2Jb6LdHKnQxaN",
                    evidenceDocument = listOf("Passport"),
                    subjectPresence = "Physical",
                    documentPresence = listOf("Physical")
                ))
            )
        }
    )

    data class VerifiableIdSubject(
        @Json(serializeNull = false) override var id: String? = null,
        @Json(serializeNull = false) var identifier: List<Identifier>? = null,
        var familyName: String? = null,
        var firstName: String? = null,
        var dateOfBirth: String? = null,
        @Json(serializeNull = false) var personalIdentifier: String? = null,
        @Json(serializeNull = false) var nameAndFamilyNameAtBirth: String? = null,
        @Json(serializeNull = false) var placeOfBirth: String? = null,
        @Json(serializeNull = false) var currentAddress: List<String>? = null,
        @Json(serializeNull = false) var gender: String? = null,
    ) : CredentialSubject() {

        data class Identifier(
            var schemeID: String,
            var value: String
        )
    }

    data class Evidence(
        @Json(serializeNull = false) var id: String? = null,
        var type: List<String?>,
        var verifier: String,
        var evidenceDocument: List<String?>,
        var subjectPresence: String,
        var documentPresence: List<String?>
    )

    override fun newId(id: String) = "urn:uuid:${id}"
}

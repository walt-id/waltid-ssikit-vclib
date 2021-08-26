package id.walt.vclib.vclist

import com.beust.klaxon.Json
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.model.CredentialStatus
import id.walt.vclib.model.Proof
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata

data class VerifiableId(
    @Json(name = "@context") var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) var id: String? = null,
    @Json(serializeNull = false) var issuer: String? = null,
    @Json(serializeNull = false) var issuanceDate: String? = null,
    var validFrom: String? = null,
    @Json(serializeNull = false) var expirationDate: String? = null,
    var credentialSubject: CredentialSubject? = null,
    @Json(serializeNull = false) var credentialStatus: CredentialStatus? = null,
    var credentialSchema: CredentialSchema? = null,
    var evidence: Evidence? = null,
    @Json(serializeNull = false) var proof: Proof? = null
) : VerifiableCredential(type) {
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableAttestation", "VerifiableId"),
        template = {
            VerifiableId(
                credentialSchema = CredentialSchema(
                    id = "https://api.preprod.ebsi.eu/trusted-schemas-registry/v1/schemas/0x2488fd38783d65e4fd46e7889eb113743334dbc772b05df382b8eadce763101b",
                    type = "JsonSchemaValidator2018"
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
        @Json(serializeNull = false) var id: String? = null,
        var familyName: String? = null,
        var firstName: String? = null,
        var dateOfBirth: String? = null,
        var personalIdentifier: String? = null,
        @Json(serializeNull = false) var nameAndFamilyNameAtBirth: String? = null,
        @Json(serializeNull = false) var placeOfBirth: String? = null,
        @Json(serializeNull = false) var currentAddress: String? = null,
        @Json(serializeNull = false) var gender: String? = null,
    )
}
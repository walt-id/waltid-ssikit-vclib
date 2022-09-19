package id.walt.vclib.credentials.gaiax.n


import com.beust.klaxon.Json
import id.walt.vclib.model.AbstractVerifiableCredential
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.model.CredentialSubject
import id.walt.vclib.model.Proof
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ParticipantCredential(
    @SerialName("@context") @Json(name = "@context") @field:SchemaService.PropertyName(name = "@context") @field:SchemaService.Required
    val context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),

    @Json(serializeNull = false) override var issued: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,

    @Json(serializeNull = false) override var credentialSubject: ParticipantCredentialSubject? = null,
    @Json(serializeNull = false) override var id: String? = null, // https://catalogue.gaia-x.eu/credentials/ParticipantCredential/1663271448939
    @Json(serializeNull = false) override var issuanceDate: String? = null, // 2022-09-15T19:50:48.939Z
    @Json(serializeNull = false) override var issuer: String? = null, // did:web:compliance.lab.gaia-x.eu
    @Json(serializeNull = false) override var proof: Proof? = null
) : AbstractVerifiableCredential<ParticipantCredential.ParticipantCredentialSubject>(type) {
    @Serializable
    data class ParticipantCredentialSubject(
        @Json(serializeNull = false) override var id: String? = null, // did:web:delta-dao.com
        @Json(serializeNull = false) var hash: String, // 5bf0e1921de342ae8c9a7f3d0c274386a8d7f6497d03d99269d445fb20a3922f
    ) : CredentialSubject()

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "ParticipantCredential"),
        template = {
            ParticipantCredential(
                context = listOf("https://www.w3.org/2018/credentials/v1"),
                id = "https://catalogue.gaia-x.eu/credentials/ParticipantCredential/1663271448939",
                issuer = "did:web:compliance.lab.gaia-x.eu",
                issuanceDate = "2022-09-15T19:50:48.939Z",
                credentialSubject = ParticipantCredentialSubject(
                    id = "did:web:delta-dao.com",
                    hash = "5bf0e1921de342ae8c9a7f3d0c274386a8d7f6497d03d99269d445fb20a3922f"
                )
            )
        }
    )
}


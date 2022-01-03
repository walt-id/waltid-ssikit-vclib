package id.walt.vclib.credentials

import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class ParticipantCredential(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf(
        "https://www.w3.org/2018/credentials/v1",
        "https://w3id.org/security/suites/ed25519-2020/v1",
        "https://w3id.org/security/suites/jws-2020/v1"
    ),
    override var id: String?,
    override var issuer: String?,
    @Json(serializeNull = false) override var issuanceDate: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override var credentialSubject: ParticipantCredentialSubject?,
    @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) override var proof: Proof? = null,
) : AbstractVerifiableCredential<ParticipantCredential.ParticipantCredentialSubject>(type) {
    data class ParticipantCredentialSubject(
        override var id: String?,
        var type: String,
        var programName: String,
    ) : CredentialSubject()

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "ParticipantCredential"),
        template = {
            ParticipantCredential(
                id = "vc.gaia-x.eu//membership/first.last@gaia-x.eu",
                issuer = "did:web:vc.gaia-x.eu:issuer",
                issuanceDate = "2022-01-03T20:38:38Z",
                expirationDate = "2022-01-06T20:38:38Z",
                credentialSubject = ParticipantCredentialSubject(
                    id = "mailto:first.last@gaia-x.eu",
                    type = "ProgramMembership",
                    programName = "Gaia-X AISBL"
                )
            )
        }
    )
}

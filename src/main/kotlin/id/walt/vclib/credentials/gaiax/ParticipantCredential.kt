package id.walt.vclib.credentials.gaiax

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
    @Json(serializeNull = false) override var issued: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override var credentialSubject: ParticipantCredentialSubject?,
    @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) var credentialStatus: CredentialStatus? = null,
    @Json(serializeNull = false) override var proof: Proof? = null,
) : AbstractVerifiableCredential<ParticipantCredential.ParticipantCredentialSubject>(type) {

    data class ParticipantCredentialSubject(
        override var id: String?,
        @Json(serializeNull = false) var hasRegistrationNumber: String? = null,
        @Json(serializeNull = false) var hasLegallyBindingName: String? = null,
        @Json(serializeNull = false) var hasJurisdiction: String? = null,
        @Json(serializeNull = false) var hasCountry: String? = null,
        @Json(serializeNull = false) var leiCode: String? = null,
        @Json(serializeNull = false) var ethereumAddress: String? = null,
        @Json(serializeNull = false) var parentOrganisation: String? = null,
        @Json(serializeNull = false) var subOrganisation: String? = null,
        @Json(serializeNull = false) var hash: String? = null
    ) : CredentialSubject()

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "ParticipantCredential"),
        template = {
            ParticipantCredential(
                id = "vc.gaia-x.eu/participant-credential#392ac7f6-399a-437b-a268-4691ead8f176",
                issuer = "did:web:vc.gaia-x.eu:issuer",
                issued = "2022-01-03T20:38:38Z",
                expirationDate = "2022-01-06T20:38:38Z",
                credentialSubject = ParticipantCredentialSubject(
                    id = "did:web:delta-dao.com",
                    hasRegistrationNumber = "DEK1101R.HRB170364",
                    hasLegallyBindingName = "deltaDAO AG",
                    hasJurisdiction = "GER",
                    hasCountry = "GER",
                    leiCode = "391200FJBNU0YW987L26",
                    ethereumAddress = "0x4C84a36fCDb7Bc750294A7f3B5ad5CA8F74C4A52",
                    parentOrganisation = "",
                    subOrganisation = "",
                    hash = "9ecf754ffdad0c6de238f60728a90511780b2f7dbe2f0ea015115515f3f389cd"
                ),
                credentialSchema = CredentialSchema(
                    id = "https://raw.githubusercontent.com/walt-id/waltid-ssikit-vclib/master/src/test/resources/schemas/ParticipantCredential.json",
                    type = "JsonSchemaValidator2018"
                ),
                credentialStatus = CredentialStatus(
                    id = "https://gaiax.europa.eu/status/participant-credential#392ac7f6-399a-437b-a268-4691ead8f176",
                    type = "CredentialStatusList2020"
                ),
            )
        }
    )
}

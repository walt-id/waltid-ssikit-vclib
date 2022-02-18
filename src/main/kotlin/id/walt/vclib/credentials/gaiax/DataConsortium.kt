package id.walt.vclib.credentials.gaiax

import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class DataConsortium(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf(
        "https://www.w3.org/2018/credentials/v1"
    ),
    override var id: String?,
    override var issuer: String?,
    @Json(serializeNull = false) override var issued: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override var credentialSubject: DataConsortiumCredentialSubject?,
    @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) var dataspace: List<Dataspace>? = null,
    @Json(serializeNull = false) override var proof: Proof? = null,
) : AbstractVerifiableCredential<DataConsortium.DataConsortiumCredentialSubject>(type) {
    data class DataConsortiumCredentialSubject(
        override var id: String?
    ) : CredentialSubject()

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "DataConsortiumCredential"),
        template = {
            DataConsortium(
                id = "1234",
                issuer = "did:web:vc.gaia-x.eu:issuer",
                issued = "2022-01-03T20:38:38Z",
                expirationDate = "2022-01-06T20:38:38Z",
                credentialSubject = DataConsortiumCredentialSubject(
                    id = "1234"
                ),
                dataspace = listOf(
                    Dataspace("Gaia-X AISBL", "https://endpoint1.io"),
                    Dataspace("Gaia-X XYZ", "https://endpoint2.io"))
            )
        }
    )

    data class Dataspace(val id: String, val serviceEndpoint: String = "")
}

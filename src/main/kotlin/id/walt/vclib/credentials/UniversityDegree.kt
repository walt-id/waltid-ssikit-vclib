package id.walt.vclib.credentials


import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class UniversityDegree(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf(
        "https://www.w3.org/2018/credentials/v1",
        "https://www.w3.org/2018/credentials/examples/v1"
    ),
    override var id: String?, // http://example.gov/credentials/3732
    @field:SchemaService.PropertyName("issuer") @Json(name = "issuer") var issuerObject: Issuer,
    @Json(serializeNull = false) override var issuanceDate: String?, // 2020-03-10T04:24:12.164Z
    override var credentialSubject: UniversityDegreeSubject?,
    @Json(serializeNull = false) override var proof: Proof? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,

    ) : AbstractVerifiableCredential<UniversityDegree.UniversityDegreeSubject>(type) {

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "UniversityDegreeCredential"),
        template = {
            UniversityDegree(
                id= "http://example.gov/credentials/3732",
                issuerObject = Issuer("did:example:456"),
                issuanceDate = "2020-03-10T04:24:12.164Z",
                credentialSubject = UniversityDegree.UniversityDegreeSubject(
                    id = "did:example:123",
                    UniversityDegreeSubject.Degree (
                        type = "BachelorDegree",
                        name = "Bachelor of Science and Arts"
                    )
                )
            )
        }
    )

    data class Issuer(
        var id: String // did:web:vc.transmute.world
    )

    data class UniversityDegreeSubject(
        override var id: String?, // did:example:ebfeb1f712ebc6f1c276e12ec21
        var degree: Degree
    ) : CredentialSubject() {
        data class Degree(
            var type: String, // BachelorDegree
            var name: String // Bachelor of Science and Arts
        )
    }

    @SchemaService.JsonIgnore
    @Json(ignored = true)
    override var issuer: String?
        get() = issuerObject.id
        set(value) {
            issuerObject.id = value!!
        }
}

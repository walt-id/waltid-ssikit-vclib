package id.walt.vclib.credentials

import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class GaiaxSelfDescription(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    override var id: String?,
    override var issuer: String?,
    @Json(serializeNull = false) override var issuanceDate: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override var credentialSubject: GaiaxSelfDescriptionSubject?,
    @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) override var proof: Proof? = null,
) : AbstractVerifiableCredential<GaiaxSelfDescription.GaiaxSelfDescriptionSubject>(type) {
    data class GaiaxSelfDescriptionSubject(
        override var id: String?, // "Pilot004AIService"
        var type: String, // "Service"
        var hasName: String, // "AIS"
        var description: String, // "AIS demonstrates machine learning application use case."
        var hasVersion: String, // "0.1.0"
        var providedBy: String, // "GAIA-X"
        var hasMarketingImage: String, // https:...
        @Json(serializeNull = false) var hasCertifications: List<String>? = null,
        @Json(serializeNull = false) var utilizes: List<String>? = null,
        @Json(serializeNull = false) var dependsOn: List<String>? = null,
    ) : CredentialSubject()

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "GaiaxSelfDescription"),
        template = {
            GaiaxSelfDescription(
                id = "did:ebsi-eth:00000001/credentials/1872",
                issuer = "did:example:456",
                issuanceDate = "2020-08-24T14:13:44Z",
                credentialSubject = GaiaxSelfDescriptionSubject(
                    id = "Pilot004AIService",
                    type = "Service",
                    hasName = "AIS",
                    description = "AIS demonstrates machine learning application use case.",
                    hasVersion = "0.1.0",
                    providedBy = "GAIA-X",
                    hasMarketingImage = "https://www.data-infrastructure.eu/GAIAX/Redaktion/EN/Bilder/UseCases/ai-marketplace-for-product-development.jpg?__blob=normal",
                    hasCertifications = listOf("ISO_27001", "GDPR_Compliance"),
                    utilizes = listOf("ExampleKubernetesService"),
                    dependsOn = listOf("Pilot004DataService"),
                )
            )
        }
    )
}

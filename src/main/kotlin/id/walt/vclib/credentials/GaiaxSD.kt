package id.walt.vclib.credentials

import com.beust.klaxon.Json
import id.walt.vclib.model.Proof
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class GaiaxSD(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    override var id: String?,
    var issuer: String,
    @Json(serializeNull = false) var issuanceDate: String? = null,
    @Json(serializeNull = false) var validFrom: String? = null,
    @Json(serializeNull = false) var expirationDate: String? = null,
    @Json(serializeNull = false) var credentialSubject: CustomCredentialSubject,
    @Json(serializeNull = false) var proof: Proof? = null,
) : VerifiableCredential(type) {
    data class CustomCredentialSubject(
        var id: String, // "Pilot004AIService"
        var type: String, // "Service"
        var hasName: String, // "AIS"
        var description: String, // "AIS demonstrates machine learning application use case."
        var hasVersion: String, // "0.1.0"
        var providedBy: String, // "GAIA-X"
        var hasMarketingImage: String, // https:...
        @Json(serializeNull = false) var hasCertifications: List<String>? = null,
        @Json(serializeNull = false) var utilizes: List<String>? = null,
        @Json(serializeNull = false) var dependsOn: List<String>? = null,
    )

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "GaiaxSD"),
        template = {
            GaiaxSD(
                id = "did:ebsi-eth:00000001/credentials/1872",
                issuer = "did:example:456",
                issuanceDate = "2020-08-24T14:13:44Z",
                credentialSubject = CustomCredentialSubject(
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
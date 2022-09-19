package id.walt.vclib.credentials.gaiax.n


import com.beust.klaxon.Json
import com.fasterxml.jackson.annotation.JsonProperty
import id.walt.vclib.model.AbstractVerifiableCredential
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.model.CredentialSubject
import id.walt.vclib.model.Proof
import id.walt.vclib.registry.VerifiableCredentialMetadata
import kotlinx.serialization.SerialName

data class ServiceOfferingCredential(
    @Json(name = "@context", serializeNull = false) @JsonProperty("@context") @SerialName("@context")
    var context: List<String>,

    @Json(serializeNull = false) override var issued: String? = null,
    @Json(serializeNull = false) override var issuer: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,

    @Json(serializeNull = false) override var credentialSubject: ServiceOfferingCredentialSubject?,
    @Json(serializeNull = false) override var id: String?, // https://compliance.gaia-x.eu/.well-known/serviceComplianceService.json

    @Json(serializeNull = false) override var proof: Proof? = null
) : AbstractVerifiableCredential<ServiceOfferingCredential.ServiceOfferingCredentialSubject>(type) {
    data class ServiceOfferingCredentialSubject(
        @Json(
            name = "gx-service-offering:dataExport",
            serializeNull = false
        ) @JsonProperty("gx-service-offering:dataExport") @SerialName("gx-service-offering:dataExport")
        var gxServiceOfferingDataExport: List<GxServiceOfferingDataExport>?,
        @Json(
            name = "gx-service-offering:dataProtectionRegime",
            serializeNull = false
        ) @JsonProperty("gx-service-offering:dataProtectionRegime") @SerialName("gx-service-offering:dataProtectionRegime")
        var gxServiceOfferingDataProtectionRegime: List<String>?,
        @Json(
            name = "gx-service-offering:dependsOn",
            serializeNull = false
        ) @JsonProperty("gx-service-offering:dependsOn") @SerialName("gx-service-offering:dependsOn")
        var gxServiceOfferingDependsOn: List<String>?,
        @Json(
            name = "gx-service-offering:description",
            serializeNull = false
        ) @JsonProperty("gx-service-offering:description") @SerialName("gx-service-offering:description")
        var gxServiceOfferingDescription: String?, // The Compliance Service will validate the shape and content of Self Descriptions. Required fields and consistency rules are defined in the Gaia-X Trust Framework.
        @Json(
            name = "gx-service-offering:gdpr",
            serializeNull = false
        ) @JsonProperty("gx-service-offering:gdpr") @SerialName("gx-service-offering:gdpr")
        var gxServiceOfferingGdpr: List<GxServiceOfferingGdpr>?,
        @Json(
            name = "gx-service-offering:name",
            serializeNull = false
        ) @JsonProperty("gx-service-offering:name") @SerialName("gx-service-offering:name")
        var gxServiceOfferingName: String?, // Gaia-X Lab Compliance Service
        @Json(
            name = "gx-service-offering:providedBy",
            serializeNull = false
        ) @JsonProperty("gx-service-offering:providedBy") @SerialName("gx-service-offering:providedBy")
        var gxServiceOfferingProvidedBy: String?, // https://compliance.gaia-x.eu/.well-known/participant.json
        @Json(
            name = "gx-service-offering:termsAndConditions",
            serializeNull = false
        ) @JsonProperty("gx-service-offering:termsAndConditions") @SerialName("gx-service-offering:termsAndConditions")
        var gxServiceOfferingTermsAndConditions: List<GxServiceOfferingTermsAndCondition>?,
        @Json(
            name = "gx-service-offering:webAddress",
            serializeNull = false
        ) @JsonProperty("gx-service-offering:webAddress") @SerialName("gx-service-offering:webAddress")
        var gxServiceOfferingWebAddress: String?, // https://compliance.gaia-x.eu/
        override var id: String? // https://compliance.gaia-x.eu/.well-known/serviceComplianceService.json
    ) : CredentialSubject() {
        data class GxServiceOfferingDataExport(
            @Json(
                name = "gx-service-offering:accessType",
                serializeNull = false
            ) @JsonProperty("gx-service-offering:accessType") @SerialName("gx-service-offering:accessType")
            var gxServiceOfferingAccessType: String?, // digital
            @Json(
                name = "gx-service-offering:formatType",
                serializeNull = false
            ) @JsonProperty("gx-service-offering:formatType") @SerialName("gx-service-offering:formatType")
            var gxServiceOfferingFormatType: String?, // mime/png
            @Json(
                name = "gx-service-offering:requestType",
                serializeNull = false
            ) @JsonProperty("gx-service-offering:requestType") @SerialName("gx-service-offering:requestType")
            var gxServiceOfferingRequestType: String? // emails
        )

        data class GxServiceOfferingGdpr(
            @Json(
                name = "gx-service-offering:imprint",
                serializeNull = false
            ) @JsonProperty("gx-service-offering:imprint") @SerialName("gx-service-offering:imprint")
            var gxServiceOfferingImprint: String?, // https://gaia-x.eu/imprint/
            @Json(
                name = "gx-service-offering:privacyPolicy",
                serializeNull = false
            ) @JsonProperty("gx-service-offering:privacyPolicy") @SerialName("gx-service-offering:privacyPolicy")
            var gxServiceOfferingPrivacyPolicy: String? // https://gaia-x.eu/privacy-policy/
        )

        data class GxServiceOfferingTermsAndCondition(
            @Json(
                name = "gx-service-offering:hash",
                serializeNull = false
            ) @JsonProperty("gx-service-offering:hash") @SerialName("gx-service-offering:hash")
            var gxServiceOfferingHash: String?, // myrandomhash
            @Json(name = "gx-service-offering:url", serializeNull = false) @JsonProperty("gx-service-offering:url") @SerialName(
                "gx-service-offering:url"
            )
            var gxServiceOfferingUrl: String? // https://compliance.gaia-x.eu/terms
        )
    }

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "ServiceOfferingExperimental"),
        template = {
            ServiceOfferingCredential(
                // Start

                context = listOf(
                    "http://www.w3.org/ns/shacl#",
                    "http://www.w3.org/2001/XMLSchema#",
                    "https://registry.gaia-x.eu/api/v2206/shape/files?file=resource&type=ttl#",
                    "https://registry.gaia-x.eu/api/v2206/shape/files?file=participant&type=ttl#",
                    "https://registry.gaia-x.eu/api/v2206/shape/files?file=service-offering&type=ttl#"
                ),
                id = "https://compliance.gaia-x.eu/.well-known/serviceComplianceService.json",
                credentialSubject = ServiceOfferingCredentialSubject(
                    id = "https://compliance.gaia-x.eu/.well-known/serviceComplianceService.json",
                    gxServiceOfferingProvidedBy = "https://compliance.gaia-x.eu/.well-known/participant.json",
                    gxServiceOfferingName = "Gaia-X Lab Compliance Service",
                    gxServiceOfferingDescription = "The Compliance Service will validate the shape and content of Self Descriptions. Required fields and consistency rules are defined in the Gaia-X Trust Framework.",
                    gxServiceOfferingWebAddress = "https://compliance.gaia-x.eu/",
                    gxServiceOfferingTermsAndConditions = listOf(
                        ServiceOfferingCredentialSubject.GxServiceOfferingTermsAndCondition(
                            gxServiceOfferingUrl = "https://compliance.gaia-x.eu/terms",
                            gxServiceOfferingHash = "myrandomhash"
                        )
                    ),
                    gxServiceOfferingGdpr = listOf(
                        ServiceOfferingCredentialSubject.GxServiceOfferingGdpr(
                            gxServiceOfferingImprint = "https://gaia-x.eu/imprint/",
                            gxServiceOfferingPrivacyPolicy = "https://gaia-x.eu/privacy-policy/"
                        )
                    ),
                    gxServiceOfferingDataProtectionRegime = listOf(
                        "GDPR2016"
                    ),
                    gxServiceOfferingDataExport = listOf(
                        ServiceOfferingCredentialSubject.GxServiceOfferingDataExport(
                            gxServiceOfferingRequestType = "emails",
                            gxServiceOfferingAccessType = "digital",
                            gxServiceOfferingFormatType = "mime/png"
                        )
                    ),
                    gxServiceOfferingDependsOn = listOf(
                        "https://compliance.gaia-x.eu/.well-known/serviceManagedPostgreSQLOVH.json",
                        "https://compliance.gaia-x.eu/.well-known/serviceManagedK8sOVH.json"
                    )
                )
            )
        }
    )
}

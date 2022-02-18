package id.walt.vclib.credentials.gaiax

import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class DataServiceOffering(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    override var id: String?,

    val serviceTitle: String,
    val version: String,
    val providedBy: String,
    val keywords: List<String>,
    val webAddress: String,
    val provisionType: String,
    val serviceModel: String,
    val modified: String,
    val description: String,
    val billing: Billing,
    override var issuer: String?,
    @Json(serializeNull = false) override var issued: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override var credentialSubject: DataServiceOfferingSubject?,
    @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) override var proof: Proof? = null,
) : AbstractVerifiableCredential<DataServiceOffering.DataServiceOfferingSubject>(type) {
    data class DataServiceOfferingSubject(
        override var id: String?,
        var type: String,
        var hasName: String,
        var description: String,
        var hasVersion: String,
        var providedBy: String,
        var hasMarketingImage: String,
        @Json(serializeNull = false) var hasCertifications: List<String>? = null,
        @Json(serializeNull = false) var utilizes: List<String>? = null,
        @Json(serializeNull = false) var dependsOn: List<String>? = null,
    ) : CredentialSubject()

    data class Billing(
        var paymentModel: PaymentModel, // "PayPerUse"
        var price: String,
        var unit: String,
    ) {
        data class PaymentModel(
            var value: String,
            var unit: String,
            var frequenceOfPayment: String
        )
    }

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "DataServiceOffering"),
        template = {
            DataServiceOffering(
                id = "did:ebsi-eth:00000001/credentials/1872",
                serviceTitle = "safeFBDC - Dataset A for safeFBDC AML analysis",
                version = "0.1",
                providedBy = "did:key:z6Mktqg13w3KRBdcqhyjU99ZhHF8FR6tRJACuQzmVcXNiH25",
                keywords = listOf("Analytics", "AML", "safeFBDC", "finance", "dataset", "graph"),
                webAddress = "https://safefbdc.portal.minimal-gaia-x.eu/asset/did:op:348e1a938dB25221031Ed6b465782efCAcBB214E",
                provisionType = "E.g., Hybrid",
                serviceModel = "E.g., DaaS",
                modified = "2020-12-02T23:00:00+01:00",
                description = "he data contains the basic information to build a transaction graph for later analysis by the safeFBDC algorithm. Is is one of multiple data sources to be analyzed.",
                billing = Billing(
                    paymentModel = Billing.PaymentModel(
                        value = "0.0",
                        unit = "EUR",
                        frequenceOfPayment = "daily"
                    ),
                    price = "0.0",
                    unit = "EUR"
                ),
                issuer = "did:example:456",
                issued = "2020-08-24T14:13:44Z",
                credentialSubject = DataServiceOfferingSubject(
                    id = "Pilot004AIService",
                    type = "Service",
                    hasName = "AIS",
                    description = "AIS demonstrates machine learning application use case.",
                    hasVersion = "0.1.0",
                    providedBy = "GAIA-X",
                    hasMarketingImage = "https://www.data-infrastructure.eu/Data/Redaktion/EN/Bilder/UseCases/ai-marketplace-for-product-development.jpg?__blob=normal",
                    hasCertifications = listOf("ISO_27001", "GDPR_Compliance"),
                    utilizes = listOf("ExampleKubernetesService"),
                    dependsOn = listOf("Pilot004DataService"),
                )
            )
        }
    )
}

package id.walt.vclib.credentials.gaiax

import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class KybCredential(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    override var id: String?,
    override var issuer: String?,
    @Json(serializeNull = false) override var issued: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override var credentialSubject: KybCredentialSubject?,
    @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) override var proof: Proof? = null,
) : AbstractVerifiableCredential<KybCredential.KybCredentialSubject>(type) {
    data class KybCredentialSubject(
        override var id: String?, // did:key
        var legallyBindingName: String, // deltaDAO AG
        var brandName: String, // deltaDAO
        var legallyBindingAddress: LegallyBindingAddress,
        var webAddress: String,
        var corporateEmailAddress: String, // contact@delta-dao.com
        var individualContactLegal: String, // legal@delta-dao.com
        var individualContactTechnical: String, // support@delta-dao.com
        var legalForm: String, // Stock Company
        var jurisdiction: String, // Germany
        var commercialRegister: CommercialRegister,
        var legalRegistrationNumber: String // HRB 170364
    ) : CredentialSubject() {
        data class LegallyBindingAddress(
            var streetAddress: String, // Geibelstr. 46B
            var postalCode: String, // 22303
            var locality: String, // Hamburg
            var countryName: String // Germany
        )

        data class CommercialRegister(
            var organizationName: String, // Amtsgericht Hamburg (-Mitte)
            var organizationUnit: String, // Registergericht
            var streetAddress: String, // Caffamacherreihe 20
            var postalCode: String, // 20355
            var locality: String, // Hamburg
            var countryName: String // Germany
        )
    }

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableAttestation", "KybCredential"),
        template = {
            KybCredential(
                id = "did:ebsi-eth:00000001/credentials/1872",
                issuer = "did:example:456",
                issued = "2020-08-24T14:13:44Z",
                credentialSubject = KybCredentialSubject(
                    id = "did:key:dummy",
                    legallyBindingName = "deltaDAO AG",
                    brandName = "deltaDAO",
                    webAddress = "https://www.delta-dao.com/",
                    legallyBindingAddress = KybCredentialSubject.LegallyBindingAddress(
                        streetAddress = "Geibelstr. 46B",
                        postalCode = "22303",
                        locality = "Hamburg",
                        countryName = "Germany"
                    ),
                    corporateEmailAddress = "contact@delta-dao.com",
                    individualContactLegal = "legal@delta-dao.com",
                    individualContactTechnical = "support@delta-dao.com",
                    legalForm = "Stock Company",
                    jurisdiction = "Germany",
                    commercialRegister = KybCredentialSubject.CommercialRegister(
                        organizationName = "Amtsgericht Hamburg (-Mitte)",
                        organizationUnit = "Registergericht",
                        streetAddress = "Caffamacherreihe 20",
                        postalCode = "20355",
                        locality = "Hamburg",
                        countryName = "Germany"
                    ),
                    legalRegistrationNumber = "HRB 170364"
                )
            )
        }
    )
}

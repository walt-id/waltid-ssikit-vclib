package id.walt.vclib.credentials.gaiax

import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class Iso27001Certificate(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    override var id: String?,
    override var issuer: String?,
    @Json(serializeNull = false) override var issued: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override var credentialSubject: Iso27001CertificateSubject?,
    @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) var iso2700Criteria: Iso2700Criteria? = null,
    @Json(serializeNull = false) override var proof: Proof? = null,
) : AbstractVerifiableCredential<Iso27001Certificate.Iso27001CertificateSubject>(type) {
    data class Iso27001CertificateSubject(
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
        var legalRegistrationNumber: String // HRB 170364
    ) : CredentialSubject() {
        data class LegallyBindingAddress(
            var streetAddress: String, // Geibelstr. 46B
            var postalCode: String, // 22303
            var locality: String, // Hamburg
            var countryName: String // Germany
        )
    }

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableAttestation", "Iso27001Certificate"),
        template = {
            Iso27001Certificate(
                id = "did:ebsi-eth:00000001/credentials/1872",
                issuer = "did:example:456",
                issued = "2020-08-24T14:13:44Z",
                credentialSubject = Iso27001CertificateSubject(
                    id = "did:key:dummy",
                    legallyBindingName = "deltaDAO AG",
                    brandName = "deltaDAO",
                    webAddress = "https://www.delta-dao.com/",
                    legallyBindingAddress = Iso27001CertificateSubject.LegallyBindingAddress(
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
                    legalRegistrationNumber = "HRB 170364"
                ),
                iso2700Criteria = Iso2700Criteria(
                    certificateNr = "123",
                    certifcateName = "IOS/IEC 27001:2022",
                    scope = listOf("consulting service", "software development", "IT operations")
                )
            )
        }
    )

    data class Iso2700Criteria(
        var certificateNr: String,
        var certifcateName: String,
        var scope: List<String>, // consulting service, ...
    )
}

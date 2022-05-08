package id.walt.vclib.credentials

import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class AmletCredential(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) override var id: String? = null,
    @Json(serializeNull = false) override var issuer: String? = null,
    @Json(serializeNull = false) override var issued: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    override var credentialSubject: AmletCredentialSubject? = null,
    @Json(serializeNull = false) var credentialStatus: CredentialStatus? = null,
    override var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) override var proof: Proof? = null
) : AbstractVerifiableCredential<AmletCredential.AmletCredentialSubject>(type) {
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "AmletCredential"),
        template = {
            AmletCredential(
                id = "",
                issuer = "did:ebsi:zkttgPyjddU8JqrkQ9aV5MW#6462224517f04e9d84ba1ea09c82e01c",
                issued = "2022-03-21T00:00:00Z",
                expirationDate = "2023-03-21T00:00:00Z",
                validFrom = "2022-03-21T00:00:00Z",
                credentialSubject = AmletCredentialSubject(
                    id = "did:ebsi:zukSNRQ7Umb2TzbRLNkTGX4#3bf09a67ac1348f4bfe35b7c7fc54fba",
                    givenName = "Jane",
                    familyName = "Doe",
                    birthDate = "1995-05-03",
                    birthPlace = "Milan",
                    taxID = "DOEJNA95M43Z330V",
                    execRappr = "LEGAL REPRESENTATIVE",

                    company = Company(
                        name = "Mopso",
                        vatId = "11717750969",
                        foundingDate= "2021-01-01",
                        address= "VLE. BACCHIGLIONE 20, MILANO",
                        email= "info@mopso.it",
                        revenue= "10000",
                        isRiskSector= false,
                        isRecipientOfPublicFunds = true,
                        hasInternationalActivity = true,
                        ultimateBeneficialOwners = listOf(
                            UltimateBeneficialOwner(
                                givenName = "Jane",
                                familyName = "Doe",
                                birthDate = "1995-05-03",
                                taxID = "DOEJNA95M43Z330V",
                                birthPlace = "Milan",
                                citizenship = "Italian",
                                isPep = false,
                                address = "Milan, Italy",
                            )
                        )
                    )
                ),
                credentialSchema = CredentialSchema(
                    id = "https://raw.githubusercontent.com/walt-id/waltid-ssikit-vclib/master/src/test/resources/schemas/AmletCredential.json",
                    type = "JsonSchemaValidator2018"
                ),
            )
        }
    )

    data class AmletCredentialSubject(
        @Json(serializeNull = false) override var id: String? = null,
        var givenName: String? = null,
        var familyName: String? = null,
        var taxID: String? = null,
        var birthDate: String? = null,
        var birthPlace: String? = null,
        var execRappr: String? = null,
        var company: Company? = null
    ) : CredentialSubject()

    data class Company(
        var name: String? = null,
        var vatId: String? = null,
        var foundingDate: String? = null,
        var address: String? = null,
        var email: String? = null,
        var revenue: String? = null,
        var isRiskSector: Boolean? = null,
        var isRecipientOfPublicFunds: Boolean? = null,
        var hasInternationalActivity: Boolean? = null,

        var ultimateBeneficialOwners: List<UltimateBeneficialOwner>
    )

    data class UltimateBeneficialOwner(
        var givenName: String? = null,
        var familyName: String? = null,
        var birthDate: String? = null,
        var taxID: String? = null,
        var birthPlace: String? = null,
        var citizenship: String? = null,
        var isPep: Boolean? = null,
        var address: String? = null,
    )



    override fun newId(id: String) = "amlet#${id}"

}

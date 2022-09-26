package id.walt.vclib.credentials.gaiax.n

import com.beust.klaxon.Json
import id.walt.vclib.model.AbstractVerifiableCredential
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.model.CredentialSubject
import id.walt.vclib.model.Proof
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class LegalPerson(
    @SerialName("@context") @Json(name = "@context") @field:SchemaService.PropertyName(name = "@context") @field:SchemaService.Required
    var context: List<String>,

    @Json(serializeNull = false) override var issued: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,

    @Json(serializeNull = false) override var credentialSubject: LegalPersonCredentialSubject? = null,
    @Json(serializeNull = false) override var id: String? = null, // https://delta-dao.com/.well-known/participant.json
    @Json(serializeNull = false) override var issuanceDate: String? = null, // 2022-09-15T20:05:20.997Z
    @Json(serializeNull = false) override var issuer: String? = null, // did:web:delta-dao.com
    @Json(serializeNull = false) override var proof: Proof? = null,
) : AbstractVerifiableCredential<LegalPerson.LegalPersonCredentialSubject>(LegalPerson.type) {

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "LegalPerson"),
        template = {
            LegalPerson(
                context = listOf(
                    "https://www.w3.org/2018/credentials/v1",
                    //"https://registry.lab.gaia-x.eu/v2206/api/shape"
                    //"http://www.w3.org/ns/shacl#",
                    //"http://www.w3.org/2001/XMLSchema#",
                    //"https://registry.gaia-x.eu/api/v2206/shape/files?file=participant&type=ttl#"
                ),
                id = "https://delta-dao.com/.well-known/participant.json",
                issuer = "did:web:delta-dao.com",

                issued = null,
                validFrom = null,

                issuanceDate = "2022-09-15T20:05:20.997Z",
                credentialSubject = LegalPersonCredentialSubject(
                    id = "did:web:delta-dao.com",
                    gxParticipantLegalName = "deltaDAO AG",
                    gxParticipantRegistrationNumber = LegalPersonCredentialSubject.GxParticipantRegistrationNumber(
                        gxParticipantRegistrationNumberType = "leiCode",
                        gxParticipantRegistrationNumberNumber = "391200FJBNU0YW987L26"
                    ),
                    gxParticipantBlockchainAccountId = "0x4C84a36fCDb7Bc750294A7f3B5ad5CA8F74C4A52",
                    gxParticipantHeadquarterAddress = LegalPersonCredentialSubject.GxParticipantHeadquarterAddress(
                        gxParticipantAddressCountryCode = "DE",
                        gxParticipantAddressCode = "DE-HH",
                        gxParticipantStreetAddress = "Geibelstraße 46b",
                        gxParticipantPostalCode = "22303"
                    ),
                    gxParticipantLegalAddress = LegalPersonCredentialSubject.GxParticipantLegalAddress(
                        gxParticipantAddressCountryCode = "DE",
                        gxParticipantAddressCode = "DE-HH",
                        gxParticipantStreetAddress = "Geibelstraße 46b",
                        gxParticipantPostalCode = "22303"
                    ),
                    gxParticipantTermsAndConditions = "70c1d713215f95191a11d38fe2341faed27d19e083917bc8732ca4fea4976700"
                )
            )
        }
    )

    @Serializable
    data class LegalPersonCredentialSubject(
        @SerialName("gx-participant:blockchainAccountId") @Json(
            name = "gx-participant:blockchainAccountId",
            serializeNull = false
        ) @field:SchemaService.PropertyName("gx-participant:blockchainAccountId")
        var gxParticipantBlockchainAccountId: String? = null, // 0x4C84a36fCDb7Bc750294A7f3B5ad5CA8F74C4A52
        @SerialName("gx-participant:headquarterAddress") @Json(
            name = "gx-participant:headquarterAddress",
            serializeNull = false
        ) @field:SchemaService.PropertyName("gx-participant:headquarterAddress")
        var gxParticipantHeadquarterAddress: GxParticipantHeadquarterAddress? = null,
        @SerialName("gx-participant:legalAddress") @Json(name = "gx-participant:legalAddress", serializeNull = false)
        @field:SchemaService.PropertyName("gx-participant:legalAddress")
        var gxParticipantLegalAddress: GxParticipantLegalAddress? = null,
        @SerialName("gx-participant:legalName") @Json(name = "gx-participant:legalName", serializeNull = false)
        @field:SchemaService.PropertyName("gx-participant:legalName")
        var gxParticipantLegalName: String? = null, // deltaDAO AG
        @SerialName("gx-participant:registrationNumber") @Json(
            name = "gx-participant:registrationNumber",
            serializeNull = false
        )
        @field:SchemaService.PropertyName("gx-participant:registrationNumber")
        var gxParticipantRegistrationNumber: GxParticipantRegistrationNumber? = null,
        @SerialName("gx-participant:termsAndConditions") @Json(
            name = "gx-participant:termsAndConditions",
            serializeNull = false
        )
        @field:SchemaService.PropertyName("gx-participant:termsAndConditions")
        var gxParticipantTermsAndConditions: String? = null, // 70c1d713215f95191a11d38fe2341faed27d19e083917bc8732ca4fea4976700
        @Json(serializeNull = false) override var id: String? = null // did:web:delta-dao.com
    ) : CredentialSubject() {
        @Serializable
        data class GxParticipantHeadquarterAddress(
            @SerialName("gx-participant:addressCode") @Json(name = "gx-participant:addressCode", serializeNull = false)
            @field:SchemaService.PropertyName("gx-participant:addressCode")
            var gxParticipantAddressCode: String? = null, // DE-HH
            @SerialName("gx-participant:addressCountryCode") @Json(
                name = "gx-participant:addressCountryCode",
                serializeNull = false
            )
            @field:SchemaService.PropertyName("gx-participant:addressCountryCode")
            var gxParticipantAddressCountryCode: String? = null, // DE
            @SerialName("gx-participant:postal-code") @Json(name = "gx-participant:postal-code", serializeNull = false)
            @field:SchemaService.PropertyName("gx-participant:postal-code")
            var gxParticipantPostalCode: String? = null, // 22303
            @SerialName("gx-participant:street-address") @Json(name = "gx-participant:street-address", serializeNull = false)
            @field:SchemaService.PropertyName("gx-participant:street-address")
            var gxParticipantStreetAddress: String? = null // Geibelstraße 46b
        )

        @Serializable
        data class GxParticipantLegalAddress(
            @SerialName("gx-participant:addressCode") @Json(name = "gx-participant:addressCode", serializeNull = false)
            @field:SchemaService.PropertyName("gx-participant:addressCode")
            var gxParticipantAddressCode: String? = null, // DE-HH
            @SerialName("gx-participant:addressCountryCode") @Json(
                name = "gx-participant:addressCountryCode",
                serializeNull = false
            )
            @field:SchemaService.PropertyName("gx-participant:addressCountryCode")
            var gxParticipantAddressCountryCode: String? = null, // DE
            @SerialName("gx-participant:postal-code") @Json(name = "gx-participant:postal-code", serializeNull = false)
            @field:SchemaService.PropertyName("gx-participant:postal-code")
            var gxParticipantPostalCode: String? = null, // 22303
            @SerialName("gx-participant:street-address") @Json(name = "gx-participant:street-address", serializeNull = false)
            @field:SchemaService.PropertyName("gx-participant:street-address")
            var gxParticipantStreetAddress: String? = null // Geibelstraße 46b
        )

        @Serializable
        data class GxParticipantRegistrationNumber(
            @SerialName("gx-participant:registrationNumberNumber") @Json(
                name = "gx-participant:registrationNumberNumber",
                serializeNull = false
            )
            @field:SchemaService.PropertyName("gx-participant:registrationNumberNumber")
            var gxParticipantRegistrationNumberNumber: String? = null, // 391200FJBNU0YW987L26
            @SerialName("gx-participant:registrationNumberType") @Json(
                name = "gx-participant:registrationNumberType",
                serializeNull = false
            )
            @field:SchemaService.PropertyName("gx-participant:registrationNumberType")
            var gxParticipantRegistrationNumberType: String? = null // leiCode
        )
    }
}

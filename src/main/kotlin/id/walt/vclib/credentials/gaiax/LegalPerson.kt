package id.walt.vclib.credentials.gaiax

import com.beust.klaxon.Json
import id.walt.vclib.model.AbstractVerifiableCredential
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService

data class LegalPerson(
    @Json(name = "@context") @field:SchemaService.PropertyName(name = "@context") @field:SchemaService.Required
    var context: List<String> = listOf(
        "https://www.w3.org/2018/credentials/v1",
        "http://www.w3.org/ns/shacl#",
        "http://www.w3.org/2001/XMLSchema#",
        "http://w3id.org/gaia-x/participant#",
    ),
    override var issuer: String?,
    @Json(serializeNull = false) override var issued: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,

    @Json(serializeNull = false, name = "@id") @field:SchemaService.PropertyName(name = "@id") override var id: String? = null, // "https://compliance.gaia-x.eu/.well-known/participant.json"
    @Json(serializeNull = false) override var credentialSubject: CredentialSubject? = null,
    @Json(serializeNull = false) override val credentialSchema: id.walt.vclib.model.CredentialSchema? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var proof: id.walt.vclib.model.Proof? = null
) : AbstractVerifiableCredential<LegalPerson.CredentialSubject>(type)  {
    data class CredentialSubject(
        @Json(serializeNull = false) override var id: String? = null, // "did:compliance.gaia-x.eu"
        @Json(serializeNull = false, name = "gx-participant:name") @field:SchemaService.PropertyName(name = "gx-participant:name") var gxParticipantName: GxParticipantName? = null,
        @Json(serializeNull = false, name = "gx-participant:legalName") @field:SchemaService.PropertyName(name = "gx-participant:legalName") var gxParticipantLegalName: GxParticipantLegalName? = null,
        @Json(serializeNull = false, name = "gx-participant:registrationNumber") @field:SchemaService.PropertyName(name = "gx-participant:registrationNumber") var gxParticipantRegistrationNumber: GxParticipantRegistrationNumber? = null,
        @Json(serializeNull = false, name = "gx-participant:headquarterAddress") @field:SchemaService.PropertyName(name = "gx-participant:headquarterAddress") var gxParticipantHeadquarterAddress: GxParticipantHeadquarterAddress? = null,
        @Json(serializeNull = false, name = "gx-participant:legalAddress") @field:SchemaService.PropertyName(name = "gx-participant:legalAddress")  var gxParticipantLegalAddress: GxParticipantLegalAddress? = null
    ) : id.walt.vclib.model.CredentialSubject() {
        data class GxParticipantName(
            @Json(serializeNull = false, name = "@value") @field:SchemaService.PropertyName(name = "@value") var value: String? = null, // "Gaia-X AISBL"
            @Json(serializeNull = false, name = "@type") @field:SchemaService.PropertyName(name = "@type") var type: String? = null // "xsd:string"
        )

        data class GxParticipantLegalName(
            @Json(serializeNull = false, name = "@value") @field:SchemaService.PropertyName(name = "@value") var value: String? = null, // "Gaia-X European Association for Data and Cloud AISBL"
            @Json(serializeNull = false, name = "@type") @field:SchemaService.PropertyName(name = "@type") var type: String? = null // "xsd:string"
        )

        data class GxParticipantRegistrationNumber(
            @Json(serializeNull = false, name = "@value") @field:SchemaService.PropertyName(name = "@value") var value: String? = null, // "0762747721"
            @Json(serializeNull = false, name = "@type") @field:SchemaService.PropertyName(name = "@type") var type: String? = null // "xsd:string"
        )

        data class GxParticipantHeadquarterAddress(
            @Json(serializeNull = false, name = "@type") @field:SchemaService.PropertyName(name = "@type") var type: String? = null, // "gx-participant:Address"
            @Json(serializeNull = false, name = "gx-participant:country") @field:SchemaService.PropertyName(name = "gx-participant:country") var gxParticipantCountry: GxParticipantCountry? = null,
            @Json(serializeNull = false, name = "gx-participant:street-address") @field:SchemaService.PropertyName(name = "gx-participant:street-address") var gxParticipantStreetAddress: GxParticipantStreetAddress? = null,
            @Json(serializeNull = false, name = "gx-participant:postal-code") @field:SchemaService.PropertyName(name = "gx-participant:postal-code") var gxParticipantPostalCode: GxParticipantPostalCode? = null,
            @Json(serializeNull = false, name = "gx-participant:locality") @field:SchemaService.PropertyName(name = "gx-participant:locality") var gxParticipantLocality: GxParticipantLocality? = null

        ) {
            data class GxParticipantCountry(
                @Json(serializeNull = false, name = "@value") @field:SchemaService.PropertyName(name = "@value") var value: String? = null, // "BE"
                @Json(serializeNull = false, name = "@type") @field:SchemaService.PropertyName(name = "@type") var type: String? = null // "xsd:string"
            )

            data class GxParticipantStreetAddress(
                @Json(serializeNull = false, name = "@value") @field:SchemaService.PropertyName(name = "@value") var value: String? = null, // "Avenue des Arts 6-9"
                @Json(serializeNull = false, name = "@type") @field:SchemaService.PropertyName(name = "@type") var type: String? = null // "xsd:string"
            )

            data class GxParticipantPostalCode(
                @Json(serializeNull = false, name = "@value") @field:SchemaService.PropertyName(name = "@value") var value: String? = null, // "1210"
                @Json(serializeNull = false, name = "@type") @field:SchemaService.PropertyName(name = "@type") var type: String? = null // "xsd:string"
            )

            data class GxParticipantLocality(
                @Json(serializeNull = false, name = "@value") @field:SchemaService.PropertyName(name = "@value") var value: String? = null, // "Bruxelles/Brussels"
                @Json(serializeNull = false, name = "@type") @field:SchemaService.PropertyName(name = "@type") var type: String? = null // "xsd:string"
            )
        }

        data class GxParticipantLegalAddress(
            @Json(serializeNull = false, name = "@type") @field:SchemaService.PropertyName(name = "@type") var type: String? = null, // "gx-participant:Address"
            @Json(serializeNull = false, name = "gx-participant:country") @field:SchemaService.PropertyName(name = "gx-participant:country") var gxParticipantCountry: GxParticipantCountry? = null,
            @Json(serializeNull = false, name = "gx-participant:street-address") @field:SchemaService.PropertyName(name = "gx-participant:street-address") var gxParticipantStreetAddress: GxParticipantStreetAddress? = null,
            @Json(serializeNull = false, name = "gx-participant:postal-code") @field:SchemaService.PropertyName(name = "gx-participant:postal-code") var gxParticipantPostalCode: GxParticipantPostalCode? = null,
            @Json(serializeNull = false, name = "gx-participant:locality") @field:SchemaService.PropertyName(name = "gx-participant:locality") var gxParticipantLocality: GxParticipantLocality? = null
        ) {
            data class GxParticipantCountry(
                @Json(serializeNull = false, name = "@value") @field:SchemaService.PropertyName(name = "@value") var value: String? = null, // "BE"
                @Json(serializeNull = false, name = "@type") @field:SchemaService.PropertyName(name = "@type") var type: String? = null // "xsd:string"
            )

            data class GxParticipantStreetAddress(
                @Json(serializeNull = false, name = "@value") @field:SchemaService.PropertyName(name = "@value") var value: String? = null, // "Avenue des Arts 6-9"
                @Json(serializeNull = false, name = "@type") @field:SchemaService.PropertyName(name = "@type") var type: String? = null // "xsd:string"
            )

            data class GxParticipantPostalCode(
                @Json(serializeNull = false, name = "@value") @field:SchemaService.PropertyName(name = "@value") var value: String? = null, // "1210"
                @Json(serializeNull = false, name = "@type") @field:SchemaService.PropertyName(name = "@type") var type: String? = null // "xsd:string"
            )

            data class GxParticipantLocality(
                @Json(serializeNull = false, name = "@value") @field:SchemaService.PropertyName(name = "@value") var value: String? = null, // "Bruxelles/Brussels"
                @Json(serializeNull = false, name = "@type") @field:SchemaService.PropertyName(name = "@type") var type: String? = null // "xsd:string"
            )
        }
    }
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential","LegalPerson"),
        template = {
            LegalPerson(
                id = "vc.gaia-x.eu/participant-credential#392ac7f6-399a-437b-a268-4691ead8f176",
                issuer = "did:web:vc.gaia-x.eu:issuer",
                issued = "2022-01-03T20:38:38Z",
                expirationDate = "2022-01-06T20:38:38Z",
                credentialSubject = LegalPerson.CredentialSubject(
                    id = "did:compliance.gaia-x.eu",
                    gxParticipantName = CredentialSubject.GxParticipantName(
                        value = "Gaia-X AISBL",
                        type = "xsd:string"
                    ),
                    gxParticipantLegalName = CredentialSubject.GxParticipantLegalName(
                        value = "Gaia-X European Association for Data and Cloud AISBL",
                        type = "xsd:string"
                    ),
                    gxParticipantRegistrationNumber = CredentialSubject.GxParticipantRegistrationNumber(
                        value = "0762747721",
                        type = "xsd:string"
                    ),
                    gxParticipantHeadquarterAddress = CredentialSubject.GxParticipantHeadquarterAddress(
                        type = "gx-participant:Address",
                        gxParticipantCountry = CredentialSubject.GxParticipantHeadquarterAddress.GxParticipantCountry(
                            value = "BE",
                            type = "xsd:string"
                        ),
                        gxParticipantStreetAddress = CredentialSubject.GxParticipantHeadquarterAddress.GxParticipantStreetAddress(
                            value = "Avenue des Arts 6-9",
                            type = "xsd:string"
                        ),
                        gxParticipantPostalCode = CredentialSubject.GxParticipantHeadquarterAddress.GxParticipantPostalCode(
                            value = "1210",
                            type = "xsd:string"
                        ),
                        gxParticipantLocality = CredentialSubject.GxParticipantHeadquarterAddress.GxParticipantLocality(
                            value = "Bruxelles/Brussels",
                            type = "xsd:string"
                        )
                    ),
                    gxParticipantLegalAddress = CredentialSubject.GxParticipantLegalAddress(
                        type = "gx-participant:Address",
                        gxParticipantCountry = CredentialSubject.GxParticipantLegalAddress.GxParticipantCountry(
                            value = "BE",
                            type = "xsd:string"
                        ),
                        gxParticipantStreetAddress = CredentialSubject.GxParticipantLegalAddress.GxParticipantStreetAddress(
                            value = "Avenue des Arts 6-9",
                            type = "xsd:string"
                        ),
                        gxParticipantPostalCode = CredentialSubject.GxParticipantLegalAddress.GxParticipantPostalCode(
                            value = "1210",
                            type = "xsd:string"
                        ),
                        gxParticipantLocality = CredentialSubject.GxParticipantLegalAddress.GxParticipantLocality(
                            value = "Bruxelles/Brussels",
                            type = "xsd:string"
                        )
                    )
                ),
                credentialSchema = CredentialSchema(
                    id = "https://raw.githubusercontent.com/walt-id/waltid-ssikit-vclib/master/src/test/resources/schemas/ParticipantCredential.json",
                    type = "JsonSchemaValidator2018"
                )
            )
        })
}
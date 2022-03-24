package id.walt.vclib.credentials

import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService

data class EuropeanBankIdentity(
    @Json(name = "@context") @field:SchemaService.PropertyName(name = "@context")
    var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) override var id: String? = null,
    @Json(serializeNull = false) override var issued: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override var credentialSubject: EuropeanBankIdentitySubject? = null,
    @Json(serializeNull = false) override var issuer: String? = null,
    @Json(serializeNull = false) override var proof: Proof? = null,
    override var credentialSchema: CredentialSchema? = null,
) : AbstractVerifiableCredential<EuropeanBankIdentity.EuropeanBankIdentitySubject>(type) {
    data class PlaceOfBirth(
        @Json(serializeNull = false) var country: String? = null,
        @Json(serializeNull = false) var region: String? = null,
        @Json(serializeNull = false) var locality: String? = null
    )

    data class EuropeanBankIdentitySubject(
        @Json(serializeNull = false) override var id: String? = null,
        @Json(serializeNull = false) var familyName: String? = null,
        @Json(serializeNull = false) var givenName: String? = null,
        @Json(serializeNull = false) var birthDate: String? = null,
        @Json(serializeNull = false) var placeOfBirth: PlaceOfBirth? = null
    ) : CredentialSubject()

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "EuropeanBankIdentity"),
        template = {
            EuropeanBankIdentity(
                id = "identity#EuropeanBankIdentity#3add94f4-28ec-42a1-8704-4e4aa51006b4",
                issuer = "did:example:456",
                issued = "2021-08-31T00:00:00Z",
                validFrom = "2021-08-31T00:00:00Z",
                credentialSubject = EuropeanBankIdentitySubject(
                    id = "identity#verifiableID",
                    familyName = "DOE",
                    givenName = "JOHN",
                    birthDate = "1958-08-17",
                    placeOfBirth = PlaceOfBirth("DE", null, "Berlin")
                ),
                credentialSchema = CredentialSchema(
                    id = "https://raw.githubusercontent.com/walt-id/waltid-ssikit-vclib/master/src/test/resources/serialized/EuropeanBankIdentity.json",
                    type = "JsonSchemaValidator2018"
                ),
            )
        }
    )

    override fun newId(id: String) = "identity#EuropeanBankIdentity#${id}"
}

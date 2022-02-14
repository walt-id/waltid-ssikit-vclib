package id.walt.vclib.credentials.gaiax

import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class KycCredential(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) override var id: String? = null,
    @Json(serializeNull = false) override var issuer: String? = null,
    @Json(serializeNull = false) override var issued: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    override var credentialSubject: KycCredentialSubject? = null,
    @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) override var proof: Proof? = null
) : AbstractVerifiableCredential<KycCredential.KycCredentialSubject>(type) {
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableAttestation", "KycCredential"),
        template = {
            KycCredential(
                id = "identity#KycCredential#3add94f4-28ec-42a1-8704-4e4aa51006b4",
                issuer = "did:ebsi:2A9BZ9SUe6BatacSpvs1V5CdjHvLpQ7bEsi2Jb6LdHKnQxaN",
                issued = "2021-08-31T00:00:00Z",
                validFrom = "2021-08-31T00:00:00Z",
                credentialSubject = KycCredentialSubject(
                    id = "did:ebsi:2AEMAqXWKYMu1JHPAgGcga4dxu7ThgfgN95VyJBJGZbSJUtp",
                    familyName = "DOE",
                    firstName = "Jane",
                    dateOfBirth = "1993-04-08",
                    personalIdentifier = "0904008084H",
                    nameAndFamilyNameAtBirth = "Jane DOE",
                    placeOfBirth = "LILLE, FRANCE",
                    currentAddress = "1 Boulevard de la Liberté, 59800 Lille",
                    gender = "FEMALE"
                )
            )
        }
    )

    data class KycCredentialSubject(
        @Json(serializeNull = false) override var id: String? = null,
        var familyName: String? = null,
        var firstName: String? = null,
        var dateOfBirth: String? = null,
        var personalIdentifier: String? = null,
        @Json(serializeNull = false) var nameAndFamilyNameAtBirth: String? = null,
        @Json(serializeNull = false) var placeOfBirth: String? = null,
        @Json(serializeNull = false) var currentAddress: String? = null,
        @Json(serializeNull = false) var gender: String? = null,
    ) : CredentialSubject()

    override fun newId(id: String) = "identity#${id}"
}

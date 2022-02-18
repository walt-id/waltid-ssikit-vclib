package id.walt.vclib.credentials.gaiax

import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class KybMonoCredential(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    override var id: String?,
    override var issuer: String?,
    @Json(serializeNull = false) override var issued: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override var credentialSubject: KybMonoCredentialSubject?,
    @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) override var proof: Proof? = null,
) : AbstractVerifiableCredential<KybMonoCredential.KybMonoCredentialSubject>(type) {
    data class KybMonoCredentialSubject(
        override var id: String?, // did:key
        var kybValidationPassed: Boolean, // true
    ) : CredentialSubject()

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableAttestation", "KybMonoCredential"),
        template = {
            KybMonoCredential(
                id = "did:ebsi-eth:00000001/credentials/1872",
                issuer = "did:example:456",
                issued = "2020-08-24T14:13:44Z",
                credentialSubject = KybMonoCredentialSubject(
                    id = "did:key:dummy",
                    kybValidationPassed = true,
                )
            )
        }
    )
}

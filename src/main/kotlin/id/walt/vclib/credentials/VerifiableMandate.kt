package id.walt.vclib.credentials

import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService

data class VerifiableMandate(
    @Json(name = "@context") @field:SchemaService.PropertyName(name = "@context") @field:SchemaService.Required
    var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) override var id: String? = null,
    @Json(serializeNull = false) override var issuer: String? = null,
    @Json(serializeNull = false) override var issued: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    override var credentialSubject: VerifiableMandateSubject? = null,
    @Json(serializeNull = false) var credentialStatus: CredentialStatus? = null,
    override var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) var evidence: List<Evidence>? = null,
    @Json(serializeNull = false) override var proof: Proof? = null
) : AbstractVerifiableCredential<VerifiableMandate.VerifiableMandateSubject>(type) {
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableMandate"),
        template = {
            VerifiableMandate(
                id = "urn:uuid:3add94f4-28ec-42a1-8704-4e4aa51006b4",
                issuer = "did:ebsi:2A9BZ9SUe6BatacSpvs1V5CdjHvLpQ7bEsi2Jb6LdHKnQxaN",
                issued = "2021-08-31T00:00:00Z",
                validFrom = "2021-08-31T00:00:00Z",
                credentialSubject = VerifiableMandateSubject(
                    id = "",
                    holder = VerifiableMandateHolder(
                        role = "family",
                        grant = "apply_to_masters",
                        id = "",
                        constraints = mapOf()
                    ),
                    policySchemaURI = "https://raw.githubusercontent.com/walt-id/waltid-ssikit/master/src/test/resources/verifiable-mandates/test-policy.rego"
                ),
                credentialSchema = CredentialSchema(
                    id = "https://api.preprod.ebsi.eu/trusted-schemas-registry/v1/schemas/0xb77f8516a965631b4f197ad54c65a9e2f9936ebfb76bae4906d33744dbcc60ba",
                    type = "FullJsonSchemaValidator2021"
                ),
                evidence = listOf(
                    Evidence(
                        id = "https://essif.europa.eu/tsr-va/evidence/f2aeec97-fc0d-42bf-8ca7-0548192d5678",
                        type = listOf("VerifiableMandatePresentation"),
                        evidenceValue = ""
                    )
                )
            )
        }
    )

  data class VerifiableMandateSubject(
    @Json(serializeNull = false) override var id: String? = null,
    var holder: VerifiableMandateHolder,
    var policySchemaURI: String
  ) : CredentialSubject()

  data class Evidence(
    @Json(serializeNull = false) var id: String? = null,
    var type: List<String?>,
    var evidenceValue: String
  )

  data class VerifiableMandateHolder(
    var role: String,
    var grant: String,
    var id: String,
    var constraints: Map<String, String>
  )
}



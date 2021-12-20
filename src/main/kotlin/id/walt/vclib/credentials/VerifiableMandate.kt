package id.walt.vclib.credentials

import com.beust.klaxon.Json
import com.nimbusds.jwt.SignedJWT
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.model.CredentialStatus
import id.walt.vclib.model.Proof
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.JsonIgnore
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required
import java.text.SimpleDateFormat
import java.util.*

private val dateFormat = SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss'Z'").also { it.timeZone = TimeZone.getTimeZone("UTC") }

data class VerifiableMandate(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) override var id: String? = null,
    @Json(serializeNull = false) var issuer: String,
    @Json(serializeNull = false) var issuanceDate: String? = null,
    @Json(serializeNull = false) var validFrom: String? = null,
    @Json(serializeNull = false) var expirationDate: String? = null,
    var credentialSubject: CredentialSubject? = null,
    @Json(serializeNull = false) var credentialStatus: CredentialStatus? = null,
    var credentialSchema: CredentialSchema? = null,
    var evidence: Evidence? = null,
    @Json(serializeNull = false) var proof: Proof? = null
) : VerifiableCredential(type) {
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableAttestation", "VerifiableMandate"),
        template = {
            VerifiableMandate(
                id = "identity#verifiableMandateID#${UUID.randomUUID()}",
                issuer = "did:ebsi:2A9BZ9SUe6BatacSpvs1V5CdjHvLpQ7bEsi2Jb6LdHKnQxaN",
                issuanceDate = "2021-08-31T00:00:00Z",
                validFrom = "2021-08-31T00:00:00Z",
                credentialSubject = CredentialSubject(
                    id = "did:ebsi:2AEMAqXWKYMu1JHPAgGcga4dxu7ThgfgN95VyJBJGZbSJUtp",
                    holder = Holder(
                        id = "",
                        role = "",
                        constraints = Holder.Constraints("", "", "", "", "", "")
            ),
            proxied = Proxied(
                id = "did:ebsi:zgqR3EoNy6WuDkSs1kM8jtH",
                permissions = Proxied.Permissions(
                    grant = listOf("sign", "enroll"),
                    Proxied.Permissions.WhenPermission(role = "family")
                )
            )
            ),
            credentialStatus = CredentialStatus(
                id = "https://api.preprod.ebsi.eu/status/identity#verifiableMandate#51e42fda-cb0a-4333-b6a6-35cb147e1a88",
                type = "CredentialsStatusList2020"
            ),
            credentialSchema = CredentialSchema(
                id = "https://api.preprod.ebsi.eu/trusted-schemas-registry/v1/schemas/0x2488fd38783d65e4fd46e7889eb113743334dbc772b05df382b8eadce763101b",
                type = "JsonSchemaValidator2018"
            ),
            evidence = Evidence(
                id = "https://essif.europa.eu/tsr-va/evidence/f2aeec97-fc0d-42bf-8ca7-0548192d5678",
                type = listOf("DocumentVerification"),
                verifier = "did:ebsi:2A9BZ9SUe6BatacSpvs1V5CdjHvLpQ7bEsi2Jb6LdHKnQxaN",
                evidenceDocument = listOf("https://essif.europa.eu/tsr-va/evidence/f2aeec97-fc0d-42bf-8ca7-0548192d5678"),
                subjectPresence = "Physical",
                documentPresence = listOf("Physical")
            )
            )
        }
    )

    @field:JsonIgnore
    @Json(ignored = true)
    override var jwt: String? = null
        set(value) {
            field = value.also {
                val jwtClaimsSet = SignedJWT.parse(value).jwtClaimsSet
                id = id ?: jwtClaimsSet.jwtid
                issuer = issuer ?: jwtClaimsSet.issuer
                issuanceDate = issuanceDate ?: jwtClaimsSet.issueTime?.let { dateFormat.format(it) }
                validFrom = validFrom ?: jwtClaimsSet.notBeforeTime?.let { dateFormat.format(it) }
                expirationDate = expirationDate ?: jwtClaimsSet.expirationTime?.let { dateFormat.format(it) }
                credentialSubject?.also { it.id = it.id ?: jwtClaimsSet.subject }
            }
        }

    data class CredentialSubject(
        @Json(serializeNull = false) var id: String? = null,
        var holder: Holder,
        var proxied: Proxied
    )

    data class Holder(
        @Json(serializeNull = false) var id: String? = null,
        var role: String,
        var constraints: Constraints,
    ) {
        data class Constraints(
            var startTime: String,
            var endTime: String,
            var boundaries: String,
            var circumstances: String,
            var pointOfOrigin: String,
            var radiusKm: String
        )
    }

    data class Proxied(
        @Json(serializeNull = false) var id: String? = null,
        var permissions: Permissions
    ) {
        data class Permissions(
            var grant: List<String>? = null,
            @Json(name = "when") @field:PropertyName(name = "when")
            var whenPermission: WhenPermission
        ) {
            data class WhenPermission(var role: String)
        }
    }

    data class Evidence(
        @Json(serializeNull = false) var id: String? = null,
        var type: List<String?>,
        var verifier: String,
        var evidenceDocument: List<String?>,
        var subjectPresence: String,
        var documentPresence: List<String?>
    )
}

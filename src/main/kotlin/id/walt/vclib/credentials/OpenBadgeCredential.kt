package id.walt.vclib.credentials

import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required
import java.util.UUID

data class OpenBadgeCredential(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf(
        "https://www.w3.org/2018/credentials/v1",
        "https://purl.imsglobal.org/spec/ob/v3p0/context.json"
    ),
    @Json(serializeNull = false)
    override var id: String?,
    var name: String,
    @field:SchemaService.PropertyName("issuer") @Json(name = "issuer") var issuerObject: Issuer,
    @Json(serializeNull = false) override var issued: String?,
    override var credentialSubject: OpenBadgeCredentialSubject?,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false, name = "credentialSchema") @field:SchemaService.PropertyName("credentialSchema") val credentialSchemas: List<CredentialSchema>? = listOf(CredentialSchema(
        id = "https://purl.imsglobal.org/spec/ob/v3p0/schema/json/ob_v3p0_achievementcredential_schema.json",
        type = "https://json-schema.org/draft/2019-09/schema"
    )),
    @Json(serializeNull = false) override var proof: Proof? = null
    ) : AbstractVerifiableCredential<OpenBadgeCredential.OpenBadgeCredentialSubject>(type) {

    override var issuanceDate: String?
        get() = issued
        set(value) {
            issued = value
        }

    // override and ignore derived property, in favor of credentialSchemas array
    @SchemaService.JsonIgnore
    @Json(ignored = true)
    override val credentialSchema: CredentialSchema?
        get() = credentialSchemas?.firstOrNull()

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "OpenBadgeCredential"),

        template = {
            OpenBadgeCredential(
                id = "urn:uuid:${UUID.randomUUID()}",
                name = "Achievement Credential",
                issuerObject = Issuer(
                    "Profile",
                    "did:key:z6MkrHKzgsahxBLyNAbLQyB1pcWNYC9GmywiWPgkrvntAZcj",
                    "Jobs for the Future (JFF)",
                    "https://w3c-ccg.github.io/vc-ed/plugfest-2-2022/images/JFF-VC-EDU-PLUGFEST2-badge-image.png",
                    Image("https://w3c-ccg.github.io/vc-ed/plugfest-2-2022/images/JFF-VC-EDU-PLUGFEST2-badge-image.png")
                ),
                issued = "2020-03-10T04:24:12.164Z",
                credentialSubject = OpenBadgeCredentialSubject(
                    type = "AchievementSubject",
                    id = "did:jwk:1235667890",
                    OpenBadgeCredentialSubject.Achievement (
                        id = "0",
                        type = "Achievement",
                        name = "Our Wallet Passed JFF Plugfest #2 2022",
                        description = "This wallet can display this Open Badge 3.0",
                        criteria = OpenBadgeCredentialSubject.Achievement.Criteria(
                            type = "Criteria",
                            narrative = "The cohort of the JFF Plugfest 2 in August-November of 2022 collaborated to push interoperability of VCs in education forward."
                        ),
                        image = Image("https://w3c-ccg.github.io/vc-ed/plugfest-2-2022/images/JFF-VC-EDU-PLUGFEST2-badge-image.png"),
                    )
                )
            )
        }
    )

    data class Image(
        var id: String,
        val type: String = "Image",
        @Json(serializeNull = false) var caption: String? = null
    )

    data class Issuer(
        var type: String,
        var id: String,
        var name: String,
        @Json(serializeNull = false) var url: String? = null,
        @Json(serializeNull = false) var image: Image? = null
    )

    data class OpenBadgeCredentialSubject(
        var type: String,
        override var id: String?,
        var achievement: Achievement
    ) : CredentialSubject() {
        data class Achievement(
            var id: String,
            var type: String,
            var name: String,
            @Json(serializeNull = false) var description: String? = null,
            var criteria: Criteria,
            @Json(serializeNull = false) var image: Image? = null,
        ){
            data class Criteria(
                var type: String,
                @Json(serializeNull = false) var narrative: String? = null,
            )
        }
    }

    @SchemaService.JsonIgnore
    @Json(ignored = true)
    override var issuer: String?
        get() = issuerObject.id
        set(value) {
            issuerObject.id = value!!
        }

}

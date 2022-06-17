package id.walt.vclib.credentials

import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required

data class OpenBadgeCredential(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf(
        "https://www.w3.org/2018/credentials/v1",
        "https://w3c-ccg.github.io/vc-ed/plugfest-1-2022/jff-vc-edu-plugfest-1-context.json"
    ),
    @Json(serializeNull = false)
    override var id: String?,
    @field:SchemaService.PropertyName("issuer") @Json(name = "issuer") var issuerObject: Issuer,
    @Json(serializeNull = false) override var issued: String?,
    override var credentialSubject: OpenBadgeCredentialSubject?,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var issuanceDate: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(ignored = true) override var credentialSchema: CredentialSchema? = CredentialSchema(
        id = "https://github.com/walt-id/waltid-ssikit-vclib/blob/master/src/test/resources/schemas/OpenBadgeCredential.json",
        type = "FullJsonSchemaValidator2021"
    ),
    @Json(serializeNull = false) override var proof: Proof? = null
    ) : AbstractVerifiableCredential<OpenBadgeCredential.OpenBadgeCredentialSubject>(type) {

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "OpenBadgeCredential"),
        template = {
            OpenBadgeCredential(
                id = null,
                issuerObject = Issuer(
                    "Profile",
                    "did:key:z6MkrHKzgsahxBLyNAbLQyB1pcWNYC9GmywiWPgkrvntAZcj",
                    "Jobs for the Future (JFF)",
                    "https://kayaelle.github.io/vc-ed/plugfest-1-2022/images/JFF_LogoLockup.png",
                    "https://kayaelle.github.io/vc-ed/plugfest-1-2022/images/JFF_LogoLockup.png"
                ),
                issued = null,
                issuanceDate = "2020-03-10T04:24:12.164Z",
                credentialSubject = OpenBadgeCredentialSubject(
                    type = "AchievementSubject",
                    id = "did:example:123",
                    OpenBadgeCredentialSubject.Achievement (
                        type = "Achievement",
                        name = "Our Wallet Passed JFF Plugfest #1 2022",
                        description = "This wallet can display this Open Badge 3.0",
                        criteria = OpenBadgeCredentialSubject.Achievement.Criteria(
                            type = "Criteria",
                            narrative = "The first cohort of the JFF Plugfest 1 in May/June of 2021 collaborated to push interoperability of VCs in education forward."
                        ),
                        image = "https://w3c-ccg.github.io/vc-ed/plugfest-1-2022/images/plugfest-1-badge-image.png",
                    )
                )
            )
        }
    )

    data class Issuer(
        var type: String,
        var id: String,
        var name: String,
        @Json(serializeNull = false) var url: String? = null,
        @Json(serializeNull = false) var image: String? = null
    )

    data class OpenBadgeCredentialSubject(
        var type: String,
        override var id: String?,
        var achievement: Achievement
    ) : CredentialSubject() {
        data class Achievement(
            var type: String,
            var name: String,
            @Json(serializeNull = false) var description: String? = null,
            var criteria: Criteria,
            @Json(serializeNull = false) var image: String? = null,
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

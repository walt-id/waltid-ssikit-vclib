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
        "https://purl.imsglobal.org/spec/ob/v3p0/context.json"
    ),
    @Json(serializeNull = false) override var id: String?,
    @Json(serializeNull = false) var name: String?,
    @field:SchemaService.PropertyName("issuer") @Json(name = "issuer") var issuerObject: Issuer,
    @Json(serializeNull = false) override var issued: String?,
    override var credentialSubject: OpenBadgeCredentialSubject?,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var issuanceDate: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,


    @Json(serializeNull = false) override var proof: Proof? = null
    ) : AbstractVerifiableCredential<OpenBadgeCredential.OpenBadgeCredentialSubject>(type) {


    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "OpenBadgeCredential"),

        template = {
            OpenBadgeCredential(
                id = "urn:uuid:a63a60be-f4af-491c-87fc-2c8fd3007a58",
                name="JFF x vc-edu PlugFest 2 Interoperability",
                issuerObject = Issuer(
                    "Profile",
                    "did:key:z6MkrHKzgsahxBLyNAbLQyB1pcWNYC9GmywiWPgkrvntAZcj",
                    "Jobs for the Future (JFF)"
                ),
                issued = null,
                issuanceDate = "2022-11-14T00:00:00Z",
                credentialSubject = OpenBadgeCredentialSubject(
                    type = "AchievementSubject",
                    id = "did:key:123",
                    OpenBadgeCredentialSubject.Achievement (
                        id=null,
                        type = "Achievement",
                        name = "JFF x vc-edu PlugFest 2 Interoperability",
                        description = "This credential solution supports the use of OBv3 and w3c Verifiable Credentials and is interoperable with at least two other solutions.  This was demonstrated successfully during JFF x vc-edu PlugFest 2.",
                        criteria = OpenBadgeCredentialSubject.Achievement.Criteria(
                            type = "Criteria",
                            narrative = "Solutions providers earned this badge by demonstrating interoperability between multiple providers based on the OBv3 candidate final standard, with some additional required fields. Credential issuers earning this badge successfully issued a credential into at least two wallets.  Wallet implementers earning this badge successfully displayed credentials issued by at least two different credential issuers."
                        ),
                        image = OpenBadgeCredentialSubject.Achievement.Image(
                            type = "Image",
                            id = "https://w3c-ccg.github.io/vc-ed/plugfest-2-2022/images/JFF-VC-EDU-PLUGFEST2-badge-image.png"
                        )
                    )
                )
            )
        }
    )

    data class Issuer(
        var type: String,
        var id: String,
        var name: String
    )

    data class OpenBadgeCredentialSubject(
        var type: String,
        override var id: String?,
        var achievement: Achievement
    ) : CredentialSubject() {
        data class Achievement(
            var id: String?,
            var type: String,
            var name: String,
            @Json(serializeNull = false) var description: String? = null,
            var criteria: Criteria,
            var image: Image

        ){
            data class Criteria(
                var type: String,
                @Json(serializeNull = false) var narrative: String? = null,
            )
            data class Image(
                var type: String,
                @Json(serializeNull = false) var id: String? = null,

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

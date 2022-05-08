package id.walt.vclib.credentials

import com.beust.klaxon.Json
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService

data class PeerReview(
    @Json(name = "@context") @field:SchemaService.PropertyName(name = "@context")
    var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) override var id: String? = null,
    @Json(serializeNull = false) override var issued: String? = null,
    @Json(serializeNull = false) override var validFrom: String? = null,
    @Json(serializeNull = false) override var expirationDate: String? = null,
    @Json(serializeNull = false) override var credentialSubject: PeerReviewSubject? = null,
    @Json(serializeNull = false) override var issuer: String? = null,
    @Json(serializeNull = false) override var proof: Proof? = null,
    override var credentialSchema: CredentialSchema? = null,
) : AbstractVerifiableCredential<PeerReview.PeerReviewSubject>(type) {
    data class Measurement(
        @Json(serializeNull = false) var name: String? = null,
        @Json(serializeNull = false) var numberValue: Int? = null,
        @Json(serializeNull = false) var scale: String? = null,
        @Json(serializeNull = false) var type: String? = null,
        @Json(serializeNull = false) var indicators: List<Indicator>? = null
    ) {
        data class Indicator(
            @Json(serializeNull = false) var name: String? = null,
            @Json(serializeNull = false) var type: String? = null,
            @Json(serializeNull = false) var category: String? = null
        )
    }

    data class PeerReviewSubject(
        @Json(serializeNull = false) override var id: String? = null,
        @Json(serializeNull = false) var name: String? = null,
        @Json(serializeNull = false) var groupingKey: String? = null,
        @Json(serializeNull = false) var providerName: String? = null,
        @Json(serializeNull = false) var completedDate: String? = null,
        @Json(serializeNull = false) var type: String? = null,
        @Json(serializeNull = false) var measurements: List<Measurement>? = null
    ) : CredentialSubject()

    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "PeerReview"),
        template = {
            PeerReview(
                id = "identity#PeerReview#3add94f4-28ec-42a1-8704-4e4aa51006b4",
                issuer = "did:example:456",
                issued = "2021-08-31T00:00:00Z",
                validFrom = "2021-08-31T00:00:00Z",
                credentialSubject = PeerReviewSubject(
                    id = "identity#verifiableID",
                    name = "WorkPi Peer Review",
                    groupingKey = "c4235de3-e35b-4a53-ac46-f62c2b222441",
                    providerName = "WorkPi",
                    completedDate = "2021-08-31T00:00:00Z",
                    type = "Assessment",
                    measurements = listOf(
                        Measurement(
                            name = "Coaching",
                            numberValue = 87,
                            scale = "PERCENTAGE",
                            type = "People Skills",
                            indicators = listOf(
                                Measurement.Indicator(
                                    name = "Coaching",
                                    type = "People Skills",
                                    category = "SKILL"
                                )
                            )
                        )
                    )
                ),
                credentialSchema = CredentialSchema(
                    id = "https://raw.githubusercontent.com/walt-id/waltid-ssikit-vclib/master/src/test/resources/serialized/PeerReview.json",
                    type = "JsonSchemaValidator2018"
                ),
            )
        }
    )

    override fun newId(id: String) = "identity#PeerReview#${id}"
}

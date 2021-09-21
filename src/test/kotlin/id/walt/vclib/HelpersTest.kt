package id.walt.vclib

import com.beust.klaxon.Json
import id.walt.vclib.Helpers.toMap
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class HelpersTest : StringSpec({
    "test toMap()" {
        val dummyVcMap = DummyCredential().toMap()

        dummyVcMap["@context"] shouldBe listOf("https://www.w3.org/2018/credentials/v1")
        dummyVcMap["type"] shouldBe listOf("VerifiableCredential", "VerifiableAttestation", "DummyCredential")

        dummyVcMap.containsKey("id") shouldBe false
    }
}) {
    data class DummyCredential(
        @Json(name = "@context") var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
        @Json(serializeNull = false) override var id: String? = null
    ) : VerifiableCredential(type) {
        companion object :
            VerifiableCredentialMetadata(listOf("VerifiableCredential", "VerifiableAttestation", "DummyCredential"))
    }
}

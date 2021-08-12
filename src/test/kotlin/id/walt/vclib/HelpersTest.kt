package id.walt.vclib

import com.beust.klaxon.Json
import id.walt.vclib.Helpers.toMap
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata
import io.kotest.core.spec.style.AnnotationSpec
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse

class HelpersTest : AnnotationSpec() {

    data class Dummy(
        @Json(name = "@context")
        var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
        @Json(serializeNull = false) var id: String? = null
    ) : VerifiableCredential(type) {
        companion object : VerifiableCredentialMetadata(type = listOf("VerifiableCredential", "VerifiableAttestation", "Dummy"));
    }

    @Test
    fun testToMap() {
        val dummyVcAsMap = Dummy().toMap()
        assertEquals(listOf("https://www.w3.org/2018/credentials/v1"), dummyVcAsMap["@context"])
        assertEquals(listOf("VerifiableCredential", "VerifiableAttestation", "Dummy"), dummyVcAsMap["type"])
        assertFalse(dummyVcAsMap.containsKey("id"))
    }
}
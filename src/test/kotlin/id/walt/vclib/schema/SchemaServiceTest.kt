package id.walt.vclib.schema

import com.beust.klaxon.Json
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.StringSpec
import java.io.File
import id.walt.vclib.schema.SchemaService.DateTimeFormat;
import id.walt.vclib.schema.SchemaService.JsonIgnore;
import id.walt.vclib.schema.SchemaService.Nullable;
import id.walt.vclib.schema.SchemaService.PropertyName;

data class DummyCredential(
    @Json(name = "@context") @field:PropertyName(name = "@context") var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) @field:Nullable override var id: String? = null,
    @Json(serializeNull = false) @field:DateTimeFormat var issuanceDate: String? = null,
    @field:JsonIgnore var toIgnore: String? = null
) : VerifiableCredential(type) {
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableAttestation", "Dummy"),
        template = { DummyCredential() }
    );
}

class SchemaServiceTest : StringSpec({

    "testing schema generation"   {
        val schema = SchemaService.generateSchema(DummyCredential::class).toPrettyString()
        val expected = File("src/test/resources/schemas/DummyCredential-schema.json").readText()
        schema shouldEqualJson expected
    }
})

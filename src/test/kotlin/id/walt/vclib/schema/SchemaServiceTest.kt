package id.walt.vclib.schema

import id.walt.vclib.Helpers.toCredential
import id.walt.vclib.vclist.Europass
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class SchemaServiceTest : StringSpec({
    "testing schema generation"   {
        val schema = SchemaService.generateSchema(Europass::class).toPrettyString()
        val expected = File("src/test/resources/schemas/Europass-schema.json").readText()
        schema shouldEqualJson expected
    }

    "testing schema validation" {
        SchemaService.validateSchema(
            File("src/test/resources/serialized/Europass.json").readText().toCredential()
        ) shouldBe true
    }
})

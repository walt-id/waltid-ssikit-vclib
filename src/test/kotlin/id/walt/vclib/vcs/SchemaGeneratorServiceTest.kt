package id.walt.vclib.vcs

import id.walt.vclib.schema.SchemaGeneratorService
import id.walt.vclib.vclist.Europass
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class SchemaGeneratorServiceTest : StringSpec({
    "testing schema generation"   {
        val schema = SchemaGeneratorService.generateSchema(Europass::class).toPrettyString()
        println("Generated schema: $schema")
        val expected = File("src/test/resources/schemas/Europass-schema.json").readText()
        schema shouldEqualJson expected
    }
})

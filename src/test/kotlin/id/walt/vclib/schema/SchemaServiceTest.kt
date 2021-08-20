package id.walt.vclib.schema

import id.walt.vclib.Helpers.toCredential
import id.walt.vclib.vclist.Europass
import id.walt.vclib.vclist.VerifiableID
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class SchemaServiceTest : StringSpec({
    "testing schema generation"   {
        listOf(Europass::class, VerifiableID::class).forEach {
            val schema = SchemaService.generateSchema(it).toPrettyString()
            val expected = File("src/test/resources/schemas/${it.java.simpleName}-schema.json").readText()
            schema shouldEqualJson expected
        }
    }

    "testing schema validation" {
        listOf(Europass::class, VerifiableID::class).forEach {
            val vc = File("src/test/resources/serialized/${it.java.simpleName}.json").readText()
            val schema = File("src/test/resources/schemas/${it.java.simpleName}-schema.json").readText()
            SchemaService.validate(vc, schema) shouldBe true
            SchemaService.validate(vc.toCredential(), schema) shouldBe true
        }
    }
})

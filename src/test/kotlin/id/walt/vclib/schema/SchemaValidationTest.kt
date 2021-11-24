package id.walt.vclib.schema

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class SchemaValidationTest : StringSpec({
    val TEST_RESOURCES = "src/test/resources/"
    "validate all Schemas" {
        File("$TEST_RESOURCES/serialized").listFiles { _, name -> name.endsWith(".json") }!!.forEach {
            println("Checking ${it.name}")
            val vc = it.readText()
            SchemaService.validateSchema(vc).errors?.forEach{e -> println(e)}

            SchemaService.validateSchema(vc).valid shouldBe true

            SchemaService.validateSchema(vc, File("$TEST_RESOURCES/schemas/${it.name}").readText()).valid shouldBe true
        }
    }

    "testing VerifiableDiploma" {
        val vc =   File("$TEST_RESOURCES/serialized/VerifiableDiploma.json").readText()

        SchemaService.validateSchema(vc).valid shouldBe true
    }

    "testing Europass" {
        val vc =   File("$TEST_RESOURCES/serialized/Europass.json").readText()
        SchemaService.validateSchema(vc).errors?.forEach{e -> println(e)}
        SchemaService.validateSchema(vc).valid shouldBe true
    }
})

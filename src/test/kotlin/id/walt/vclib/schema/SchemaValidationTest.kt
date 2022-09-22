package id.walt.vclib.schema

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.readText

class SchemaValidationTest : StringSpec({
    val TEST_RESOURCES = "src/test/resources/"

    /**
     * People that have broken schemas
     */
    val BLACKLIST = setOf("https://registry.gaia-x.eu/")

    "validate all Schemas" {
        File("$TEST_RESOURCES/serialized").listFiles { _, name -> name.endsWith(".json") }!!.forEach {
            println("Checking ${it.name} at: $it")
            val vc = it.readText()

            if (BLACKLIST.any { vc.contains(it) }) {
                println("${it.name} CONTAINS BLACKLISTED TERM $it")
                return@forEach
            }

            val validationResult = SchemaService.validateSchema(vc)

            validationResult.errors?.forEach { e -> println("Error in ${it.name}: $e") }

            validationResult.valid shouldBe true

            if (!vc.contains("VerifiablePresentation")) {
                val path = Path("$TEST_RESOURCES/schemas/${it.name}")
                println("Checking schema: $path")

                SchemaService.validateSchema(
                    jsonLdCredential = vc,
                    schema = path.readText()
                ).valid shouldBe true
            }
        }
    }

    "testing VerifiableDiploma" {
        val vc = File("$TEST_RESOURCES/serialized/VerifiableDiploma.json").readText()

        SchemaService.validateSchema(vc).valid shouldBe true
    }

    "testing Europass" {
        val vc = File("$TEST_RESOURCES/serialized/Europass.json").readText()
        SchemaService.validateSchema(vc).errors?.forEach { e -> println(e) }
        SchemaService.validateSchema(vc).valid shouldBe true
    }

    "testing VerifiablePresentation" {
        val vc = File("$TEST_RESOURCES/serialized/VerifiablePresentation.json").readText()
        SchemaService.validateSchema(vc).errors?.forEach { e -> println(e) }
        SchemaService.validateSchema(vc).valid shouldBe true
    }
})

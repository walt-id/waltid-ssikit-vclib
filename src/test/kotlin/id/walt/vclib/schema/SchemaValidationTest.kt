package id.walt.vclib.schema

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class SchemaValidationTest : StringSpec({
    val TEST_RESOURCES = "src/test/resources/"

    /**
     * People that have broken schemas
     */
    val BLACKLIST = setOf("https://registry.gaia-x.eu/")

    "validate all Schemas" {
        File("$TEST_RESOURCES/serialized").listFiles { _, name -> name.endsWith(".json") }!!.forEach {
            println("Checking ${it.name}")
            val vc = it.readText()

            if (BLACKLIST.any { vc.contains(it) }) {
                println("${it.name} CONTAINS BLACKLISTED TERM $it")
                return@forEach
            }

            SchemaService.validateSchema(vc).errors?.forEach { e -> println(e) }

            SchemaService.validateSchema(vc).valid shouldBe true

            if (!vc.contains("VerifiablePresentation")) {
                SchemaService.validateSchema(
                    vc,
                    File("$TEST_RESOURCES/schemas/${it.name}").readText()
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

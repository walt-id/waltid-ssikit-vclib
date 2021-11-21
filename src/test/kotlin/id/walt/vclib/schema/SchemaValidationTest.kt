package id.walt.vclib.schema

import id.walt.vclib.Helpers.toCredential
import io.kotest.core.spec.style.StringSpec
import java.io.File

class SchemaValidationTest : StringSpec({
    "testing example json files" {
        File("src/test/resources/serialized").listFiles { _, name -> name.endsWith(".json") }!!.forEach {
            println("Checking ${it.name}")
            val vc = it.readText().toCredential()
            val jsonSchema = File("src/test/resources/schemas/${it.name}").readText()
            val result = SchemaService.validateSchema(vc, jsonSchema)
            println(result)
        }
    }
})

package id.walt.vclib.vcs

import id.walt.vclib.Helpers.toCredential
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File
import kotlin.reflect.jvm.jvmName

class CredentialsTests : StringSpec({
    "testing example json files" {
        File("src/test/resources").listFiles { _, name -> name.endsWith(".json") }!!.forEach {
            println("Checking ${it.name}")
            val json = it.readText()
            val name = it.nameWithoutExtension

            val vc = json.toCredential()

            vc::class.simpleName shouldBe name
            println("PASS: ${it.name} corresponds to ${vc::class.jvmName}")
        }
    }
})

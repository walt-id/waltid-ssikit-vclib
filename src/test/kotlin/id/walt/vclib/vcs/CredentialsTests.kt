package id.walt.vclib.vcs

import id.walt.vclib.Helpers.encode
import id.walt.vclib.Helpers.toCredential
import id.walt.vclib.credentials.*
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File
import kotlin.reflect.jvm.jvmName

class CredentialsTests : StringSpec({
    "serialize all VCs" {
        File("src/test/resources/serialized/PermanentResidentCard.json").writeText(PermanentResidentCard.template?.invoke()!!.encode())
        File("src/test/resources/serialized/VerifiableAttestation.json").writeText(VerifiableAttestation.template?.invoke()!!.encode())
        File("src/test/resources/serialized/VerifiableAuthorization.json").writeText(VerifiableAuthorization.template?.invoke()!!.encode())
        File("src/test/resources/serialized/Europass.json").writeText(Europass.template?.invoke()!!.encode())
        File("src/test/resources/serialized/UniversityDegree.json").writeText(UniversityDegree.template?.invoke()!!.encode())
        File("src/test/resources/serialized/VerifiableDiploma.json").writeText(VerifiableDiploma.template?.invoke()!!.encode())
        File("src/test/resources/serialized/VerifiableId.json").writeText(VerifiableId.template?.invoke()!!.encode())
        File("src/test/resources/serialized/GaiaxCredential.json").writeText(GaiaxCredential.template?.invoke()!!.encode())
    }

    "parse all serialized VCs" {
        File("src/test/resources/serialized").listFiles { _, name -> name.endsWith(".json") }!!.forEach {
            println("Checking ${it.name}")
            val json = it.readText()
            val name = it.nameWithoutExtension

            val vc = json.toCredential()

            vc::class.simpleName shouldBe name
            println("PASS: ${it.name} corresponds to ${vc::class.jvmName}")
        }
    }
})

package id.walt.vclib.credentials

import com.beust.klaxon.Klaxon
import id.walt.vclib.model.toCredential
import id.walt.vclib.NestedVCs
import id.walt.vclib.credentials.gaiax.GaiaxCredential
import id.walt.vclib.credentials.gaiax.DataSelfDescription
import id.walt.vclib.credentials.gaiax.DataServiceOffering
import id.walt.vclib.credentials.gaiax.ParticipantCredential
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.nestedVCsConverter
import id.walt.vclib.registry.VcTypeRegistry
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File
import kotlin.reflect.jvm.jvmName

private val klaxon = Klaxon().fieldConverter(NestedVCs::class, nestedVCsConverter)

class CredentialsTests : StringSpec({

    "serialize all" {
        VcTypeRegistry.getTemplateTypes().forEach { vcName ->
            run {
                println("Serializing $vcName")
                val vc = VcTypeRegistry.getRegistration(vcName)
                val serializedVc = vc!!.metadata.template!!.invoke()!!.encodePretty()
                File("src/test/resources/serialized/$vcName.json").writeText(serializedVc)
            }
        }
    }

    "serialize defaults" {
        val vc = VerifiableVaccinationCertificate.template?.invoke()!!
        println(vc.subject)
        println(vc.issuer)

        vc.subject = "asdf"
        vc.issuer = "qwer"

        vc as VerifiableVaccinationCertificate

        println(vc.encodePretty())

        vc.credentialSubject?.id shouldBe "asdf"
        vc.issuer shouldBe "qwer"

        File("src/test/resources/serialized/VerifiableVaccinationCertificate.json").writeText(klaxon.toJsonString(vc))

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

fun Any.jsonToString(prettyPrint: Boolean): String{
    var thisJsonString = Klaxon().toJsonString(this)
    var result = thisJsonString
    if(prettyPrint) {
        if(thisJsonString.startsWith("[")){
            result = Klaxon().parseJsonArray(thisJsonString.reader()).toJsonString(true)
        } else {
            result = Klaxon().parseJsonObject(thisJsonString.reader()).toJsonString(true)
        }
    }
    return result
}


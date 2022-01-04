package id.walt.vclib.credentials

import com.beust.klaxon.Klaxon
import id.walt.vclib.model.toCredential
import id.walt.vclib.NestedVCs
import id.walt.vclib.nestedVCsConverter
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File
import kotlin.reflect.jvm.jvmName

private val klaxon = Klaxon().fieldConverter(NestedVCs::class, nestedVCsConverter)

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
        File("src/test/resources/serialized/GaiaxSelfDescription.json").writeText(GaiaxSelfDescription.template?.invoke()!!.encode())
        File("src/test/resources/serialized/GaiaxServiceOffering.json").writeText(GaiaxServiceOffering.template?.invoke()!!.encode())
        File("src/test/resources/serialized/VerifiablePresentation.json").writeText(VerifiablePresentation.template?.invoke()!!.encode())
        File("src/test/resources/serialized/VerifiableVaccinationCertificate.json").writeText(VerifiableVaccinationCertificate.template?.invoke()!!.encode())
        File("src/test/resources/serialized/ProofOfResidence.json").writeText(ProofOfResidence.template?.invoke()!!.encode())
        File("src/test/resources/serialized/ParticipantCredential.json").writeText(ParticipantCredential.template?.invoke()!!.encode())

    }

    "serialize defaults" {
        val vc = VerifiableVaccinationCertificate.template?.invoke()!!
        println(vc.subject)
        println(vc.issuer)

        vc.subject = "asdf"
        vc.issuer = "qwer"

        vc as VerifiableVaccinationCertificate

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

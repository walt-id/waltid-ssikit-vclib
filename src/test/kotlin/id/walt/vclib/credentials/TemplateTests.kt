package id.walt.vclib.credentials

import id.walt.vclib.templates.VcTemplateManager
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class TemplateTests : StringSpec({

    val europassTemplateType = listOf("VerifiableCredential", "VerifiableAttestation", "Europass")
    val verifiableIdTemplateType = listOf("VerifiableCredential", "VerifiableAttestation", "VerifiableId")
    val verifiableDiplomaTemplateType = listOf("VerifiableCredential", "VerifiableAttestation", "VerifiableDiploma")

    "default definitions registered" {
        println("Loading Europass by type")
        val europass = VcTemplateManager.loadTemplate(europassTemplateType)

        europass.type shouldBe europassTemplateType

        println("Loading VerifiableId by type")
        val verifiableId = VcTemplateManager.loadTemplate(verifiableIdTemplateType)

        verifiableId.type shouldBe verifiableIdTemplateType

        println("Loading VerifiableDiploma by type")
        val verifiableDiploma = VcTemplateManager.loadTemplate(verifiableDiplomaTemplateType)

        verifiableDiploma.type shouldBe verifiableDiplomaTemplateType
    }

    "loading default definitions by name" {
        println("Loading Europass by name")
        val europass = VcTemplateManager.loadTemplate("Europass")

        europass.type shouldBe europassTemplateType
    }

//    Loading by alias is currently not required
//    "test aliases loading by type" {
//        val vaMetadata = VerifiableAuthorization.Companion
//        val primaryType = vaMetadata.type
//        val aliasType = vaMetadata.aliases.first()
//
//        val template1 = VcTemplateManager.loadTemplate(primaryType)
//        template1.type shouldBe primaryType
//
//        val template2 = VcTemplateManager.loadTemplate(aliasType)
//        template2.type shouldBe primaryType
//    }
//
//    "test aliases loading by name" {
//        val vaMetadata = VerifiableAuthorization.Companion
//        val primaryType = vaMetadata.type
//        val aliasType = vaMetadata.aliases.first()
//
//        val template1 = VcTemplateManager.loadTemplate("VerifiableAuthorization")
//        template1.type shouldNotBe aliasType
//        template1.type shouldBe primaryType
//    }
})

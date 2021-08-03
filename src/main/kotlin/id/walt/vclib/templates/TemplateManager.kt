package id.walt.vclib.templates

import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VcTypeRegistry

object VcTemplateManager {

    fun loadTemplate(type: List<String>): VerifiableCredential = VcTypeRegistry.getMetadata(type).template!!.invoke()

    fun listTemplateIds(): List<String> = listOf("Europass", "VerifiableAttestation", "VerifiableCredential")
}

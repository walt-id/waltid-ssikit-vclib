package id.walt.vclib.templates

import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VcTypeRegistry
import id.walt.vclib.registry.VerifiableCredentialMetadata

object VcTemplateManager {

    private fun VerifiableCredentialMetadata.getTemplate() = this.template!!.invoke()

    fun loadTemplate(type: List<String>): VerifiableCredential = VcTypeRegistry.getMetadata(type).getTemplate()
    fun loadTemplate(type: String): VerifiableCredential = VcTypeRegistry.getMetadata(type).getTemplate()

    fun getTemplateList(): List<String> = VcTypeRegistry.getTemplateTypes()
}

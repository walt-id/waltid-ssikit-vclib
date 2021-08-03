package id.walt.vclib.adapter

import com.beust.klaxon.TypeAdapter
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VcTypeRegistry
import kotlin.reflect.KClass

class VCTypeAdapter : TypeAdapter<VerifiableCredential> {
    @Suppress("UNCHECKED_CAST")
    override fun classFor(type: Any): KClass<out VerifiableCredential> = VcTypeRegistry.getType(type as List<String>)
        ?: throw IllegalArgumentException("VcLib: Unknown credential type: $type")
}

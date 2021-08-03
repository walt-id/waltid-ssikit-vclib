package id.walt.vclib.registry

import id.walt.vclib.Defaults
import id.walt.vclib.model.VerifiableCredential
import kotlin.reflect.KClass

class CredentialTypeAlreadyRegisteredException(type: List<String>) :
    IllegalArgumentException("Type [${type.joinToString()}] already exists in the registry.")

object VcTypeRegistry {

    private val typeRegistry = HashMap<Int, KClass<out VerifiableCredential>>()
    private val metadataRegistry = HashMap<Int, VerifiableCredentialMetadata>()

    private fun computeKey(type: List<String>) = type.sorted().hashCode()

    fun remove(type: List<String>) = computeKey(type).also { key ->
        typeRegistry.remove(key)
        metadataRegistry.remove(key)
    }

    fun contains(type: List<String>) = computeKey(type).let { key ->
        typeRegistry.containsKey(key) || metadataRegistry.containsKey(key)
    }

    private fun registerDefinition(
        type: List<String>,

        vc: KClass<out VerifiableCredential>,
        metadata: VerifiableCredentialMetadata
    ) {
        if (contains(type)) {
            throw CredentialTypeAlreadyRegisteredException(type)
        }

        computeKey(type).let { key ->
            typeRegistry[key] = vc
            metadataRegistry[key] = metadata
        }
    }

    fun register(metadata: VerifiableCredentialMetadata, vc: KClass<out VerifiableCredential>) {
        registerDefinition(metadata.type, vc, metadata)
        metadata.aliases.forEach { registerDefinition(it, vc, metadata) }
    }

    fun getType(type: List<String>): KClass<out VerifiableCredential>? = typeRegistry[computeKey(type)]
    fun getMetadata(type: List<String>): VerifiableCredentialMetadata = metadataRegistry[computeKey(type)]!!

    init {
        Defaults.loadVcLibDefaults()
    }
}

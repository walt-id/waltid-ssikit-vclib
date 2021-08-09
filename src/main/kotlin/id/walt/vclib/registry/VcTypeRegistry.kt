package id.walt.vclib.registry

import id.walt.vclib.Defaults
import id.walt.vclib.model.VerifiableCredential
import org.lighthousegames.logging.logging
import kotlin.reflect.KClass

class CredentialTypeAlreadyRegisteredException(registration: VcTypeRegistry.TypeRegistration) :
    IllegalArgumentException("Type \"${registration.vc.simpleName!!}\" [${registration.metadata.type.joinToString()}] already exists in the registry.")

object VcTypeRegistry {

    class TypeRegistration(
        val vc: KClass<out VerifiableCredential>,
        val metadata: VerifiableCredentialMetadata,
        val isPrimary: Boolean = true
    )

    private val log = logging()
    private val registry = HashMap<Int, TypeRegistration>()

    private fun computeKey(type: List<String>) = type.sorted().hashCode()

    fun remove(type: List<String>) = registry.remove(computeKey(type))

    fun contains(type: List<String>) = registry.contains(computeKey(type))
    fun contains(type: String) = getRegistration(type) != null


    private fun registerDefinition(
        type: List<String>,
        registration: TypeRegistration
    ) {
        if (contains(type)) {
            throw CredentialTypeAlreadyRegisteredException(registration)
        }

        registry[computeKey(type)] = registration
    }

    fun register(metadata: VerifiableCredentialMetadata, vc: KClass<out VerifiableCredential>) {
        log.info { "Registering ${vc.simpleName}..." }

        registerDefinition(metadata.type, TypeRegistration(vc, metadata))

        metadata.aliases.forEach {
            registerDefinition(it, TypeRegistration(vc, metadata, false))
        }
    }

    fun getRegistration(type: String) = registry.values.firstOrNull { it.vc.simpleName == type && it.isPrimary }

    fun getType(type: List<String>): KClass<out VerifiableCredential>? = registry[computeKey(type)]?.vc
    fun getType(type: String): KClass<out VerifiableCredential>? = getRegistration(type)?.vc
    fun getMetadata(type: List<String>): VerifiableCredentialMetadata = registry[computeKey(type)]!!.metadata
    fun getMetadata(type: String): VerifiableCredentialMetadata = getRegistration(type)!!.metadata

    fun getTypesWithTemplate() = registry.filterValues { it.metadata.template != null }
    fun getTemplateTypes() = getTypesWithTemplate().map { it.value.vc.simpleName!! }

    init {
        Defaults.loadVcLibDefaults()
    }
}

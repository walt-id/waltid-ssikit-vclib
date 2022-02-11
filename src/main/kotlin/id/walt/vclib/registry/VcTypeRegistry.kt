package id.walt.vclib.registry

import id.walt.vclib.credentials.*
import id.walt.vclib.credentials.gaiax.*
import id.walt.vclib.model.VerifiableCredential
import org.lighthousegames.logging.logging
import kotlin.reflect.KClass

class CredentialTypeAlreadyRegisteredException(registration: VcTypeRegistry.TypeRegistration) :
    IllegalArgumentException("Type \"${registration.vc.simpleName!!}\" [${registration.metadata.type.joinToString()}] already exists in the registry.")

object VcTypeRegistry {

    private val log = logging()
    private val registry = HashMap<Int, TypeRegistration>()

    init {
        log.debug { "Registering default templates" }
        register<PermanentResidentCard>(PermanentResidentCard)
        register<VerifiableAttestation>(VerifiableAttestation)
        register<VerifiableAuthorization>(VerifiableAuthorization)
        register<VerifiablePresentation>(VerifiablePresentation)
        register<Europass>(Europass)
        register<UniversityDegree>(UniversityDegree)
        register<VerifiableId>(VerifiableId)
        register<VerifiableDiploma>(VerifiableDiploma)
        register<VerifiableVaccinationCertificate>(VerifiableVaccinationCertificate)
        register<ProofOfResidence>(ProofOfResidence)
        register<EuropeanBankIdentity>(EuropeanBankIdentity)

        // Gaiax
        register<GaiaxCredential>(GaiaxCredential)
        register<DataConsortium>(DataConsortium)
        register<DataServiceOffering>(DataServiceOffering)
        register<DataSelfDescription>(DataSelfDescription)
        register<Iso27001Certificate>(Iso27001Certificate)
        register<KybCredential>(KybCredential)
        register<KybMonoCredential>(KybMonoCredential)
        register<KycCredential>(KycCredential)
        register<ParticipantCredential>(ParticipantCredential)
    }

    class TypeRegistration(
        val vc: KClass<out VerifiableCredential>,
        val metadata: VerifiableCredentialMetadata,
        val isPrimary: Boolean = true
    )

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

    fun getTypesWithTemplate() = registry.filterValues { it.metadata.template != null && it.isPrimary }
    fun getTemplateTypes() = getTypesWithTemplate().map { it.value.vc.simpleName!! }

    inline fun <reified T : VerifiableCredential> register(metadata: VerifiableCredentialMetadata) = register(metadata, T::class)
}

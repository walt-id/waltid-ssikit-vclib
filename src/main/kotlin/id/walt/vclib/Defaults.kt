package id.walt.vclib

import id.walt.vclib.credentials.*
import org.lighthousegames.logging.logging


object Defaults {

    private val log = logging()

    private val defaults = lazy {
        with(VcLibManager) {
            register<PermanentResidentCard>(PermanentResidentCard)
            register<VerifiableAttestation>(VerifiableAttestation)
            register<VerifiableAuthorization>(VerifiableAuthorization)
            register<VerifiablePresentation>(VerifiablePresentation)
            register<Europass>(Europass)
            register<UniversityDegree>(UniversityDegree)
            register<VerifiableId>(VerifiableId)
            register<VerifiableDiploma>(VerifiableDiploma)
            register<GaiaxCredential>(GaiaxCredential)
            register<GaiaxSelfDescription>(GaiaxSelfDescription)
        }
    }

    fun loadVcLibDefaults() {
        // Register default types
        if (!defaults.isInitialized()) {
            log.info { "Registering default templates" }
            defaults.value
        }
    }
}

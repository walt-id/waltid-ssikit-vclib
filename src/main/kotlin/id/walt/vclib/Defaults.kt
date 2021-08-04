package id.walt.vclib

import id.walt.vclib.vclist.*
import org.lighthousegames.logging.logging


object Defaults {

    private val log = logging()

    private val defaults = lazy {
        VcLibManager.register<PermanentResidentCard>(PermanentResidentCard)
        VcLibManager.register<VerifiableAttestation>(VerifiableAttestation)
        VcLibManager.register<VerifiableAuthorization>(VerifiableAuthorization)
        VcLibManager.register<VerifiablePresentation>(VerifiablePresentation)
        VcLibManager.register<Europass>(Europass)
        VcLibManager.register<UniversityDegree>(UniversityDegree)
    }

    fun loadVcLibDefaults() {
        // Register default types
        if (!defaults.isInitialized()) {
            log.info { "Registering default templates" }
            defaults.value
        }
    }
}

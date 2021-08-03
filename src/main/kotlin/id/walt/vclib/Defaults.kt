package id.walt.vclib

import id.walt.vclib.vclist.*


object Defaults {

    fun loadVcLibDefaults() {
        // Register default types
        VcLibManager.register<PermanentResidentCard>(PermanentResidentCard)
        VcLibManager.register<VerifiableAttestation>(VerifiableAttestation)
        VcLibManager.register<VerifiableAuthorization>(VerifiableAuthorization)
        VcLibManager.register<VerifiablePresentation>(VerifiablePresentation)
        VcLibManager.register<Europass>(Europass)
        VcLibManager.register<UniversityDegree>(UniversityDegree)
    }

}

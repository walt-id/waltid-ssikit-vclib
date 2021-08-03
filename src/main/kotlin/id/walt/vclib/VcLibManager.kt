package id.walt.vclib

import com.beust.klaxon.Klaxon
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VcTypeRegistry
import id.walt.vclib.registry.VerifiableCredentialMetadata
import kotlin.reflect.KClass

object VcLibManager {
    private val klaxon = Klaxon()

    fun getVerifiableCredential(json: String): VerifiableCredential {
        return klaxon.parse<VerifiableCredential>(json)!!
    }

    fun register(metadata: VerifiableCredentialMetadata, vc: KClass<out VerifiableCredential>) = VcTypeRegistry.register(metadata, vc)
    inline fun <reified T : VerifiableCredential> register(metadata: VerifiableCredentialMetadata) = register(metadata, T::class)
}

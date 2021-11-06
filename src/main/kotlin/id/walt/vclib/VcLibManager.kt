package id.walt.vclib

import com.beust.klaxon.Klaxon
import com.nimbusds.jwt.SignedJWT
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VcTypeRegistry
import id.walt.vclib.registry.VerifiableCredentialMetadata
import kotlin.reflect.KClass

object VcLibManager {
    private val klaxon = Klaxon().fieldConverter(NestedVCs::class, nestedVCsConverter)
    val JWT_PATTERN = "(^[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*\$)"
    val JWT_VC_CLAIM = "vc"
    val JWT_VP_CLAIM = "vp"

    fun isJWT(data: String): Boolean {
        return Regex(JWT_PATTERN).matches(data)
    }

    private fun vcJsonFromJwt(jwt: String): String {
        val claims = SignedJWT.parse(jwt).jwtClaimsSet.claims
        return when(claims.keys.contains(JWT_VP_CLAIM)) {
            true -> claims[JWT_VP_CLAIM].toString()
            false -> claims[JWT_VC_CLAIM].toString()
        }
    }

    fun getVerifiableCredential(data: String): VerifiableCredential {
        return if (!isJWT(data)) klaxon.parse<VerifiableCredential>(data)!!.also {
            it.json = data
        }
        else klaxon.parse<VerifiableCredential>(vcJsonFromJwt(data))!!.also {
            it.jwt = data;
            it.json = klaxon.toJsonString(it)
        }
    }

    fun getVerifiableCredentialString(vc: VerifiableCredential): String {
        return vc.jwt ?: klaxon.toJsonString(vc)
    }

    fun register(metadata: VerifiableCredentialMetadata, vc: KClass<out VerifiableCredential>) = VcTypeRegistry.register(metadata, vc)
    inline fun <reified T : VerifiableCredential> register(metadata: VerifiableCredentialMetadata) = register(metadata, T::class)
}

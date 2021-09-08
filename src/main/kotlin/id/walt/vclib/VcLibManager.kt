package id.walt.vclib

import com.beust.klaxon.Klaxon
import com.nimbusds.jwt.SignedJWT
import id.walt.vclib.Helpers.encode
import id.walt.vclib.model.Proof
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VcTypeRegistry
import id.walt.vclib.registry.VerifiableCredentialMetadata
import kotlin.reflect.KClass

object VcLibManager {
    private val klaxon = Klaxon()
    val JWT_PATTERN = "(^[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*\$)"
    val JWT_VC_CLAIM = "vc"

    fun getVerifiableCredential(data: String): VerifiableCredential {
        val isJwt = Regex(JWT_PATTERN).matches(data)
        val json = when(isJwt) {
            true -> SignedJWT.parse(data).jwtClaimsSet.claims[JWT_VC_CLAIM].toString()
            false -> data
        }
        var vc = klaxon.fieldConverter(NestedVCs::class, nestedVCsConverter).parse<VerifiableCredential>(json)!!
        vc.json = json
        if(isJwt) {
            vc.proof = Proof(jwt = data)
        }
        return vc
    }

    fun getVerifiableCredentialString(vc: VerifiableCredential): String {
        if(vc?.proof?.jwt != null) {
            return vc!!.proof!!.jwt!!
        } else {
            return klaxon.toJsonString(vc)
        }
    }

    fun register(metadata: VerifiableCredentialMetadata, vc: KClass<out VerifiableCredential>) = VcTypeRegistry.register(metadata, vc)
    inline fun <reified T : VerifiableCredential> register(metadata: VerifiableCredentialMetadata) = register(metadata, T::class)
}

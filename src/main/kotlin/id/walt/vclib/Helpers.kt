package id.walt.vclib

import com.beust.klaxon.Klaxon
import id.walt.vclib.model.VerifiableCredential

object Helpers {

    private val klaxon = Klaxon()

    fun VerifiableCredential.encode(): String = VcLibManager.getVerifiableCredentialString(this)
    fun VerifiableCredential.toMap(): Map<String, Any> = klaxon.parse(encode())!!
    fun String.toCredential() = VcLibManager.getVerifiableCredential(this)
}

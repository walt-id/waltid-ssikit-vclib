package id.walt.vclib

import com.beust.klaxon.Klaxon
import id.walt.vclib.model.VerifiableCredential

object Helpers {

    fun VerifiableCredential.encode(): String = Klaxon().toJsonString(this)
    fun String.toCredential() = VcLibManager.getVerifiableCredential(this)
}

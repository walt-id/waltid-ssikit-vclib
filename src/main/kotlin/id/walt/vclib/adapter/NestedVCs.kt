package id.walt.vclib

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonValue
import com.beust.klaxon.KlaxonException
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.model.toCredential

@Target(AnnotationTarget.FIELD)
annotation class NestedVCs

object nestedVCsConverter : Converter {

    override fun canConvert(cls: Class<*>): Boolean {
        return true
    }

    override fun fromJson(jv: JsonValue): Any? {
        if(jv.array != null) {
            val arr = jv.array
            return arr!!.map {
                when(it) {
                    is JsonObject -> it.toJsonString().toCredential()
                    else -> it.toString().toCredential()
                }
            }.toList()
        } else {
            throw KlaxonException("Couldn't parse nested verifiable credentials")
        }
    }

    override fun toJson(value: Any): String {
        if(value is List<*> && (value.isEmpty() || value.first() is VerifiableCredential)) {
            return "[${ value.joinToString {
                it as VerifiableCredential
                if (it.jwt != null) "\"${ it.encode() }\""
                else it.encode()
            } }]"
        } else {
            throw KlaxonException("Couldn't convert nested verifiable credentials to Json")
        }
    }

}

package id.walt.vclib.credentials.builder

import id.walt.vclib.credentials.w3c.AnyCredentialSubject
import kotlinx.serialization.json.JsonObject

class SubjectBuilder() : BasicBuilder<AnyCredentialSubject, SubjectBuilder>() {
    fun setId(id: String?) = setProperty("id", id)

    override fun build(): AnyCredentialSubject {
        return AnyCredentialSubject.fromJsonObject(JsonObject(properties))
    }
}
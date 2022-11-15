package id.walt.vclib.credentials.w3c

import id.walt.vclib.model.CredentialSubject
import kotlinx.serialization.json.*

open class AnyCredentialSubject(override var id: String? = null, override val properties: Map<String, Any?> = mapOf())
    : CredentialSubject(), ICredentialElement {

    fun toJsonObject() = buildJsonObject {
        id?.let { put("id", it) }
        properties?.let { props ->
            props.keys.forEach { key ->
                put(key, JsonBuilder.toJsonElement(props[key]))
            }
        }
    }

    fun toJson() = toJsonObject().toString()

    companion object {
        fun fromJson(json: String): AnyCredentialSubject {
            return fromJsonObject(Json.parseToJsonElement(json).jsonObject)
        }

        fun fromJsonObject(jsonObject: JsonObject): AnyCredentialSubject {
            return AnyCredentialSubject(
                jsonObject["id"]?.jsonPrimitive?.contentOrNull,
                jsonObject.filterKeys { k -> k != "id" }.mapValues { entry -> JsonBuilder.fromJsonElement(entry.value) }
            )
        }
    }
}
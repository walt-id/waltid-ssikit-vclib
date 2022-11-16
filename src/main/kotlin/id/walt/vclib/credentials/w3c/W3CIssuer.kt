package id.walt.vclib.credentials.w3c

import kotlinx.serialization.json.*

class W3CIssuer(
    var id: String,
    val _isObject: Boolean,
    val customProperties: Map<String, Any?>? = null
) {
    constructor(id: String): this(id, false)
    constructor(id: String, customProperties: Map<String, Any?>): this(id, true, customProperties)
    fun toJsonElement(): JsonElement {
        return if(_isObject) {
            buildJsonObject {
                id.let { put("id", it) }
                customProperties?.let { props ->
                    props.keys.forEach { key ->
                        put(key, JsonBuilder.toJsonElement(props[key]))
                    }
                }
            }
        } else {
            JsonPrimitive(id)
        }
    }

    fun toJson() = toJsonElement().toString()

    companion object {
        fun fromJson(json: String): W3CIssuer {
            return fromJsonElement(Json.parseToJsonElement(json))
        }

        fun fromJsonElement(jsonElement: JsonElement): W3CIssuer {
            return if(jsonElement is JsonObject) {
                W3CIssuer(
                    jsonElement["id"]!!.jsonPrimitive.content,
                    jsonElement.filterKeys { k -> k != "id" }.mapValues { entry -> JsonBuilder.fromJsonElement(entry.value) }
                )
            } else {
                W3CIssuer(id = jsonElement.jsonPrimitive.content)
            }
        }
    }
}
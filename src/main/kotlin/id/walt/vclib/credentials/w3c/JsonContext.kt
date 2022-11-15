package id.walt.vclib.credentials.w3c

import kotlinx.serialization.json.*

class JsonContext(
    var uri: String?,
    val _isObject: Boolean,
    val customProperties: Map<String, Any?>? = null
) {
    constructor(uri: String): this(uri, false)
    constructor(properties: Map<String, Any?>): this(null, true, properties)

    fun toJsonElement(): JsonElement {
        return if(_isObject) {
            buildJsonObject {
                uri?.let { put("uri", it) }
                customProperties?.let { props ->
                    props.keys.forEach { key ->
                        put(key, JsonBuilder.toJsonElement(props[key]))
                    }
                }
            }
        } else {
            uri?.let { JsonPrimitive(it) } ?: JsonNull
        }
    }

    fun toJson() = toJsonElement().toString()

    companion object {
        fun fromJson(json: String): JsonContext {
            return fromJsonElement(Json.parseToJsonElement(json))
        }

        fun fromJsonElement(jsonElement: JsonElement): JsonContext {
            return if(jsonElement is JsonObject) {
                JsonContext(
                    jsonElement["uri"]?.jsonPrimitive?.contentOrNull,
                    _isObject = true,
                    jsonElement.filterKeys { k -> k != "uri" }
                        .mapValues { entry -> JsonBuilder.fromJsonElement(entry.value) }
                )
            } else {
                JsonContext(uri = jsonElement.jsonPrimitive.contentOrNull, _isObject = false)
            }
        }
    }
}
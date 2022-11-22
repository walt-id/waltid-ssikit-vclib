package id.walt.vclib.model

import kotlinx.serialization.json.*

data class CredentialSchema(
    var id: String, // https://essif.europa.eu/tsr-vid/verifiableid1.json
    var type: String // JsonSchemaValidator2018
) {
    /*fun toJsonObject() = buildJsonObject {
        id.let { put("id", it) }
        type.let { put("type", it) }
    }

    fun toJson() = toJsonObject().toString()

    companion object {
        val PREDEFINED_PROPERTY_KEYS = setOf(
            "id", "type"
        )

        fun fromJsonObject(jsonObject: JsonObject): CredentialSchema {
            return CredentialSchema(
                id = jsonObject["id"]?.jsonPrimitive?.content ?: throw Exception("Missing id property in CredentialSchema"),
                type = jsonObject["type"]?.jsonPrimitive?.content ?: throw Exception("Missing id property in CredentialSchema"),
            )
        }

        fun fromJson(json: String) = fromJsonObject(kotlinx.serialization.json.Json.parseToJsonElement(json).jsonObject)
    }*/
}

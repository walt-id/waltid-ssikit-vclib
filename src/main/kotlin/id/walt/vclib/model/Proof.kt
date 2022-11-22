package id.walt.vclib.model

import com.beust.klaxon.Json
import id.walt.vclib.schema.SchemaService
import kotlinx.serialization.json.*

data class Proof(
    @Json(serializeNull = false) val type: String? = null, // "Ed25519Signature2018"
    @Json(serializeNull = false) val creator: String? = null, // did:ebsi:2sMvVBpwueU8j6WBnJcUAkcNnPXLQvGy3a6a3X59wKRnJzZQ
    @Json(serializeNull = false) val created: String? = null, //"2020-04-22T10:37:22Z",
    @Json(serializeNull = false) val domain: String? = null, // "https://api.preprod.ebsi.eu"
    @Json(serializeNull = false) val proofPurpose: String? = null, //"assertionMethod",
    @Json(serializeNull = false) val verificationMethod: String? = null, //"did:example:456#key-1",
    @Json(serializeNull = false) val jws: String? = null, //"eyJjcml0IjpbImI2NCJdLCJiNjQiOmZhbHNlLCJhbGciOiJFZERTQSJ9..BhWew0x-txcroGjgdtK-yBCqoetg9DD9SgV4245TmXJi-PmqFzux6Cwaph0r-mbqzlE17yLebjfqbRT275U1AA"
    @Json(serializeNull = false) val nonce: String? = null, // "d04442d3-661f-411e-a80f-42f19f594c9d"
    //@Json(ignored = true) @field:SchemaService.JsonIgnore val customProperties: Map<String, Any?>? = null
) {
    /*fun toJsonObject() = buildJsonObject {
        type?.let { put("type", it) }
        creator?.let { put("creator", it) }
        created?.let { put("created", it) }
        domain?.let { put("domain", it) }
        proofPurpose?.let { put("proofPurpose", it) }
        verificationMethod?.let { put("verificationMethod", it) }
        jws?.let { put("jws", it) }
        nonce?.let { put("nonce", it) }
        customProperties?.let { props ->
            props.keys.forEach { key ->
                put(key, JsonBuilder.toJsonElement(props[key]))
            }
        }
    }

    fun toJson() = toJsonObject().toString()

    companion object {
        val PREDEFINED_PROPERTY_KEYS = setOf(
            "type", "creator", "created", "domain", "proofPurpose", "verificationMethod", "jws", "nonce"
        )

        fun fromJsonObject(jsonObject: JsonObject): Proof {
            return Proof(
                type = jsonObject["type"]?.jsonPrimitive?.contentOrNull,
                creator = jsonObject["creator"]?.jsonPrimitive?.contentOrNull,
                created = jsonObject["created"]?.jsonPrimitive?.contentOrNull,
                domain = jsonObject["domain"]?.jsonPrimitive?.contentOrNull,
                proofPurpose = jsonObject["proofPurpose"]?.jsonPrimitive?.contentOrNull,
                verificationMethod = jsonObject["verificationMethod"]?.jsonPrimitive?.contentOrNull,
                jws = jsonObject["jws"]?.jsonPrimitive?.contentOrNull,
                nonce = jsonObject["nonce"]?.jsonPrimitive?.contentOrNull,
                customProperties = jsonObject.filterKeys { k -> !PREDEFINED_PROPERTY_KEYS.contains(k) }.mapValues { entry -> JsonBuilder.fromJsonElement(entry.value) }
            )
        }

        fun fromJson(json: String) = fromJsonObject(kotlinx.serialization.json.Json.parseToJsonElement(json).jsonObject)
    }*/
}

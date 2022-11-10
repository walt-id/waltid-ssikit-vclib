package id.walt.vclib.credentials.w3c

import id.walt.vclib.model.AbstractVerifiableCredential
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.model.Proof
import kotlinx.serialization.json.*

class AnyCredential(
    type: List<String>,
    var context: List<JsonContext> = listOf(JsonContext("https://www.w3.org/2018/credentials/v1", false)),
    override var id: String?,
    var issuerObject: W3CIssuer?,
    override var issued: String?,
    override var validFrom: String?,
    override var expirationDate: String?,
    override var proof: Proof?,
    override val credentialSchema: CredentialSchema?,
    override var credentialSubject: AnyCredentialSubject?,
    val customProperties: Map<String, Any?>?

): AbstractVerifiableCredential<AnyCredentialSubject>(type) {

    override var issuer: String?
        get() = issuerObject?.id
        set(value) {
            issuerObject = value?.let { v -> issuerObject?.apply { id = v } ?: W3CIssuer(v, _isObject = false) }
        }

    override var subject: String?
        get() = super.subject
        set(value) {
            credentialSubject = value?.let { v -> credentialSubject?.apply { id = v } ?: AnyCredentialSubject(v, null) }
        }

    fun toJsonObject() = buildJsonObject {
        type.let { put("type", JsonBuilder.toJsonElement(it)) }
        context.let { put("@context",
            if(context.size == 1) {
                context.first().toJsonElement()
            } else {
                buildJsonArray {
                    context.forEach { add(it.toJsonElement()) }
                }
            }
        )}
        id?.let { put("id", JsonBuilder.toJsonElement(it)) }
        issuerObject?.let { put("issuer", it.toJsonElement()) }
        issued?.let { put("issuanceDate", JsonBuilder.toJsonElement(it)) }
        validFrom?.let { put("validFrom", JsonBuilder.toJsonElement(it)) }
        expirationDate?.let { put("expirationDate", JsonBuilder.toJsonElement(it)) }
        proof?.let { put("proof", it.toJsonObject()) }
        credentialSchema?.let { put("credentialSchema", it.toJsonObject()) }
        credentialSubject?.let { put("credentialSubject", it.toJsonObject()) }
        customProperties?.let { props ->
            props.keys.forEach { key ->
                put(key, JsonBuilder.toJsonElement(props[key]))
            }
        }
    }

    fun toJson() = toJsonObject().toString()

    companion object {
        val PREDEFINED_PROPERTY_KEYS = setOf(
            "type",
            "@context",
            "id",
            "issuer",
            "issuanceDate",
            "issued",
            "validFrom",
            "expirationDate",
            "proof",
            "credentialSchema",
            "credentialSubject"
        )

        fun fromJsonObject(jsonObject: JsonObject): AnyCredential {
            return AnyCredential(
                type = jsonObject["type"]?.jsonArray?.map { it.jsonPrimitive.content }?.toList() ?: throw Exception("type list missing"),
                context = jsonObject["@context"]?.let { ctx ->
                    when(ctx) {
                        is JsonArray -> ctx.map { JsonContext.fromJsonElement(it) }.toList()
                        else -> listOf(JsonContext.fromJsonElement(ctx))
                    }
                } ?: throw Exception("Context missing"),
                id = jsonObject["id"]?.jsonPrimitive?.contentOrNull,
                issuerObject = jsonObject["issuer"]?.let { W3CIssuer.fromJsonElement(it) },
                issued = jsonObject["issuanceDate"]?.jsonPrimitive?.contentOrNull,
                validFrom = jsonObject["validFrom"]?.jsonPrimitive?.contentOrNull,
                expirationDate = jsonObject["expirationDate"]?.jsonPrimitive?.contentOrNull,
                proof = jsonObject["proof"]?.let { it as? JsonObject }?.let { Proof.fromJsonObject(it) },
                credentialSchema = jsonObject["credentialSchema"]?.let { it as? JsonObject }?.let { CredentialSchema.fromJsonObject(it) },
                credentialSubject = jsonObject["credentialSubject"]?.let { it as? JsonObject }?.let { AnyCredentialSubject.fromJsonObject(it) },
                customProperties = jsonObject.filterKeys { k -> !PREDEFINED_PROPERTY_KEYS.contains(k) }.mapValues { entry -> JsonBuilder.fromJsonElement(entry.value) }
            )
        }

        fun fromJson(json: String) = fromJsonObject(Json.parseToJsonElement(json).jsonObject)
    }
}
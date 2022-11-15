package id.walt.vclib.credentials.w3c

import id.walt.vclib.credentials.builder.CredentialFactory
import id.walt.vclib.model.AbstractVerifiableCredential
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.model.Proof
import kotlinx.serialization.json.*

open class AnyCredential internal constructor (
    type: List<String>,
    var context: List<JsonContext> = listOf(JsonContext("https://www.w3.org/2018/credentials/v1", false)),
    override var id: String? = null,
    var issuerObject: W3CIssuer? = null,
    override var issuanceDate: String? = null,
    override var issued: String? = null,
    override var validFrom: String? = null,
    override var expirationDate: String? = null,
    override var proof: Proof? = null,
    override val credentialSchema: CredentialSchema? = null,
    override var credentialSubject: AnyCredentialSubject? = null,
    override val properties: Map<String, Any?> = mapOf()
): AbstractVerifiableCredential<AnyCredentialSubject>(type), ICredentialElement {

    internal constructor(jsonObject: JsonObject) : this(
        type = jsonObject["type"]?.jsonArray?.map { it.jsonPrimitive.content }?.toList() ?: throw Exception("type list missing"),
        context = jsonObject["@context"]?.let { ctx ->
            when(ctx) {
                is JsonArray -> ctx.map { JsonContext.fromJsonElement(it) }.toList()
                else -> listOf(JsonContext.fromJsonElement(ctx))
            }
        } ?: throw Exception("Context missing"),
        id = jsonObject["id"]?.jsonPrimitive?.contentOrNull,
        issuerObject = jsonObject["issuer"]?.let { W3CIssuer.fromJsonElement(it) },
        issuanceDate = jsonObject["issuanceDate"]?.jsonPrimitive?.contentOrNull,
        issued = jsonObject["issued"]?.jsonPrimitive?.contentOrNull,
        validFrom = jsonObject["validFrom"]?.jsonPrimitive?.contentOrNull,
        expirationDate = jsonObject["expirationDate"]?.jsonPrimitive?.contentOrNull,
        proof = jsonObject["proof"]?.let { it as? JsonObject }?.let { Proof.fromJsonObject(it) },
        credentialSchema = jsonObject["credentialSchema"]?.let { it as? JsonObject }?.let { CredentialSchema.fromJsonObject(it) },
        credentialSubject = jsonObject["credentialSubject"]?.let { it as? JsonObject }?.let { AnyCredentialSubject.fromJsonObject(it) },
        properties = jsonObject.filterKeys { k -> !PREDEFINED_PROPERTY_KEYS.contains(k) }.mapValues { entry -> JsonBuilder.fromJsonElement(entry.value) }
    )

    override var issuer: String?
        get() = issuerObject?.id
        set(value) {
            issuerObject = value?.let { v -> issuerObject?.apply { id = v } ?: W3CIssuer(v, _isObject = false) }
        }

    override var subject: String?
        get() = super.subject
        set(value) {
            credentialSubject = value?.let { v -> credentialSubject?.apply { id = v } ?: AnyCredentialSubject(v) }
        }

    fun toJsonObject() = buildJsonObject {
        put("type", JsonBuilder.toJsonElement(type))
        put("@context",buildJsonArray {
            context.forEach { add(it.toJsonElement()) }
        })
        id?.let { put("id", JsonBuilder.toJsonElement(it)) }
        issuerObject?.let { put("issuer", it.toJsonElement()) }
        issuanceDate?.let { put("issuanceDate", JsonBuilder.toJsonElement(it)) }
        issued?.let { put("issued", JsonBuilder.toJsonElement(it)) }
        validFrom?.let { put("validFrom", JsonBuilder.toJsonElement(it)) }
        expirationDate?.let { put("expirationDate", JsonBuilder.toJsonElement(it)) }
        proof?.let { put("proof", it.toJsonObject()) }
        credentialSchema?.let { put("credentialSchema", it.toJsonObject()) }
        credentialSubject?.let { put("credentialSubject", it.toJsonObject()) }
        properties.keys.forEach { key ->
            put(key, JsonBuilder.toJsonElement(properties[key]))
        }
    }

    fun toJson() = toJsonObject().toString()

    companion object : CredentialFactory<AnyCredential> {
        val PREDEFINED_PROPERTY_KEYS = setOf(
            "type",
            "@context",
            "id",
            "issuer",
            "issued",
            "issuanceDate",
            "validFrom",
            "expirationDate",
            "proof",
            "credentialSchema",
            "credentialSubject"
        )

        override fun fromJsonObject(jsonObject: JsonObject): AnyCredential {
            return AnyCredential(jsonObject)
        }
    }
}
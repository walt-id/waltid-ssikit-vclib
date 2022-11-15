package id.walt.vclib.credentials.builder

import id.walt.vclib.credentials.w3c.AnyCredential
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

interface CredentialFactory<C: AnyCredential> {
  fun fromJsonObject(jsonObject: JsonObject): C
  fun fromJson(json: String) = fromJsonObject(Json.parseToJsonElement(json).jsonObject)
}
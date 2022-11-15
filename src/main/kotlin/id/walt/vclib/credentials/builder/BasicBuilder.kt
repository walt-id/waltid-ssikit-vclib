package id.walt.vclib.credentials.builder

import id.walt.vclib.credentials.w3c.ICredentialElement
import id.walt.vclib.credentials.w3c.JsonBuilder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject

abstract class BasicBuilder<T : ICredentialElement, B : BasicBuilder<T, B>>() {
  protected val properties = mutableMapOf<String, JsonElement>()

  private fun setAnyProperty(key: String, value: Any?): B {
    properties[key] = JsonBuilder.toJsonElement(value);
    return this as B
  }

  fun setProperty(key: String, value: Boolean?) = setAnyProperty(key, value)
  fun setProperty(key: String, value: Number?) = setAnyProperty(key, value)
  fun setProperty(key: String, value: String?) = setAnyProperty(key, value)
  fun setProperty(key: String, value: Map<String, Any?>?) = setAnyProperty(key, value)
  fun setProperty(key: String, value: List<Any?>?) = setAnyProperty(key, value)
  fun setProperty(key: String, value: JsonElement) = setAnyProperty(key, value)

  abstract fun build(): T
}
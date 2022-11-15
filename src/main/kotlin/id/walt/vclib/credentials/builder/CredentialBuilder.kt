package id.walt.vclib.credentials.builder

import id.walt.vclib.credentials.w3c.*
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.model.Proof
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import java.time.Instant
import java.time.format.DateTimeFormatterBuilder

class AnyCredentialBuilder(type: List<String>):
  CredentialBuilder<AnyCredential, AnyCredentialBuilder>(type, AnyCredential)

open class CredentialBuilder<C: AnyCredential, B: CredentialBuilder<C, B>>(
  val type: List<String>,
  val credentialFactory: CredentialFactory<C>)
    : BasicBuilder<C, B>() {

  protected val dateFormat = DateTimeFormatterBuilder()
    .parseCaseInsensitive()
    .appendInstant(0)
    .toFormatter()

  protected val subjectBuilder = SubjectBuilder()

  val context = mutableListOf<JsonContext>(JsonContext("https://www.w3.org/2018/credentials/v1"))

  fun addContext(contextItem: JsonContext): B {
    context.add(contextItem)
    return this as B
  }
  fun setId(id: String) = setProperty("id", id)
  fun setIssuer(issuer: String) = setProperty("issuer", issuer)
  fun setIssuer(issuer: W3CIssuer) = setProperty("issuer", issuer.toJsonElement())
  fun setIssuanceDate(date: Instant) = setProperty("issuanceDate", dateFormat.format(date))
  fun setValidFrom(date: Instant) = setProperty("validFrom", dateFormat.format(date))
  fun setExpirationDate(date: Instant) = setProperty("expirationDate", dateFormat.format(date))
  fun setCredentialSchema(schema: CredentialSchema) = setProperty("credentialSchema", schema.toJsonObject() as JsonElement)
  fun setSubjectId(id: String) = buildSubject { setId(id) }
  fun buildSubject(builderAction: SubjectBuilder.() -> Unit): B {
    subjectBuilder.builderAction()
    return this as B
  }
  fun setProof(proof: Proof) = setProperty("proof", proof.toJsonObject() as JsonElement)

  override fun build(): C {
    setProperty("type", type)
    setProperty("@context", context.map { it.toJsonElement() }.toList())
    setProperty("credentialSubject", subjectBuilder.build().toJsonObject() as JsonElement)
    return credentialFactory.fromJsonObject(JsonObject(properties))
  }

}
package id.walt.vclib.credentials.builder

import id.walt.vclib.credentials.w3c.*
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.model.Proof
import kotlinx.serialization.json.*
import java.time.Instant
import java.time.format.DateTimeFormatterBuilder

class AnyCredentialBuilder(type: List<String>):
  CredentialBuilder<AnyCredential, AnyCredentialBuilder>(type, AnyCredential) {
    companion object {
      fun fromPartial(partialCredential: AnyCredential) = AnyCredentialBuilder(listOf()).setFromJsonObject(partialCredential.toJsonObject())
      fun fromPartial(partialJson: String) = fromPartial(AnyCredential.fromJson(partialJson))
    }
  }

open class CredentialBuilder<C: AnyCredential, B: CredentialBuilder<C, B>>(
  type: List<String>,
  val credentialFactory: CredentialFactory<C>)
    : BasicBuilder<C, B>() {

  init {
    setProperty("type", type)
    setProperty("@context", listOf("https://www.w3.org/2018/credentials/v1"))
  }
  protected val dateFormat = DateTimeFormatterBuilder()
    .parseCaseInsensitive()
    .appendInstant(0)
    .toFormatter()

  protected val subjectBuilder = SubjectBuilder()

  fun addContext(contextItem: JsonContext): B {
    setProperty("@context", properties["@context"]!!.jsonArray.plus(contextItem.toJsonElement()))
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

  override fun setFromJsonObject(jsonObject: JsonObject): B {
    super.setFromJsonObject(jsonObject)
    properties["credentialSubject"]?.let { subjectBuilder.setFromJsonObject(it.jsonObject) }
    return this as B
  }

  override fun build(): C {
    setProperty("credentialSubject", subjectBuilder.build().toJsonObject() as JsonElement)
    return credentialFactory.fromJsonObject(JsonObject(properties))
  }
}
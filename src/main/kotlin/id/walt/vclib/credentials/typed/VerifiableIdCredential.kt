package id.walt.vclib.credentials.typed

import id.walt.vclib.credentials.builder.CredentialBuilder
import id.walt.vclib.credentials.builder.CredentialFactory
import id.walt.vclib.credentials.w3c.AnyCredential
import id.walt.vclib.credentials.w3c.AnyCredentialSubject
import id.walt.vclib.credentials.w3c.JsonBuilder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import java.text.SimpleDateFormat
import java.util.*

class VerifiableIdCredential internal constructor(jsonObject: JsonObject): AnyCredential(jsonObject) {

  val firstName: String?
    get() = credentialSubject?.properties?.get("firstName")?.toString()

  val familyName: String?
    get() = credentialSubject?.properties?.get("familyName")?.toString()

  // ...

  companion object: CredentialFactory<VerifiableIdCredential> {
    override fun fromJsonObject(jsonObject: JsonObject): VerifiableIdCredential {
      return VerifiableIdCredential(jsonObject)
    }
  }
}

class VerifiableIdBuilder: CredentialBuilder<VerifiableIdCredential, VerifiableIdBuilder>(listOf("VerifiableCredential", "VerifiableAttestation", "VerifiableId"), VerifiableIdCredential) {

  fun setFirstName(names: String) = buildSubject { setProperty("firstName", names) }
  fun setFamilyName(name: String) = buildSubject { setProperty("familyName", name) }
  fun setDateOfBirth(date: Date) = buildSubject { setProperty("dateOfBirth", SimpleDateFormat("yyyy-MM-dd").format(date)) }

//...

}
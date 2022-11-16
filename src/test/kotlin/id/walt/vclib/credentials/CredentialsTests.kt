package id.walt.vclib.credentials

import com.beust.klaxon.Klaxon
import id.walt.vclib.model.toCredential
import id.walt.vclib.NestedVCs
import id.walt.vclib.credentials.builder.AnyCredentialBuilder
import id.walt.vclib.credentials.builder.CredentialBuilder
import id.walt.vclib.credentials.builder.SubjectBuilder
import id.walt.vclib.credentials.typed.VerifiableIdBuilder
import id.walt.vclib.credentials.typed.VerifiableIdCredential
import id.walt.vclib.credentials.w3c.AnyCredential
import id.walt.vclib.credentials.w3c.AnyCredentialSubject
import id.walt.vclib.credentials.w3c.JsonContext
import id.walt.vclib.credentials.w3c.W3CIssuer
import id.walt.vclib.nestedVCsConverter
import id.walt.vclib.registry.VcTypeRegistry
import id.walt.vclib.templates.VcTemplateManager
import io.kotest.assertions.json.shouldMatchJson
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.types.instanceOf
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import java.io.File
import java.time.Instant
import java.util.Calendar
import kotlin.reflect.jvm.jvmName

private val klaxon = Klaxon().fieldConverter(NestedVCs::class, nestedVCsConverter)

class CredentialsTests : StringSpec({

    "serialize all" {
        VcTypeRegistry.getTemplateTypes().forEach { vcName ->
            run {
                println("Serializing $vcName")
                val vc = VcTypeRegistry.getRegistration(vcName)
                val serializedVc = vc!!.metadata.template!!.invoke().encodePretty()
                File("src/test/resources/serialized/$vcName.json").writeText(serializedVc)
            }
        }
    }

    "serialize defaults" {
        val vc = VerifiableVaccinationCertificate.template?.invoke()!!
        println(vc.subject)
        println(vc.issuer)

        vc.subject = "asdf"
        vc.issuer = "qwer"

        vc as VerifiableVaccinationCertificate

        println(vc.encodePretty())

        vc.credentialSubject?.id shouldBe "asdf"
        vc.issuer shouldBe "qwer"

        File("src/test/resources/serialized/VerifiableVaccinationCertificate.json").writeText(klaxon.toJsonString(vc))

    }

    "parse all serialized VCs" {
        File("src/test/resources/serialized").listFiles { _, name -> name.endsWith(".json") }!!.forEach {
            println("Checking ${it.name}")
            val json = it.readText()
            val name = it.nameWithoutExtension

            val vc = json.toCredential()

            vc::class.simpleName shouldBe name
            println("PASS: ${it.name} corresponds to ${vc::class.jvmName}")
        }
    }

    "verifiableCredential's issuanceDate is validFrom" {
        val vc = Europass(validFrom = "2022-02-08T22:12:00Z")
        vc.encode() shouldContain "\"validFrom\" : \"2022-02-08T22:12:00Z\""
        vc.encode() shouldContain "\"issuanceDate\" : \"2022-02-08T22:12:00Z\""
    }

    "generic w3c credential 0" {
        val credentialString =
"""
{
  "@context": [
    "https://www.w3.org/2018/credentials/v1",
    "https://w3id.org/security/suites/jws-2020/v1",
    { "@vocab": "https://example.com/#" }
  ],
  "type": ["VerifiableCredential"],
  "issuer": "did:example:123",
  "issuanceDate": "2022-03-19T15:20:55Z",
  "credentialSubject": {
    "foo": "bar"
  }
}
""".trimIndent()
        val credential = credentialString.toCredential()
        credential shouldNotBe null
        credential shouldBe instanceOf<AnyCredential>()
        credential.issuer shouldBe "did:example:123"
        credential.subject shouldBe null
        (credential as AnyCredential).credentialSubject!!.properties.keys shouldContain "foo"

        credential.encode() shouldMatchJson credentialString
    }

    "generic w3c credential 1" {
        val credentialString =
"""
    {
      "@context": [
        "https://www.w3.org/2018/credentials/v1",
        "https://w3id.org/security/suites/jws-2020/v1",
        { "@vocab": "https://example.com/#" }
      ],
      "type": ["VerifiableCredential"],
      "issuer": "did:example:123",
      "issuanceDate": "2021-01-01T19:23:24Z",
      "expirationDate": "2031-01-01T19:23:24Z",
      "credentialSubject": {
        "id": "did:example:456",
        "type": "Person"
      }
    }
""".trimIndent()
        val credential = credentialString.toCredential()
        credential shouldNotBe null
        credential shouldBe instanceOf<AnyCredential>()
        credential.issuer shouldBe "did:example:123"
        credential.subject shouldBe "did:example:456"
        (credential as AnyCredential).credentialSubject!!.properties!!.keys shouldContain "type"

        credential.encode() shouldMatchJson credentialString
    }

    "generic w3c credential 2" {
        val credentialString =
            """
    {
  "@context": [
    "https://www.w3.org/2018/credentials/v1",
    "https://w3id.org/security/suites/jws-2020/v1",
    { "@vocab": "https://example.com/#" }
  ],
  "type": ["VerifiableCredential"],
  "issuer": "did:example:123",
  "issuanceDate": "2021-01-01T19:23:24Z",
  "credentialSubject": {
    "id": "did:example:456"
  },
  "evidence": [
    {
      "id": "https://example.edu/evidence/f2aeec97-fc0d-42bf-8ca7-0548192d4231",
      "type": ["DocumentVerification"],
      "verifier": "https://example.edu/issuers/14",
      "evidenceDocument": "DriversLicense",
      "subjectPresence": "Physical",
      "documentPresence": "Physical"
    },
    {
      "id": "https://example.edu/evidence/f2aeec97-fc0d-42bf-8ca7-0548192dxyzab",
      "type": ["SupportingActivity"],
      "verifier": "https://example.edu/issuers/14",
      "evidenceDocument": "Fluid Dynamics Focus",
      "subjectPresence": "Digital",
      "documentPresence": "Digital"
    }
  ]
}
""".trimIndent()
        val credential = credentialString.toCredential()
        credential shouldNotBe null
        credential shouldBe instanceOf<AnyCredential>()
        credential.issuer shouldBe "did:example:123"
        credential.subject shouldBe "did:example:456"
        (credential as AnyCredential).properties!!.keys shouldContain "evidence"

        credential.encode() shouldMatchJson credentialString
    }

    "generic w3c credential 3" {
        val credentialString =
            """
    {
  "@context": [
    "https://www.w3.org/2018/credentials/v1",
    "https://w3id.org/security/suites/jws-2020/v1",
    { "@vocab": "https://example.com/#" }
  ],
  "id": "https://example.com/credential/123456",
  "type": ["VerifiableCredential", "VerifiableBusinessCard"],
  "name": "Verifiable Business Card",
  "relatedLink": [
    {
      "type": "LinkRole",
      "target": "https://example.com/organizations/example-org/presentations/available",
      "linkRelationship": "OrganizationPresentationEndpoint"
    }
  ],
  "issuanceDate": "2016-12-31T23:59:59Z",
  "expirationDate": "2038-01-19T03:14:08Z",
  "issuer": {
    "id": "did:example:123",
    "type": "Organization",
    "name": "Grady, Purdy and Pacocha",
    "description": "Secured 24/7 neural-net",
    "address": {
      "type": "PostalAddress",
      "streetAddress": "0516 Kendrick Heights",
      "addressLocality": "Port Reba",
      "addressRegion": "South Carolina",
      "postalCode": "53062-7356",
      "addressCountry": "Austria"
    },
    "email": "Cora31@example.net",
    "phoneNumber": "555-158-2528",
    "faxNumber": "555-577-3567"
  },
  "credentialSubject": {
    "type": ["Organization"],
    "name": "Steel Manufacturer Org",
    "url": "https://www.example.com/"
  }
}

""".trimIndent()
        val credential = credentialString.toCredential()
        credential shouldNotBe null
        credential shouldBe instanceOf<AnyCredential>()
        credential.issuer shouldBe "did:example:123"
        (credential as AnyCredential).issuerObject!!.customProperties!!.keys shouldContain "type"
        credential.subject shouldBe null
        (credential as AnyCredential).credentialSubject!!.properties!!.keys shouldContain "name"
        (credential as AnyCredential).properties!!.keys shouldContain "relatedLink"

        credential.encode() shouldMatchJson credentialString
    }

  "any credential builder test" {
    val credentialString =
      """
    {
      "@context": [
        "https://www.w3.org/2018/credentials/v1",
        "https://w3id.org/security/suites/jws-2020/v1",
        { "@vocab": "https://example.com/#" }
      ],
      "type": ["VerifiableCredential"],
      "issuer": {
        "id": "did:example:123",
        "type": "Organization",
        "name": "Grady, Purdy and Pacocha"
      },
      "issuanceDate": "2021-01-01T19:23:24Z",
      "expirationDate": "2031-01-01T19:23:24Z",
      "credentialSubject": {
        "id": "did:example:456",
        "type": "Person"
      },
      "name": "Verifiable Business Card"
    }
""".trimIndent()

    AnyCredentialBuilder(listOf("VerifiableCredential"))
      .addContext(JsonContext("https://w3id.org/security/suites/jws-2020/v1"))
      .addContext(JsonContext(mapOf("@vocab" to "https://example.com/#")))
      .setIssuer(W3CIssuer(
        "did:example:123", true,
        mapOf(
          "type" to "Organization",
          "name" to "Grady, Purdy and Pacocha"
        )
      ))
      .setIssuanceDate(Instant.parse("2021-01-01T19:23:24Z"))
      .setExpirationDate(Instant.parse("2031-01-01T19:23:24Z"))
      .buildSubject {
        setId("did:example:456")
        setProperty("type", "Person")
      }
      .setProperty("name", "Verifiable Business Card")
      .build().toJson() shouldMatchJson credentialString
  }

  "verifiable ID builder test" {
    val vid = VerifiableIdBuilder()
      .setIssuer("did:example:1234")
      .setSubjectId("did:example:567")
      .setFirstName("John")
      .setFamilyName("Doe")
      .build()

    println(vid.toJson())

    vid shouldBe instanceOf<VerifiableIdCredential>()
    vid.firstName shouldBe "John"
    vid.familyName shouldBe "Doe"
  }

  "test parse VerifiableId" {
    // parse as typed VerifiableIdCredential
    val vidJson = File("src/test/resources/serialized/VerifiableId.json").readText()
    val vid = VerifiableIdCredential.fromJson(vidJson)
    vid shouldBe instanceOf<VerifiableIdCredential>()
    vid.subject shouldBe "did:ebsi:2AEMAqXWKYMu1JHPAgGcga4dxu7ThgfgN95VyJBJGZbSJUtp"
    vid.firstName shouldBe "Jane"
    vid.familyName shouldBe "DOE"

    vid.toJson() shouldMatchJson vidJson

    // parse as AnyCredential
    val vidAny = AnyCredential.fromJson(vidJson)
    vidAny shouldBe instanceOf<AnyCredential>()
    vidAny.subject shouldBe vid.subject
    vidAny.credentialSubject?.properties?.get("firstName") shouldBe vid.firstName

    vidAny.toJson() shouldMatchJson vidJson
  }

  "test build from partial" {
    val partial =
      """
    {
      "@context": [
        "https://www.w3.org/2018/credentials/v1",
        "https://w3id.org/security/suites/jws-2020/v1",
        { "@vocab": "https://example.com/#" }
      ],
      "type": ["VerifiableCredential"],
      "issuer": {
        "id": "did:example:123",
        "type": "Organization",
        "name": "Grady, Purdy and Pacocha"
      },
      "issuanceDate": "2021-01-01T19:23:24Z",
      "expirationDate": "2031-01-01T19:23:24Z",
      "credentialSubject": {
        "id": "did:example:456",
        "type": "Person"
      },
      "name": "Verifiable Business Card"
    }
""".trimIndent()

    val target =
      """
    {
      "@context": [
        "https://www.w3.org/2018/credentials/v1",
        "https://w3id.org/security/suites/jws-2020/v1",
        { "@vocab": "https://example.com/#" }
      ],
      "type": ["VerifiableCredential"],
      "issuer": {
        "id": "did:example:123",
        "type": "Organization",
        "name": "Grady, Purdy and Pacocha"
      },
      "issuanceDate": "2022-01-01T00:00:00Z",
      "expirationDate": "2031-01-01T19:23:24Z",
      "credentialSubject": {
        "id": "did:example:789",
        "type": "Person",
        "role": "user"
      },
      "name": "Verifiable Business Card",
      "custom": "value"
    }
""".trimIndent()

    val cred = AnyCredentialBuilder
      .fromPartial(partial)
      .setIssuanceDate(Instant.parse("2022-01-01T00:00:00Z"))
      .setSubjectId("did:example:789")
      .buildSubject {
        setProperty("role", "user")
      }
      .setProperty("custom", "value")
      .build()

    cred.subject shouldBe "did:example:789"
    cred.credentialSubject!!.properties["role"] shouldBe "user"
    cred.properties["custom"] shouldBe "value"

    cred.toJson() shouldMatchJson target
  }

  "test populate template" {
    val vidTemplate = File("src/test/resources/serialized/VerifiableId.json").readText()
    val vidData = """
      {
        "credentialSubject": {
          "familyName": "LATE",
          "firstName": "Popu"
        }
      }
    """.trimIndent()
    val cred = AnyCredentialBuilder
      .fromPartial(vidTemplate)
      .setFromJson(vidData)
      .setSubjectId("did:example:123")
      .build()

    cred.subject shouldBe "did:example:123"
    cred.credentialSubject!!.properties["dateOfBirth"] shouldBe "1993-04-08" // data taken from template
    cred.credentialSubject!!.properties["familyName"] shouldBe "LATE" // data taken from population data
    cred.credentialSubject!!.properties["firstName"] shouldBe "Popu"

    println(cred.toJson())
  }

  "test setIssuerId" {
    val credIssuerString = """
      {
        "issuer": "did:example:123"
      }
    """.trimIndent()
    val credIssuerObject = """
      {
        "issuer": {
          "id": "did:example:123",
          "custom": "property"
        }
      }
    """.trimIndent()

    // test set on empty credential
    AnyCredentialBuilder(listOf("VerifiableCredential"))
      .setIssuerId("did:example:456")
      .build().also {
        it.issuer shouldBe "did:example:456"
        it.issuerObject?._isObject shouldBe false
      }

    // test set on issuer string
    AnyCredentialBuilder.fromPartial(credIssuerString)
      .setIssuerId("did:example:456")
      .build().also {
        it.issuer shouldBe "did:example:456"
        it.issuerObject?._isObject shouldBe false
      }

    // test set on issuer object
    AnyCredentialBuilder.fromPartial(credIssuerObject)
      .setIssuerId("did:example:456")
      .build().also {
        it.issuer shouldBe "did:example:456"
        it.issuerObject?._isObject shouldBe true
        it.issuerObject?.customProperties?.get("custom") shouldBe "property"
      }
  }
})

fun Any.jsonToString(prettyPrint: Boolean): String{
    var thisJsonString = Klaxon().toJsonString(this)
    var result = thisJsonString
    if(prettyPrint) {
        if(thisJsonString.startsWith("[")){
            result = Klaxon().parseJsonArray(thisJsonString.reader()).toJsonString(true)
        } else {
            result = Klaxon().parseJsonObject(thisJsonString.reader()).toJsonString(true)
        }
    }
    return result
}


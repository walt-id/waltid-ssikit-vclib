package id.walt.vclib.schema

import com.beust.klaxon.Json
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.DateTimeFormat
import id.walt.vclib.schema.SchemaService.JsonIgnore
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.vclist.*
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.StringSpec
import java.io.File

data class DummyCredential(
    @Json(name = "@context") @field:PropertyName(name = "@context")  @field:SchemaService.Required var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) @field:SchemaService.Required override var id: String? = null,
    @Json(serializeNull = false) @field:DateTimeFormat var issuanceDate: String? = null,
    @field:JsonIgnore var toIgnore: String? = null
) : VerifiableCredential(type) {
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableAttestation", "Dummy"),
        template = { DummyCredential() }
    );
}

class SchemaServiceTest : StringSpec({

    "Testing schema generation"   {
        validateSchema(DummyCredential::class.java)
    }

    "Verify VerifiableDiploma schema"   {
        validateSchema(VerifiableDiploma::class.java)
    }

    "Verify VerifiableAttestation schema"   {
        validateSchema(VerifiableAttestation::class.java)
    }

    "Verify VerifiableAuthorization schema"   {
        validateSchema(VerifiableAuthorization::class.java)
    }

    "Verify VerifiablePresentation schema"   {
        validateSchema(VerifiablePresentation::class.java)
    }

    "Verify VerifiableId schema"   {
        validateSchema(VerifiableId::class.java)
    }

    "Verify Europass schema"   {
        validateSchema(Europass::class.java)
    }

    "Verify GaiaxCredential schema"   {
        validateSchema(GaiaxCredential::class.java)
    }

    "Verify UniversityDegree schema"   {
        validateSchema(UniversityDegree::class.java)
    }

    "Verify PermanentResidentCard schema"   {
        validateSchema(PermanentResidentCard::class.java)
    }
})

private inline fun <T : VerifiableCredential>validateSchema(vc: Class<T>) {
    val schema = SchemaService.generateSchema(vc)
    File("src/test/resources/schemas/${vc.simpleName}.json").writeText(schema)
    val expected = File("src/test/resources/schemas/${vc.simpleName}.json").readText()
    schema shouldEqualJson expected
}

package id.walt.vclib.schema

import com.beust.klaxon.Json
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.DateTimeFormat
import id.walt.vclib.schema.SchemaService.JsonIgnore
import id.walt.vclib.schema.SchemaService.Nullable
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.vclist.*
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.StringSpec
import java.io.File

data class DummyCredential(
    @Json(name = "@context") @field:PropertyName(name = "@context") var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) @field:Nullable override var id: String? = null,
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
        validateSchema<DummyCredential>()
    }

    "Verify VerifiableDiploma schema"   {
        validateSchema<VerifiableDiploma>()
    }

    "Verify VerifiableAttestation schema"   {
        validateSchema<VerifiableAttestation>()
    }

    "Verify VerifiableAuthorization schema"   {
        validateSchema<VerifiableAuthorization>()
    }

    "Verify VerifiablePresentation schema"   {
        validateSchema<VerifiablePresentation>()
    }

    "Verify VerifiableId schema"   {
        validateSchema<VerifiableId>()
    }

    "Verify Europass schema"   {
        validateSchema<Europass>()
    }

    "Verify GaiaxCredential schema"   {
        validateSchema<GaiaxCredential>()
    }

    "Verify UniversityDegree schema"   {
        validateSchema<UniversityDegree>()
    }

    "Verify PermanentResidentCard schema"   {
        validateSchema<UniversityDegree>()
    }
})

private inline fun <reified T : VerifiableCredential> validateSchema() {
    val schema = SchemaService.generateSchema(T::class).toPrettyString()
    // File("src/test/resources/schemas/${T::class.simpleName}.json").writeText(schema)
    val expected = File("src/test/resources/schemas/${T::class.simpleName}.json").readText()
    schema shouldEqualJson expected
}

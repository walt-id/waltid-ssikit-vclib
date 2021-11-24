package id.walt.vclib.schema

import com.beust.klaxon.Json
import id.walt.vclib.credentials.*
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.DateTimeFormat
import id.walt.vclib.schema.SchemaService.JsonIgnore
import id.walt.vclib.schema.SchemaService.PropertyName
import io.kotest.core.spec.style.StringSpec
import java.io.File

data class DummyCredential(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:SchemaService.Required var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
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

    "testing schema generation"   {
        generateSchema(DummyCredential::class.java)
    }

    "verify VerifiableDiploma schema"   {
        generateSchema(VerifiableDiploma::class.java)
    }

    "verify VerifiableAttestation schema"   {
        generateSchema(VerifiableAttestation::class.java)
    }

    "verify VerifiableAuthorization schema"   {
        generateSchema(VerifiableAuthorization::class.java)
    }

    "verify VerifiablePresentation schema"   {
        generateSchema(VerifiablePresentation::class.java)
    }

    "verify VerifiableId schema"   {
        generateSchema(VerifiableId::class.java)
    }

    "verify Europass schema"   {
        generateSchema(Europass::class.java)
    }

    "verify GaiaxCredential schema"   {
        generateSchema(GaiaxCredential::class.java)
    }

    "verify UniversityDegree schema"   {
        generateSchema(UniversityDegree::class.java)
    }

    "verify PermanentResidentCard schema"   {
        generateSchema(PermanentResidentCard::class.java)
    }
})

private inline fun <T : VerifiableCredential> generateSchema(vc: Class<T>) {
    val schema = SchemaService.generateSchema(vc)
    File("src/test/resources/schemas/${vc.simpleName}.json").writeText(schema)
}

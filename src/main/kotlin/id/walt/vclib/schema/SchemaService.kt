package id.walt.vclib.schema

import com.github.victools.jsonschema.generator.*
import id.walt.vclib.credentials.Europass
import id.walt.vclib.credentials.VerifiableId
import id.walt.vclib.credentials.VerifiablePresentation
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.model.toCredential
import java.util.*

data class ValidationResult(val valid: Boolean, val errors: List<String>? = null)

object SchemaService {

    annotation class PropertyName(val name: String)
    annotation class Required
    annotation class DateTimeFormat
    annotation class JsonIgnore
    annotation class ReadOnly

    private val generator = SchemaGeneratorConfigBuilder(
        SchemaVersion.DRAFT_7,
        OptionPreset.PLAIN_JSON
    ).let { configBuilder ->
        configBuilder.forTypesInGeneral()
            .withTitleResolver { getTitleResolver(it) }
            .withDescriptionResolver { getDescriptionResolver(it) }
        configBuilder.forFields()
            .withPropertyNameOverrideResolver { getPropertyNameOverrideResolver(it) }
            .withRequiredCheck { getRequiredCheck(it) }
            .withStringFormatResolver { getFormatResolverCheck(it) }
            .withIgnoreCheck { getIgnoreCheck(it) }
        configBuilder.with(Option.FORBIDDEN_ADDITIONAL_PROPERTIES_BY_DEFAULT)
        SchemaGenerator(configBuilder.build())
    }

    private fun getTitleResolver(type: TypeScope) = when (type.type.erasedType) {
        Europass::class.java -> "EBSI Verifiable Attestation - Diploma"
        VerifiableId::class.java -> "EBSI Natural Person Verifiable ID"
        else -> null
    }

    private fun getDescriptionResolver(type: TypeScope) = when (type.type.erasedType) {
        Europass::class.java -> "Schema of an EBSI Verifiable Attestation - Diploma"
        VerifiableId::class.java -> "Schema of an EBSI Verifiable ID for a natural person"
        else -> null
    }

    private fun getPropertyNameOverrideResolver(field: FieldScope) =
        Optional.ofNullable(field.getAnnotationConsideringFieldAndGetter(PropertyName::class.java))
            .map(PropertyName::name)
            .orElse(null)

    private fun getRequiredCheck(field: FieldScope) =
        field.getAnnotationConsideringFieldAndGetter(Required::class.java) != null

    private fun getFormatResolverCheck(field: FieldScope) = when {
        field.getAnnotationConsideringFieldAndGetterIfSupported(DateTimeFormat::class.java) != null -> "date-time"
        else -> null
    }

    private fun getIgnoreCheck(field: FieldScope) =
        field.getAnnotationConsideringFieldAndGetter(JsonIgnore::class.java) != null

    fun <T : Any> generateSchema(clazz: Class<T>): String = generator.generateSchema(clazz).toPrettyString()

    fun generateSchema(vc: VerifiableCredential): String = generateSchema(vc.javaClass)

    fun validateSchema(jsonLdCredential: String): ValidationResult =
        validateSchema(jsonLdCredential, jsonLdCredential.toCredential().let {
            when (it) {
                is VerifiablePresentation -> vpSchema // for VerifiablePresentations we take the pre-baked schema
                else -> generateSchema(it)
            }
        })

    fun validateSchema(jsonLdCredential: String, schema: String) = SchemaValidatorFactory.get(schema)
        .validate(jsonLdCredential)
        .let { ValidationResult(it.isEmpty(), it.map { error -> error.toString() }.toList()) }

    val vpSchema = "{\n" +
            "  \"\$schema\" : \"http://json-schema.org/draft-07/schema#\",\n" +
            "  \"type\" : \"object\",\n" +
            "  \"properties\" : {\n" +
            "    \"@context\" : {\n" +
            "      \"type\" : \"array\",\n" +
            "      \"items\" : {\n" +
            "        \"type\" : \"string\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"holder\" : {\n" +
            "      \"type\" : \"string\"\n" +
            "    },\n" +
            "    \"id\" : {\n" +
            "      \"type\" : \"string\"\n" +
            "    },\n" +
            "    \"proof\" : {\n" +
            "      \"type\" : \"object\",\n" +
            "      \"properties\" : {\n" +
            "        \"created\" : {\n" +
            "          \"type\" : \"string\"\n" +
            "        },\n" +
            "        \"creator\" : {\n" +
            "          \"type\" : \"string\"\n" +
            "        },\n" +
            "        \"domain\" : {\n" +
            "          \"type\" : \"string\"\n" +
            "        },\n" +
            "        \"jws\" : {\n" +
            "          \"type\" : \"string\"\n" +
            "        },\n" +
            "        \"nonce\" : {\n" +
            "          \"type\" : \"string\"\n" +
            "        },\n" +
            "        \"proofPurpose\" : {\n" +
            "          \"type\" : \"string\"\n" +
            "        },\n" +
            "        \"type\" : {\n" +
            "          \"type\" : \"string\"\n" +
            "        },\n" +
            "        \"verificationMethod\" : {\n" +
            "          \"type\" : \"string\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"additionalProperties\" : false\n" +
            "    },\n" +
            "    \"type\" : {\n" +
            "      \"type\" : \"array\",\n" +
            "      \"items\" : {\n" +
            "        \"type\" : \"string\"\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  \"required\" : [ \"@context\", \"type\" ],\n" +
            "  \"additionalProperties\" : true\n" +
            "}"
}

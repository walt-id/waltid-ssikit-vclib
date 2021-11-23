package id.walt.vclib.schema

import com.github.victools.jsonschema.generator.*
import id.walt.vclib.Helpers.toCredential
import id.walt.vclib.credentials.Europass
import id.walt.vclib.credentials.VerifiableId
import id.walt.vclib.model.VerifiableCredential
import net.pwall.json.schema.JSONSchema
import java.util.*

data class ValidationResult(val valid: Boolean, val errors: List<String>? = null)

object SchemaService {

    annotation class PropertyName(val name: String)
    annotation class Required
    annotation class DateTimeFormat
    annotation class JsonIgnore

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
        validateSchema(jsonLdCredential, generateSchema(jsonLdCredential.toCredential()))

    fun validateSchema(jsonLdCredential: String, schema: String): ValidationResult {

        val parsedSchema = JSONSchema.parse(schema)
        val basicOutput = parsedSchema.validateBasic(jsonLdCredential)

        val errors = basicOutput.errors?.map { it.error }

        return ValidationResult(basicOutput.valid, errors)
    }
}

package id.walt.vclib.schema

import com.fasterxml.jackson.databind.node.ObjectNode
import com.github.victools.jsonschema.generator.*
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.vclist.Europass
import id.walt.vclib.vclist.VerifiableId
import net.pwall.json.schema.JSONSchema
import java.util.*
import kotlin.reflect.KClass

data class ValidationResult(val valid: Boolean, val errors: List<String>? = null)

object SchemaService {

    annotation class PropertyName(val name: String)
    annotation class Nullable
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
        field.getAnnotationConsideringFieldAndGetter(Nullable::class.java) == null

    private fun getFormatResolverCheck(field: FieldScope) = when {
        field.getAnnotationConsideringFieldAndGetterIfSupported(DateTimeFormat::class.java) != null -> "date-time"
        else -> null
    }

    private fun getIgnoreCheck(field: FieldScope) =
        field.getAnnotationConsideringFieldAndGetter(JsonIgnore::class.java) != null

    fun <T : Any> generateSchema(clazz: KClass<T>): ObjectNode = generator.generateSchema(clazz.java)

    fun validateSchema(vc: VerifiableCredential, schema: String): ValidationResult {
        val parsedSchema = JSONSchema.parse(schema)
        val basicOutput = parsedSchema.validateBasic(vc.json!!)

        val errors = basicOutput.errors?.map { it.error }

        return ValidationResult(basicOutput.valid, errors)
    }
}

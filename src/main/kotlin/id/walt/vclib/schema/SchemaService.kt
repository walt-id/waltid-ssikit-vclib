package id.walt.vclib.schema

import com.fasterxml.jackson.databind.node.ObjectNode
import com.github.victools.jsonschema.generator.*
import id.walt.vclib.Helpers.encode
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.vclist.Europass
import id.walt.vclib.vclist.VerifiableID
import net.pwall.json.schema.JSONSchema
import java.net.URL
import java.util.*
import kotlin.reflect.KClass

object SchemaService {

    annotation class PropertyName(val name: String)
    annotation class Nullable
    annotation class DateTimeFormat

    private val generator = SchemaGeneratorConfigBuilder(
        SchemaVersion.DRAFT_7,
        OptionPreset.PLAIN_JSON
    ).let { configBuilder ->
        configBuilder.forTypesInGeneral()
            .withTitleResolver { titleResolver(it) }
            .withDescriptionResolver { descriptionResolver(it) }
        configBuilder.forFields()
            .withPropertyNameOverrideResolver { propertyNameResolver(it) }
//            .withIgnoreCheck { it.getAnnotationConsideringFieldAndGetter(JsonIgnore::class.java) != null }
            .withRequiredCheck { it.getAnnotationConsideringFieldAndGetter(Nullable::class.java) == null } // TODO add or Json serialize null = false
            .withStringFormatResolver { formatResolvers(it) }
        SchemaGenerator(configBuilder.build())
    }

    private fun propertyNameResolver(scope: FieldScope) =
        Optional.ofNullable(scope.getAnnotationConsideringFieldAndGetter(PropertyName::class.java))
            .map(PropertyName::name)
            .orElse(null)

    private fun formatResolvers(scope: FieldScope) =
        when {
            scope.getAnnotationConsideringFieldAndGetterIfSupported(DateTimeFormat::class.java) != null -> "date-time"
            else -> null
        }

    private fun titleResolver(scope: TypeScope) = when (scope.type.erasedType) {
        Europass::class.java -> "EBSI Verifiable Attestation - Diploma"
        else -> null
    }

    private fun descriptionResolver(scope: TypeScope) = when (scope.type.erasedType) {
        Europass::class.java -> "Schema of an EBSI Verifiable Attestation - Diploma"
        else -> null
    }

    fun <T : Any> generateSchema(clazz: KClass<T>): ObjectNode = generator.generateSchema(clazz.java)

    fun validateSchema(vc: VerifiableCredential): Boolean {
        val schema = when (vc) {
            is Europass -> vc.credentialSchema!!.id
            is VerifiableID -> vc.credentialStatus!!.id
            else -> throw IllegalStateException("Data model unsupported yet.")
        }.let { URL(it).readText() }

//        val schema = File("src/test/resources/schemas/Europass-schema.json").readText()
        val output = JSONSchema.parse(schema).validateBasic(vc.encode())
        output.errors?.forEach { println("${it.error} - ${it.instanceLocation}") }

        return output.errors.isNullOrEmpty()
    }
}

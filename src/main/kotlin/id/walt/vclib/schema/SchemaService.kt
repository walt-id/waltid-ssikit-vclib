package id.walt.vclib.schema

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.node.ObjectNode
import com.github.victools.jsonschema.generator.OptionPreset
import com.github.victools.jsonschema.generator.SchemaGenerator
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder
import com.github.victools.jsonschema.generator.SchemaVersion
import id.walt.vclib.Helpers.encode
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.vclist.Europass
import id.walt.vclib.vclist.VerifiableID
import net.pwall.json.schema.JSONSchema
import java.net.URL
import kotlin.reflect.KClass

object SchemaService {

    annotation class Nullable

    private val generator = SchemaGeneratorConfigBuilder(
        SchemaVersion.DRAFT_7,
        OptionPreset.PLAIN_JSON
    ).let { configBuilder ->
        configBuilder.forFields()
            .withIgnoreCheck { it.getAnnotationConsideringFieldAndGetter(JsonIgnore::class.java) != null }
            .withRequiredCheck { it.getAnnotationConsideringFieldAndGetter(Nullable::class.java) == null }
        SchemaGenerator(configBuilder.build())
    }

    fun <T : Any> generateSchema(clazz: KClass<T>): ObjectNode = generator.generateSchema(clazz.java)

    fun validateSchema(vc: VerifiableCredential): Boolean {
        val schema = when (vc) {
            is Europass -> vc.credentialSchema!!.id
            is VerifiableID -> vc.credentialStatus!!.id
            else -> throw IllegalStateException("Data model unsupported yet.")
        }.let { URL(it).readText() }

        val output = JSONSchema.parse(schema).validateBasic(vc.encode())
        output.errors?.forEach { println("${it.error} - ${it.instanceLocation}") }

        return output.errors.isNullOrEmpty()
    }
}

package id.walt.vclib.schema

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.node.ObjectNode
import com.github.victools.jsonschema.generator.OptionPreset
import com.github.victools.jsonschema.generator.SchemaGenerator
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder
import com.github.victools.jsonschema.generator.SchemaVersion
import net.pwall.json.schema.JSONSchema
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

    fun validateSchema(schema: String, json: String): Boolean = JSONSchema.parse(schema).validate(json)
}

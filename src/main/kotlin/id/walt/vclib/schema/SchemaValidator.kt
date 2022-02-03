package id.walt.vclib.schema

interface SchemaValidator {
    fun validate(json: String): Set<Any>
}
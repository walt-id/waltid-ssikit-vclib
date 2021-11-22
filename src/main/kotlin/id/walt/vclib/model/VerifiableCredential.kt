package id.walt.vclib.model

import com.beust.klaxon.Json
import com.beust.klaxon.TypeFor
import id.walt.vclib.adapter.VCTypeAdapter
import id.walt.vclib.schema.SchemaService


@TypeFor(field = "type", adapter = VCTypeAdapter::class)
abstract class VerifiableCredential(@field:SchemaService.Required val type: List<String>) {
    @field:SchemaService.JsonIgnore
    @Json(ignored = true)
    var json: String? = null

    @field:SchemaService.JsonIgnore
    @Json(ignored = true)
    open var jwt: String? = null // the original JWT token, if credential was given in JWT format

    @Json(serializeNull = false)
    abstract var id: String?
}

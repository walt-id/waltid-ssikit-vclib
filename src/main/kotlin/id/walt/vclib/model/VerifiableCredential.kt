package id.walt.vclib.model

import com.beust.klaxon.Json
import com.beust.klaxon.TypeFor
import id.walt.vclib.adapter.VCTypeAdapter
import id.walt.vclib.schema.SchemaService


@TypeFor(field = "type", adapter = VCTypeAdapter::class)
abstract class VerifiableCredential(val type: List<String>) {
    @field:SchemaService.JsonIgnore
    @Json(ignored = true)
    var json: String? = null

    @Json(serializeNull = false)
    open var proof: Proof? = null
}
package id.walt.vclib.model

import com.beust.klaxon.TypeFor
import id.walt.vclib.adapter.VCTypeAdapter

@TypeFor(field = "type", adapter = VCTypeAdapter::class)
abstract class VerifiableCredential(val type: List<String>)

package id.walt.vclib.registry

import id.walt.vclib.model.VerifiableCredential

abstract class VerifiableCredentialMetadata(
    val type: List<String>,
    vararg val aliases: List<String> = emptyArray(),
    val template: (() -> VerifiableCredential)? = null
)


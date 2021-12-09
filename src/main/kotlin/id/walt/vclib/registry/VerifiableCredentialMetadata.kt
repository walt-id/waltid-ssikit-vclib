package id.walt.vclib.registry

import id.walt.vclib.model.CredentialSubject
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.model.VerifiableCredential2
import id.walt.vclib.model.VerifiableCredential3

abstract class VerifiableCredentialMetadata(
    val type: List<String>,
    vararg val aliases: List<String> = emptyArray(),
    val template: (() -> VerifiableCredential)? = null
)


abstract class VerifiableCredentialMetadata2(
    val type: List<String>,
    vararg val aliases: List<String> = emptyArray(),
    val template: (() -> VerifiableCredential2)? = null
)

abstract class VerifiableCredentialMetadata3(
    val type: List<String>,
    vararg val aliases: List<String> = emptyArray(),
    val template: (() -> VerifiableCredential3<out CredentialSubject>)? = null
)


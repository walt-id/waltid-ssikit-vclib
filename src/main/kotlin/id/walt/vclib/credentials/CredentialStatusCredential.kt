package id.walt.vclib.credentials

import com.beust.klaxon.Json
import id.walt.vclib.model.CredentialStatus

class CredentialStatusCredential(

) {
    @Json(serializeNull = false)
    var credentialStatus: CredentialStatus? = null
}


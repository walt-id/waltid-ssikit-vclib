package id.walt.vclib

import com.nimbusds.jwt.SignedJWT
import id.walt.vclib.credentials.*
import id.walt.vclib.model.AbstractVerifiableCredential
import id.walt.vclib.model.CredentialSubject
import id.walt.vclib.model.VerifiableCredential
import net.pwall.yaml.YAMLSimple.log


object VcUtils {

    fun getIssuer(vc: VerifiableCredential) = vc.issuer

    fun getSubject(vc: VerifiableCredential) = vc.subject

    fun getIssuanceDate(vc: VerifiableCredential) = vc.issuanceDate

    fun getValidFrom(vc: VerifiableCredential) = vc.validFrom

    fun getExpirationDate(vc: VerifiableCredential) = vc.expirationDate

    fun getCredentialSchemaUrl(vc: VerifiableCredential) = vc.credentialSchema

    fun getChallenge(vc:VerifiableCredential) = vc.challenge
}

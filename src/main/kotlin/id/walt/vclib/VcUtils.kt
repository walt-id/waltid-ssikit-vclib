package id.walt.vclib

import com.nimbusds.jwt.SignedJWT
import id.walt.vclib.credentials.*
import id.walt.vclib.model.VerifiableCredential
import net.pwall.yaml.YAMLSimple.log


object VcUtils {

    fun getIssuer(vc: VerifiableCredential): String = when (vc) {
        is Europass -> vc.issuer!!
        is VerifiableId -> vc.issuer!!
        is VerifiableDiploma -> vc.issuer!!
        is PermanentResidentCard -> vc.issuer!!
        is UniversityDegree -> vc.issuer.id
        is VerifiableAttestation -> vc.issuer
        is VerifiableAuthorization -> vc.issuer
        is VerifiablePresentation -> when (vc.jwt) {
            null -> vc.proof!!.creator!!
            else -> SignedJWT.parse(vc.jwt).jwtClaimsSet.issuer
        }
        is GaiaxCredential -> vc.issuer
        is GaiaxSD -> vc.issuer
        else -> {
            log.warn { "No getIssuer for ${vc.type.last()}!" }
            ""
        }
    }

    fun getSubject(vc: VerifiableCredential): String = when (vc) {
        is Europass -> vc.credentialSubject!!.id!!
        is VerifiableId -> vc.credentialSubject!!.id!!
        is VerifiableDiploma -> vc.credentialSubject!!.id!!
        is PermanentResidentCard -> vc.credentialSubject!!.id!!
        is UniversityDegree -> vc.credentialSubject.id
        is VerifiableAttestation -> vc.credentialSubject!!.id
        is VerifiableAuthorization -> vc.credentialSubject.id
        is GaiaxCredential -> vc.credentialSubject.id
        is GaiaxSD -> vc.credentialSubject.id
        is VerifiablePresentation -> vc.holder!!
        else -> {
            log.warn { "No getHolder for ${vc.type.last()}!" }
            ""
        }
    }

    fun getIssuanceDate(vc: VerifiableCredential) = when (vc) {
        is Europass -> vc.issuanceDate
        is VerifiableId -> vc.issuanceDate
        is VerifiableDiploma -> vc.issuanceDate
        is UniversityDegree -> vc.issuanceDate
        is VerifiableAttestation -> vc.issuanceDate
        is VerifiableAuthorization -> vc.issuanceDate
        is GaiaxCredential -> vc.issuanceDate
        is GaiaxSD -> vc.issuanceDate
        else -> {
            log.warn { "No getIssuanceDate for ${vc.type.last()}!" }
            ""
        }
    }

    fun getValidFrom(vc: VerifiableCredential) = when (vc) {
        is Europass -> vc.validFrom
        is VerifiableId -> vc.validFrom
        is VerifiableDiploma -> vc.validFrom
        is VerifiableAttestation -> vc.validFrom
        is VerifiableAuthorization -> vc.validFrom
        is GaiaxCredential -> vc.validFrom
        is GaiaxSD -> vc.validFrom
        else -> {
            log.warn { "No getValidFrom for ${vc.type.last()}!" }
            ""
        }
    }

    fun getExpirationDate(vc: VerifiableCredential) = when (vc) {
        is Europass -> vc.expirationDate
        is VerifiableId -> vc.expirationDate
        is VerifiableDiploma -> vc.expirationDate
        is VerifiableAuthorization -> vc.expirationDate
        is GaiaxCredential -> vc.expirationDate
        is GaiaxSD -> vc.expirationDate
        else -> {
            log.warn { "No getExpirationDate for ${vc.type.last()}!" }
            ""
        }
    }

    fun getCredentialSchemaUrl(vc: VerifiableCredential) = when (vc) {
        is Europass -> vc.credentialSchema
        is VerifiableId -> vc.credentialSchema
        is VerifiableDiploma -> vc.credentialSchema
        is VerifiableAttestation -> vc.credentialSchema
        is VerifiableAuthorization -> vc.credentialSchema
        else -> {
            log.warn { "No getCredentialSchema for ${vc.type.last()}!" }
            null
        }
    }

    fun getChallenge(vc:VerifiableCredential): String? = when(vc.jwt) {
        null -> when(vc) {
            is Europass -> vc.proof!!.nonce
            is VerifiableId -> vc.proof!!.nonce
            is VerifiableDiploma -> vc.proof!!.nonce
            is PermanentResidentCard -> vc.proof!!.nonce
            is UniversityDegree -> vc.proof!!.nonce
            is VerifiableAttestation -> vc.proof!!.nonce
            is VerifiableAuthorization -> vc.proof!!.nonce
            is VerifiablePresentation -> vc.proof!!.nonce
            is GaiaxCredential -> vc.proof!!.nonce
            is GaiaxSD -> vc.proof!!.nonce
            else -> {
                log.warn { "No getCredentialSchema for ${vc.type.last()}!" }
                null
            }
        }
        else -> SignedJWT.parse(vc.jwt).jwtClaimsSet.getClaim("nonce").toString()
    }
}

package id.walt.vclib

import com.nimbusds.jwt.SignedJWT
import id.walt.vclib.credentials.*
import id.walt.vclib.model.VerifiableCredential


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
        is GaiaxSelfDescription -> vc.issuer
        is GaiaxServiceOffering -> vc.issuer
        is VerifiableVaccinationCertificate -> vc.issuer!!
        is ProofOfResidence -> vc.issuer
        else -> throw Exception("No getIssuer for ${vc.type.last()}!")
    }

    fun getSubject(vc: VerifiableCredential): String = when (vc) {
        is VerifiablePresentation -> vc.holder!!
        is Europass -> vc.credentialSubject!!.id!!
        is VerifiableId -> vc.credentialSubject!!.id!!
        is VerifiableDiploma -> vc.credentialSubject!!.id!!
        is PermanentResidentCard -> vc.credentialSubject!!.id!!
        is UniversityDegree -> vc.credentialSubject.id
        is VerifiableAttestation -> vc.credentialSubject!!.id
        is VerifiableAuthorization -> vc.credentialSubject.id
        is GaiaxCredential -> vc.credentialSubject.id
        is GaiaxSelfDescription -> vc.credentialSubject.id
        is GaiaxServiceOffering -> vc.credentialSubject.id
        is VerifiableVaccinationCertificate -> vc.credentialSubject!!.id!!
        is ProofOfResidence -> vc.credentialSubject!!.id!!
        else -> throw Exception("No getSubject for ${vc.type.last()}!")
    }

    fun getIssuanceDate(vc: VerifiableCredential) = when (vc) {
        is Europass -> vc.issuanceDate
        is VerifiableId -> vc.issuanceDate
        is VerifiableDiploma -> vc.issuanceDate
        is UniversityDegree -> vc.issuanceDate
        is VerifiableAttestation -> vc.issuanceDate
        is VerifiableAuthorization -> vc.issuanceDate
        is GaiaxCredential -> vc.issuanceDate
        is VerifiableVaccinationCertificate -> vc.issuanceDate
        is GaiaxSelfDescription -> vc.issuanceDate
        is GaiaxServiceOffering -> vc.issuanceDate
        is ProofOfResidence -> vc.issuanceDate
        else -> throw Exception("No getIssuerDate for ${vc.type.last()}!")
    }

    fun getValidFrom(vc: VerifiableCredential) = when (vc) {
        is Europass -> vc.validFrom
        is VerifiableId -> vc.validFrom
        is VerifiableDiploma -> vc.validFrom
        is VerifiableAttestation -> vc.validFrom
        is VerifiableAuthorization -> vc.validFrom
        is GaiaxCredential -> vc.validFrom
        is VerifiableVaccinationCertificate -> vc.validFrom
        is GaiaxSelfDescription -> vc.validFrom
        is GaiaxServiceOffering -> vc.validFrom
        is ProofOfResidence -> vc.validFrom
        else -> throw Exception("No getValidForm for ${vc.type.last()}!")
    }

    fun getExpirationDate(vc: VerifiableCredential) = when (vc) {
        is Europass -> vc.expirationDate
        is VerifiableId -> vc.expirationDate
        is VerifiableDiploma -> vc.expirationDate
        is VerifiableAuthorization -> vc.expirationDate
        is GaiaxCredential -> vc.expirationDate
        is VerifiableVaccinationCertificate -> vc.expirationDate
        is GaiaxSelfDescription -> vc.expirationDate
        is GaiaxServiceOffering -> vc.expirationDate
        is ProofOfResidence -> vc.expirationDate
        else -> throw Exception("No getExpirationDate for ${vc.type.last()}!")
    }

    fun getCredentialSchemaUrl(vc: VerifiableCredential) = when (vc) {
        is Europass -> vc.credentialSchema
        is VerifiableId -> vc.credentialSchema
        is VerifiableDiploma -> vc.credentialSchema
        is VerifiableAttestation -> vc.credentialSchema
        is VerifiableAuthorization -> vc.credentialSchema
        is VerifiableVaccinationCertificate -> vc.credentialSchema
        is ProofOfResidence -> vc.credentialSchema
        else -> throw Exception("No getCredentialSchemaUrl for ${vc.type.last()}!")
    }

    fun getChallenge(vc: VerifiableCredential): String? = when (vc.jwt) {
        null -> when (vc) {
            is Europass -> vc.proof!!.nonce
            is VerifiableId -> vc.proof!!.nonce
            is VerifiableDiploma -> vc.proof!!.nonce
            is PermanentResidentCard -> vc.proof!!.nonce
            is UniversityDegree -> vc.proof!!.nonce
            is VerifiableAttestation -> vc.proof!!.nonce
            is VerifiableAuthorization -> vc.proof!!.nonce
            is VerifiablePresentation -> vc.proof!!.nonce
            is GaiaxCredential -> vc.proof!!.nonce
            is VerifiableVaccinationCertificate -> vc.proof!!.nonce
            is GaiaxSelfDescription -> vc.proof!!.nonce
            is GaiaxServiceOffering -> vc.proof!!.nonce
            is ProofOfResidence -> vc.proof!!.nonce
            else -> throw Exception("No getChallenge for ${vc.type.last()}!")
        }
        else -> SignedJWT.parse(vc.jwt).jwtClaimsSet.getClaim("nonce").toString()
    }
}

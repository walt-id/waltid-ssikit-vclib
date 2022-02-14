package id.walt.vclib.credentials


import id.walt.vclib.model.CredentialSchema
import io.kotest.core.spec.style.StringSpec
import org.junit.jupiter.api.Assertions.assertEquals

class BasePropertiesTest : StringSpec({
    val date = "2019-06-22T14:11:44Z"

    "getIssued returns null when it does not exist" {
        assertEquals(null, VerifiableDiploma().issued)
    }

    "getIssued returns the issued date when it exists" {
        assertEquals(date, VerifiableDiploma(issued = date).issued)
    }

    "getValidFrom & getIssuanceDate returns null when it does not exist" {
        assertEquals(null, VerifiableDiploma().validFrom)
        assertEquals(null, VerifiableDiploma().issuanceDate)
    }

    "getValidFrom & getIssuanceDate returns valid from when it exists" {
        assertEquals(date, VerifiableDiploma(validFrom = date).validFrom)
        assertEquals(date, VerifiableDiploma(validFrom = date).issuanceDate)
    }

    "getExpirationDate returns null when it does not exist" {
        assertEquals(null, VerifiableDiploma().expirationDate)
    }

    "getExpirationDate returns the expiration date when it exists" {
        assertEquals(date, VerifiableDiploma(expirationDate = date).expirationDate)
    }

    "getCredentialSchema returns the credentialSchema attribute value when it exists" {
        assertEquals(null, VerifiableDiploma().credentialSchema)
        CredentialSchema(id = "id", type = "type").let {
            assertEquals(it, VerifiableDiploma(credentialSchema = it).credentialSchema)
        }
    }
})

package id.walt.vclib.credentials


import id.walt.vclib.model.CredentialSchema
import io.kotest.core.spec.style.StringSpec
import org.junit.jupiter.api.Assertions.assertEquals

class BasePropertiesTest : StringSpec({
    val date = "2019-06-22T14:11:44Z"

    "getIssuanceDate returns null when it does not exist" {
        assertEquals(null, VerifiableDiploma().issuanceDate)
    }

    "getIssuanceDate returns the issuance date when it exists" {
        assertEquals(date, VerifiableDiploma(issuanceDate = date).issuanceDate)
    }

    "getValidFrom returns null when it does not exist" {
        assertEquals(null, VerifiableDiploma().issuanceDate)
    }

    "getValidFrom returns valid from when it exists" {
        assertEquals(date, VerifiableDiploma(validFrom = date).validFrom)
    }

    "getExpirationDate returns null when it does not exist" {
        assertEquals(null, VerifiableDiploma().validFrom)
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

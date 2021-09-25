package id.walt.vclib.vcs

import id.walt.vclib.Helpers.encode
import id.walt.vclib.model.CredentialSchema
import id.walt.vclib.vclist.VerifiableId
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.StringSpec
import java.io.File

class SerializeVerifiableId : StringSpec({
    "serialize verifiableID" {
        val verifiableId = VerifiableId(
            credentialSubject = VerifiableId.CredentialSubject(
                familyName = "DOE",
                firstName = "Jane",
                dateOfBirth = "1993-04-08T00:00:00Z",
                personalIdentifier = "0904008084H",
            ),
            credentialSchema = CredentialSchema(
                id = "https://api.preprod.ebsi.eu/trusted-schemas-registry/v1/schemas/0x2488fd38783d65e4fd46e7889eb113743334dbc772b05df382b8eadce763101b",
                type = "JsonSchemaValidator2018"
            ),
            evidence = VerifiableId.Evidence(
                id = "https://essif.europa.eu/tsr-vid/evidence/f2aeec97-fc0d-42bf-8ca7-0548192d4231",
                type = listOf("DocumentVerification"),
                verifier = "did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9",
                evidenceDocument = listOf("Passport"),
                subjectPresence = "Physical",
                documentPresence = listOf("Physical")
            )
        )
        val verifiableIdJson = verifiableId.encode()

        verifiableIdJson shouldEqualJson File("src/test/resources/serialized/VerifiableId.json").readText()
    }
})

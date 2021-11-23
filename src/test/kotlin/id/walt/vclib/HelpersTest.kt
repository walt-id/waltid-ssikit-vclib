package id.walt.vclib

import com.beust.klaxon.Json
import id.walt.vclib.Helpers.toCredential
import id.walt.vclib.Helpers.toMap
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.credentials.VerifiableDiploma
import id.walt.vclib.credentials.VerifiableId
import id.walt.vclib.credentials.VerifiablePresentation
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import java.io.File
import java.nio.file.Path

private const val V_ID_ID = "identity#verifiableID#93308ff1-c335-43c0-94da-a6863fb4bb9d"
private const val V_DIPLOMA_ID = "education#higherEducation#87ED2F2270E6C41456E94B86B9D9115B4E35BCCAD200A49B846592C14F79C86BV1Fnbllta0NZTnJkR3lDWlRmTDlSRUJEVFZISmNmYzJhUU5sZUJ5Z2FJSHpWbmZZ"
private const val V_PRESENTATION_ID = "urn:uuid:935c1af4-1ffa-4698-b509-807bed675123"
private const val ISSUER_DID = "did:ebsi:z242pBrKr3KdTwEEZxEdVtVq"
private const val SUBJECT_DID = "did:ebsi:zsT2x3C1LZpsYmztjzjL354"
private const val ISSUANCE_DATE = "2021-08-20T00:00:00Z"
private const val VALID_FROM = "2020-06-01T00:00:00Z"

class HelpersTest : StringSpec({
    "test toMap()" {
        val dummyVcMap = DummyCredential().toMap()

        dummyVcMap["@context"] shouldBe listOf("https://www.w3.org/2018/credentials/v1")
        dummyVcMap["type"] shouldBe listOf("VerifiableCredential", "VerifiableAttestation", "DummyCredential")

        dummyVcMap.containsKey("id") shouldBe false
    }

    "test toCredential JWT Proof VerifiableId striped of redundant claims" {
        val vId = File(Path.of("src", "test", "resources", "jwt", "VerifiableId.txt").toUri())
            .readText()
            .toCredential() as VerifiableId

        vId.id shouldBe V_ID_ID
        vId.issuer shouldBe ISSUER_DID
        vId.issuanceDate shouldBe ISSUANCE_DATE
        vId.validFrom shouldBe VALID_FROM
        vId.expirationDate shouldBe null
        vId.credentialSubject!!.id shouldBe SUBJECT_DID

        vId.json shouldContain "\"id\" : \"$V_ID_ID\""
        vId.json shouldContain "\"issuer\" : \"$ISSUER_DID\""
        vId.json shouldContain "\"issuanceDate\" : \"$ISSUANCE_DATE\""
        vId.json shouldContain "\"validFrom\" : \"$VALID_FROM\""
        vId.json shouldNotContain "\"expirationDate\""
        vId.json shouldContain "\"id\" : \"$SUBJECT_DID\""
    }

    "test toCredential JWT Proof VerifiableDiploma striped of redundant claims" {
        val vDiploma = File(Path.of("src", "test", "resources", "jwt", "VerifiableDiploma.txt").toUri())
            .readText()
            .toCredential() as VerifiableDiploma

        vDiploma.id shouldBe V_DIPLOMA_ID
        vDiploma.issuer shouldBe ISSUER_DID
        vDiploma.issuanceDate shouldBe ISSUANCE_DATE
        vDiploma.validFrom shouldBe VALID_FROM
        vDiploma.expirationDate shouldBe null
        vDiploma.credentialSubject!!.id shouldBe SUBJECT_DID

        vDiploma.json shouldContain "\"id\" : \"$V_DIPLOMA_ID\""
        vDiploma.json shouldContain "\"issuer\" : \"$ISSUER_DID\""
        vDiploma.json shouldContain "\"issuanceDate\" : \"$ISSUANCE_DATE\""
        vDiploma.json shouldContain "\"validFrom\" : \"$VALID_FROM\""
        vDiploma.json shouldNotContain "\"expirationDate\""
        vDiploma.json shouldContain "\"id\" : \"$SUBJECT_DID\""
    }

    "test toCredential JWT Proof VerifiablePresentation striped of redundant claims" {
        val vp = File(Path.of("src", "test", "resources", "jwt", "VerifiablePresentation.txt").toUri())
            .readText()
            .toCredential() as VerifiablePresentation

        vp.id shouldBe V_PRESENTATION_ID
        vp.holder shouldBe SUBJECT_DID

        vp.json shouldContain "\"id\" : \"$V_PRESENTATION_ID\""
        vp.json shouldContain "\"holder\" : \"$SUBJECT_DID\""
    }
}) {
    data class DummyCredential(
        @Json(name = "@context") var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
        @Json(serializeNull = false) override var id: String? = null
    ) : VerifiableCredential(type) {
        companion object :
            VerifiableCredentialMetadata(listOf("VerifiableCredential", "VerifiableAttestation", "DummyCredential"))
    }
}

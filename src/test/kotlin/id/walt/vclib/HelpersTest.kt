package id.walt.vclib

import com.beust.klaxon.Json
import id.walt.vclib.credentials.VerifiableDiploma
import id.walt.vclib.credentials.VerifiableId
import id.walt.vclib.credentials.VerifiablePresentation
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import java.io.File
import java.nio.file.Path

private const val V_ID_ID = "urn:uuid:37cc799f-d953-4768-85f9-819c2a8269ca"
private const val V_DIPLOMA_ID = "urn:uuid:daaabf2c-57c3-4b6a-b3df-e93f9d125793"
private const val V_PRESENTATION_ID = "urn:uuid:931abe09-0f31-4168-a2f9-5608bf50680a"
private const val ISSUER_DID = "did:key:z6MkmW2uWYejevVDbqfmHFtkz3kHfR6wQLqV41xmbPAq9N1m"
private const val SUBJECT_DID = "did:key:z6MkmW2uWYejevVDbqfmHFtkz3kHfR6wQLqV41xmbPAq9N1m"
private const val ISSUANCE_DATE = "2020-11-03T00:00:00Z"
private const val VALID_FROM = "2020-10-31T00:00:00Z"

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
        vId.issued shouldBe ISSUANCE_DATE
        vId.issuanceDate shouldBe VALID_FROM
        vId.validFrom shouldBe VALID_FROM
        vId.expirationDate shouldBe null
        vId.credentialSubject!!.id shouldBe SUBJECT_DID

        vId.json shouldContain "\"id\" : \"$V_ID_ID\""
        vId.json shouldContain "\"issuer\" : \"$ISSUER_DID\""
        vId.json shouldContain "\"issued\" : \"$ISSUANCE_DATE\""
        vId.json shouldContain "\"issuanceDate\" : \"$VALID_FROM\""
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
        vDiploma.issued shouldBe ISSUANCE_DATE
        vDiploma.issuanceDate shouldBe VALID_FROM
        vDiploma.validFrom shouldBe VALID_FROM
        vDiploma.expirationDate shouldBe null
        vDiploma.credentialSubject!!.id shouldBe SUBJECT_DID

        vDiploma.json shouldContain "\"id\" : \"$V_DIPLOMA_ID\""
        vDiploma.json shouldContain "\"issuer\" : \"$ISSUER_DID\""
        vDiploma.json shouldContain "\"issued\" : \"$ISSUANCE_DATE\""
        vDiploma.json shouldContain "\"issuanceDate\" : \"$VALID_FROM\""
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
        @Json(serializeNull = false) override var id: String? = null,
        @Json(serializeNull = false) override var issuer: String? = null,
        @Json(serializeNull = false) override var issued: String? = null,
        @Json(serializeNull = false) override var validFrom: String? = null,
        @Json(serializeNull = false) override var expirationDate: String? = null,
        @Json(serializeNull = false) override var credentialSchema: CredentialSchema? = null,
        @Json(serializeNull = false) override var proof: Proof? = null,
        @Json(serializeNull = false) override var credentialSubject: DummySubject? = null
    ) : AbstractVerifiableCredential<DummyCredential.DummySubject>(type) {
        data class DummySubject(override var id: String?) : CredentialSubject()
        companion object :
            VerifiableCredentialMetadata(listOf("VerifiableCredential", "VerifiableAttestation", "DummyCredential"))
    }
}

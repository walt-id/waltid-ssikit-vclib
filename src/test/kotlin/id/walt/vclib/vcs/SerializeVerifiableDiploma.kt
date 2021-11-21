package id.walt.vclib.vcs

import id.walt.vclib.Helpers.encode
import id.walt.vclib.vclist.VerifiableDiploma
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.StringSpec
import java.io.File

class SerializeVerifiableDiploma : StringSpec({
    "serialize VerifiableDiploma" {
        val vc = VerifiableDiploma.template?.invoke()!!
        val verifiableDiplomaJson = vc.encode()
        // File("src/test/resources/serialized/VerifiableDiploma.json").writeText(verifiableDiplomaJson)
        verifiableDiplomaJson shouldEqualJson File("src/test/resources/serialized/VerifiableDiploma.json").readText()
    }
})

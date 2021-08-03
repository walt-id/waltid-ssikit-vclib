package id.walt.vclib.vclist


import com.beust.klaxon.Json
import id.walt.vclib.model.Proof
import id.walt.vclib.model.VerifiableCredential
import id.walt.vclib.registry.VerifiableCredentialMetadata

data class UniversityDegree(
    @Json(name = "@context")
    var context: List<String> = listOf(
        "https://www.w3.org/2018/credentials/v1",
        "https://www.w3.org/2018/credentials/examples/v1"
    ),
    var id: String, // http://example.gov/credentials/3732
    var issuer: Issuer,
    var issuanceDate: String, // 2020-03-10T04:24:12.164Z
    var credentialSubject: CredentialSubject,
    var proof: Proof
) : VerifiableCredential(type) {
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "UniversityDegreeCredential"),
    )

    data class Issuer(
        var id: String // did:web:vc.transmute.world
    )

    data class CredentialSubject(
        var id: String, // did:example:ebfeb1f712ebc6f1c276e12ec21
        var degree: Degree
    ) {
        data class Degree(
            var type: String, // BachelorDegree
            var name: String // Bachelor of Science and Arts
        )
    }
}

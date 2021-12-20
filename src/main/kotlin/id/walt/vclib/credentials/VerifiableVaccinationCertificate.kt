package id.walt.vclib.credentials

import com.beust.klaxon.Json
import com.nimbusds.jwt.SignedJWT
import id.walt.vclib.model.*
import id.walt.vclib.registry.VerifiableCredentialMetadata
import id.walt.vclib.schema.SchemaService.JsonIgnore
import id.walt.vclib.schema.SchemaService.PropertyName
import id.walt.vclib.schema.SchemaService.Required
import java.text.SimpleDateFormat
import java.util.*

data class VerifiableVaccinationCertificate(
    @Json(name = "@context") @field:PropertyName(name = "@context") @field:Required
    var context: List<String> = listOf("https://www.w3.org/2018/credentials/v1"),
    @Json(serializeNull = false) override var id: String? = null, // education#higherEducation#51e42fda-cb0a-4333-b6a6-35cb147e1a88
    @Json(serializeNull = false) override var issuer: String? = null, // did:ebsi:2LGKvDMrNUPR6FhSNrXzQQ1h295zr4HwoX9UqvwAsenSKHe9
    @Json(serializeNull = false) override var issuanceDate: String? = null, // 2020-11-03T00:00:00Z
    @Json(serializeNull = false) override var validFrom: String? = null, // 2020-11-03T00:00:00Z
    @Json(serializeNull = false) override var expirationDate: String? = null,
    override var credentialSubject: VaccinationCredentialSubject? = null,
    override var credentialSchema: CredentialSchema? = null,
    @Json(serializeNull = false) override var proof: Proof? = null,
    @Json(serializeNull = false) var evidence: Evidence? = null,
    @Json(serializeNull = false) var credentialStatus: CredentialStatus? = null
) : AbstractVerifiableCredential<VerifiableVaccinationCertificate.VaccinationCredentialSubject>(type) {
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "VerifiableAttestation", "VerifiableVaccinationCertificate"),
        template = {
            VerifiableVaccinationCertificate(
                id = "covidvaccination#392ac7f6-399a-437b-a268-4691ead8f176",
                issuer = "did:ebsi:2A9BZ9SUe6BatacSpvs1V5CdjHvLpQ7bEsi2Jb6LdHKnQxaN",
                issuanceDate = "2021-08-31T00:00:00Z",
                expirationDate = "2022-08-31T00:00:00Z",
                validFrom = "2021-08-31T00:00:00Z",
                credentialSubject = VaccinationCredentialSubject(
                    id = "did:ebsi:2AEMAqXWKYMu1JHPAgGcga4dxu7ThgfgN95VyJBJGZbSJUtp",
                    givenNames = "Jane",
                    familyName = "DOE",
                    dateOfBirth = "1993-04-08",
                    personIdentifier = "optional The type of identifier and identifier of the person, according to the policies applicable in each country. Examples are citizen ID and/or document number (ID- card/passport) or identifier within the health system/IIS/e-registry.",
                    personSex = "optional",
                    vaccinationProphylaxisInformation = listOf(
                        VaccinationCredentialSubject.VaccinationProphylaxisInformation(
                            VaccinationCredentialSubject.VaccinationProphylaxisInformation.DiseaseOrAgentTargeted(
                                code = "840539006",
                                system = "2.16.840.1.113883. 6.96",
                                version = "2021-01-31"
                            ),
                            vaccineOrProphylaxis = "1119349007 COVID-19 example vaccine",
                            vaccineMedicinalProduct = "VACCINE concentrate for dispersion for injection",
                            marketingAuthorizationHolder = "Example Vaccine Manufacturing Company",
                            doseNumber = "1",
                            totalSeriesOfDoses = "2",
                            batchNumber = "optional 1234",
                            dateOfVaccination = "2021-02-12",
                            administeringCentre = "Name/code of administering centre or a health authority responsible for the vaccination event",
                            countryOfVaccination = "DE",
                            nextVaccinationDate = "optional - 2021-03-28"
                        )
                    ),
                    uniqueCertificateIdentifier = "UVCI0904008084H"
                ),
                credentialSchema = CredentialSchema(
                    id = "https://api.preprod.ebsi.eu/trusted-schemas-registry/v1/schemas/0xbf78fc08a7a9f28f5479f58dea269d3657f54f13ca37d380cd4e92237fb691dd",
                    type = "JsonSchemaValidator2018"
                ),
                credentialStatus = CredentialStatus(
                    id = "https://essif.europa.eu/status/covidvaccination#392ac7f6-399a-437b-a268-4691ead8f176",
                    type = "CredentialStatusList2020"
                ),
                evidence = Evidence(
                    id = "https://essif.europa.eu/tsr-va/evidence/f2aeec97-fc0d-42bf-8ca7-0548192d5678",
                    type = listOf("DocumentVerification"),
                    verifier = "did:ebsi:2962fb784df61baa267c8132497539f8c674b37c1244a7a",
                    evidenceDocument = listOf("Passport"),
                    subjectPresence = "Physical",
                    documentPresence = listOf("Physical")
                )
            )
        }
    )

    data class VaccinationCredentialSubject (
        @Json(serializeNull = false) override var id: String? = null,
        @Json(serializeNull = false) var givenNames: String? = null,
        @Json(serializeNull = false) var familyName: String? = null,
        @Json(serializeNull = false) var dateOfBirth: String? = null,
        @Json(serializeNull = false) var personIdentifier: String? = null,
        @Json(serializeNull = false) var personSex: String? = null,
        var vaccinationProphylaxisInformation: List<VaccinationProphylaxisInformation>? = null,
        @Json(serializeNull = false) var uniqueCertificateIdentifier: String? = null,
    ) : id.walt.vclib.model.CredentialSubject() {

        data class VaccinationProphylaxisInformation(
            var diseaseOrAgentTargeted: DiseaseOrAgentTargeted,
            @Json(serializeNull = false) var vaccineOrProphylaxis: String? = null,
            @Json(serializeNull = false) var vaccineMedicinalProduct: String? = null,
            @Json(serializeNull = false) var marketingAuthorizationHolder: String? = null,
            @Json(serializeNull = false) var doseNumber: String? = null,
            @Json(serializeNull = false) var totalSeriesOfDoses: String? = null,
            @Json(serializeNull = false) var batchNumber: String? = null,
            @Json(serializeNull = false) var dateOfVaccination: String? = null,
            @Json(serializeNull = false) var administeringCentre: String? = null,
            @Json(serializeNull = false) var countryOfVaccination: String? = null,
            @Json(serializeNull = false) var nextVaccinationDate: String? = null
        ) {
            data class DiseaseOrAgentTargeted(
                @Json(serializeNull = false) var code: String? = null,
                @Json(serializeNull = false) var system: String? = null,
                @Json(serializeNull = false) var version: String? = null
            )
        }
    }

    data class Evidence(
        @Json(serializeNull = false) var id: String? = null,
        @Json(serializeNull = false) var type: List<String?>? = null,
        @Json(serializeNull = false) var verifier: String? = null,
        @Json(serializeNull = false) var evidenceDocument: List<String?>? = null,
        @Json(serializeNull = false) var subjectPresence: String? = null,
        @Json(serializeNull = false) var documentPresence: List<String?>? = null
    )
}

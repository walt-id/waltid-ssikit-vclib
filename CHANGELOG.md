# Changelog

All notable changes to this project will be documented in this file.

## [Unreleased]

## [1.17.0] - 2022-03-17

-   Updated Gaia-X Participant Credential according to <https://github.com/walt-id/waltid-ssikit-vclib/issues/43>

## [1.16.0] - 2022-03-12

-   Added missing fields to Europass Credential <https://github.com/walt-id/waltid-ssikit-vclib/pull/41>

## [1.15.0] - 2022-02-16

-   Add missing fields to europass learning specification <https://github.com/walt-id/waltid-ssikit-vclib/pull/40>

## [1.4.1] - 2022-02-03

## [1.14.0] - 2022-02-03

-   Europass update <https://github.com/walt-id/waltid-ssikit-vclib/pull/35> thanks to @ohylli
-   Support of recursive references in JSON schema validation by @atuffreau-bcd <https://github.com/walt-id/waltid-ssikit-vclib/pull/36>

## [1.13.0] - 2022-01-31

## [1.12.0] - 2022-01-27

-   <https://github.com/walt-id/waltid-ssikit-vclib/pull/33>
-   Updated Eurpass and ParticipantCredential

## [1.11.1] - 2022-01-24

-   Remove dummy properties from gaiax kyc credential

## [1.11.0] - 2022-01-24

-   Use github links for credential schema ids, where the actual schema id is not yet known

## [1.10.0] - 2022-01-20

-   Renamed EuropeanBankCredential to EuropeanBankIdentity
-   Pretty VC encoding by: encodePretty()

## [1.9.0] - 2022-01-19

-   Added EuropeanBankCredential

## [1.8.0] - 2022-01-13

-   Moved Gaiax credentails to a dedicated folder
-   Following Gaiax credentials were added (data-models not finalized)
    -   DataConsortium
    -   DataSelfDescription
    -   Iso27001Credential
    -   KybCredential
    -   KybMonoCredential
    -   KycCredential
    -   ParticipantCredential

## [1.7.1] - 2022-01-05

-   fix VC challenge property

## [1.7.0] - 2022-01-03

-   Refactoring of VerifiableCredential data model
-   removal of redundant helper and utility objects (VcLibManager, VcUtils, Helpers and Defaults)

## [1.6.3] - 2021-12-28

-   Added ProofOfResidence credential

## [1.6.2] - 2021-12-03

-   Fixed Json-schema verification for VerifiablePresentations
-   Added VerifiableVaccinationCertificate 
-   Addded GaiaxServiceOffering

## [1.6.1] - 2021-11-30

-   Added GaiaxSelfDescription credential

## [1.6.0] - 2021-11-24

-   Introduced 'Required' annotation for schema generation
-   Added FORBIDDEN_ADDITIONAL_PROPERTIES_BY_DEFAULT for all generated json schemas
-   Added schema-validation function to SchemaService
-   Updated template of VerifiableDiploma & University Degree
-   Added VcUtils (previously in SSI Kit)

## [1.5.4] - 2021-11-20

-   Added eidasLegalIdentifier to VerifiableDiploma

## [1.5.3] - 2021-11-07

-   Added DNSpublicKey to GaiaxCredential

## [1.5.2] - 2021-11-06

-   Removed proofs from templates

## [1.5.1] - 2021-11-06

-   Added GaiaxCredential
-   Added credentialStatus (CredentialStatusList2020) to GaiaxCredential

## [1.5.0] - 2021-11-02

-   Fix/ebsi jwt to credential
-   Fix/get verifiable credential string

## [1.4.9] - 2021-09-25

-   As issuanceDate and expirationDate, validFrom may now have also a non serialized null value according EBSI context.

## [1.4.8] - 2021-09-24

-   Updated VP data model according to EBSI spec
-   expose optional id field on VerifiableCredential abstract interface

## [1.4.7] - 2021-09-13

-   Fix JWT claim named "vp" for verifiable presentation according to EU spec

## [1.4.6] - 2021-09-09

-   JWT-aware credential parsing
-   preserve original credential JSON body and JWT (for JWT credentials)
-   support parsing verifiable presentations with nested VCs in JSON-LD or JWT format

## [1.4.5] - 2021-09-01

-   Changed default data for Verifiable ID, Verifiable Diploma and Europass credential templates

## [1.4.4] - 2021-08-26

## [1.4.4] - 2021-08-26

-   Added credential template for Verifiable Diplomas
-   Updated Europass & Verifiable ID data model in aligned with the EBSI Schemas
-   Improved Schema Generator

## [1.4.3] - 2021-08-19

-   Added VerifiableID model

## [1.4.2] - 2021-08-18

## [1.4.1] - 2021-08-16

## [1.4.0] - 2021-08-14

-   Make TemplateManager usable with template ids
    -   ```kotlin
        VcTemplateManager.loadTemplate("Europass")
        VcTemplateManager.loadTemplate(listOf("VerifiableCredential, VerifiableAttestation, Europass"))
        ```
-   Added template id listing
-   Added toMap method toMap() for VCs
-

## [1.3.0] - 2021-07-28

-   reworked VC registration to allow for VC metadata
-   moved TemplateManager VC-Lib (see id.walt.vclib.templates.VcTemplateManager)

## [1.2.0] - 2021-07-28

### Changed

-   All credential now have a common proof
-   further testing

## [1.1.0] - 2021-07-28

### Changed

-   All credential attributes can now be changed dynamically without recreating the credential instance

## [1.0.0] - 2021-07-28

### Added

-   Initial release

[Unreleased]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.17.0...HEAD

[1.17.0]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.16.0...1.17.0

[1.16.0]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.15.0...1.16.0

[1.15.0-SNAPSHOT]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.15.0-SNAPSHOT...1.15.0-SNAPSHOT

[1.15.0-SNAPSHOT]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.15.0-SNAPSHOT...1.15.0-SNAPSHOT

[1.15.0-SNAPSHOT]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.15.0...1.15.0-SNAPSHOT

[1.15.0]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.1...1.15.0

[1.4.1]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.14.0...1.4.1

[1.14.0]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.13.0...1.14.0

[1.13.0]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.12.0...1.13.0

[1.12.0]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.11.1...1.12.0

[1.11.1]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.11.0...1.11.1

[1.11.0]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.10.0...1.11.0

[1.10.0]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.9.0...1.10.0

[1.9.0]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.8.0...1.9.0

[1.8.0]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.7.1...1.8.0

[1.7.1]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.7.0...1.7.1

[1.7.0]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.6.3...1.7.0

[1.6.3]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.6.2...1.6.3

[1.6.2]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.6.1...1.6.2

[1.6.1]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.6.0...1.6.1

[1.6.0]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.5.4...1.6.0

[1.5.4]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.5.3...1.5.4

[1.5.3]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.5.2...1.5.3

[1.5.2]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.5.1...1.5.2

[1.5.1]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.5.0...1.5.1

[1.5.0]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.5.0...1.5.0

[1.5.0]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.9...1.5.0

[1.4.9]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.8...1.4.9

[1.4.7]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.6...1.4.7

[1.4.6]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.5...1.4.6

[1.4.5]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.4...1.4.5

[1.4.4]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.4...1.4.4

[1.4.4]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.3...1.4.4

[1.4.3]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.2...1.4.3

[1.4.2]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.1...1.4.2

[1.4.1]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.0...1.4.1

[1.4.0]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.3.0...1.4.0

[1.4.2]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.3.0...1.4.2

[1.3.0]: https://github.com/letstrustid/waltid-ssikit-vclib/compare/1.2.0...1.3.0

[1.2.0]: https://github.com/letstrustid/waltid-ssikit-vclib/compare/1.1.0...1.2.0

[1.1.0]: https://github.com/letstrustid/waltid-ssikit-vclib/compare/1.0.0...1.1.0

[1.0.0]: https://github.com/letstrustid/waltid-ssikit-vclib/compare/1.0.0...1.0.0

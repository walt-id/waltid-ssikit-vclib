# Changelog

All notable changes to this project will be documented in this file.

## [Unreleased]

-   Make TemplateManager usable with template ids
    -   ```kotlin
        VcTemplateManager.loadTemplate("Europass")
        VcTemplateManager.loadTemplate(listOf("VerifiableCredential, VerifiableAttestation, Europass"))
        ```
-   Added template id listing
    -   ```kotlin
        VcTemplateManager.getTemplateList()
        ```
-   Added toMap method for VCs
    -   ```kotlin
        VerifiableCredential.toMap(): Map<String, Any>
        ```

## [1.3.0] - 2021-07-28

-   reworked VC registration to allow for VC metadata, e.g.

    ```kotlin
    // => Registration
    VcLibManager.register<UniversityDegree>(UniversityDegree)

    // OR VcLibManager.register<UniversityDegree>(UniversityDegree.Companion)

    // => Companion object of VC
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "UniversityDegreeCredential"),
    )
    ```
-   moved TemplateManager to Walt.ID VC-Lib (see id.walt.vclib.templates.VcTemplateManager)

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

[Unreleased]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.4.2...HEAD

[1.4.2]: https://github.com/walt-id/waltid-ssikit-vclib/compare/1.3.0...1.4.2

[1.3.0]: https://github.com/letstrustid/waltid-ssikit-vclib/compare/1.2.0...1.3.0

[1.2.0]: https://github.com/letstrustid/waltid-ssikit-vclib/compare/1.1.0...1.2.0

[1.1.0]: https://github.com/letstrustid/waltid-ssikit-vclib/compare/1.0.0...1.1.0

[1.0.0]: https://github.com/letstrustid/waltid-ssikit-vclib/compare/1.0.0...1.0.0

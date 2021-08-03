# Changelog

All notable changes to this project will be documented in this file.

## [Unreleased]

- reworked VC registration to allow for VC metadata, e.g.
    ```kotlin
    // => Registration
    VcLibManager.register<UniversityDegree>(UniversityDegree)
  
    // OR VcLibManager.register<UniversityDegree>(UniversityDegree.Companion)

    // => Companion object of VC
    companion object : VerifiableCredentialMetadata(
        type = listOf("VerifiableCredential", "UniversityDegreeCredential"),
    )
    ```
- moved TemplateManager to Walt.ID VC-Lib (see id.walt.vclib.templates.VcTemplateManager)

## [1.2.0] - 2021-07-28

### Changed

- All credential now have a common proof
- further testing


## [1.1.0] - 2021-07-28

### Changed

- All credential attributes can now be changed dynamically without recreating the credential instance

## [1.0.0] - 2021-07-28

### Added

- Initial release

[Unreleased]: https://github.com/letstrustid/waltid-ssikit-vclib/compare/1.1.0...HEAD

[1.2.0]: https://github.com/letstrustid/waltid-ssikit-vclib/compare/1.1.0...1.2.0
[1.1.0]: https://github.com/letstrustid/waltid-ssikit-vclib/compare/1.0.0...1.1.0
[1.0.0]: https://github.com/letstrustid/waltid-ssikit-vclib/compare/1.0.0...1.0.0

# waltid-ssikit-vclib
Typesafe implementation of W3C Verifiable Credentials in order to facilitate interoperability among various applications.


### Setup

Add the dependency using Gradle:

    implementation("id.walt:waltid-ssikit-vclib:1.5.3")
    
or Maven:

    <dependency>
        <groupId>id.walt</groupId>
        <artifactId>waltid-ssikit-vclib</artifactId>
        <version>1.5.3</version>
    </dependency>
    
### Create a credential
```kotlin
val verifiableAuthorization: VerifiableCredential = VerifiableAuthorization(
    id = "did:ebsi-eth:00000001/credentials/1872",
    issuer = "did:ebsi:000001234",
    issuanceDate = "2020-08-24T14:13:44Z",
    credentialSubject = VerifiableAuthorization.CredentialSubject1(
        "did:ebsi:00000004321",
        VerifiableAuthorization.CredentialSubject1.NaturalPerson("did:example:00001111")
    ),
    proof = Proof(
        "EcdsaSecp256k1Signature2019",
        "2020-08-24T14:13:44Z",
        "assertionMethod",
        "did:ebsi-eth:000001234#key-1",
        "eyJhbGciOiJSUzI1NiIsImI2NCI6ZmFsc2UsImNyaXQiOlsiYjY0Il19."
    )
)
```

### Encode a credential to JSON
```kotlin
val json: String = verifiableAuthorization.encode()
// {"@context" : ["https://www.w3.org/2018/credentials/v1", "https://ess ...
```

### Decode a JSON credential
```kotlin
val unknownJson: String = fromInput()

val credential: VerifiableCredential = unknownJson.toCredential()

val issuer = when (credential) {
    is PermanentResidentCard -> credential.issuer
    is VerifiablePresentation -> {
        val vcsInVP = credential.verifiableCredential
        val permanentResidentCardInVP = vcsInVP.any { it is PermanentResidentCard }

        if (permanentResidentCardInVP) {
            (vcsInVP.first { it is PermanentResidentCard } as PermanentResidentCard).issuer
        } else throw IllegalArgumentException("This VP does not contain a PermanentResidentCard")
    }
    else -> throw IllegalArgumentException("No vc of type PermanentResidentCard was found!")
}

// extendable to e.g. take the users address from the PermanentResidentCard, or an Europass (if supplied), or a VerifiableUtilityBill etc...
```

## License

The VcLib by walt.id is Open Source software released under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0.html).

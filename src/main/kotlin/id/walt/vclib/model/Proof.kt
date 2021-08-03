package id.walt.vclib.model

import com.beust.klaxon.Json

data class Proof(
    @Json(serializeNull = false) val type: String? = null, // "Ed25519Signature2018"
    @Json(serializeNull = false) val creator: String? = null, //did:ebsi:2sMvVBpwueU8j6WBnJcUAkcNnPXLQvGy3a6a3X59wKRnJzZQ
    @Json(serializeNull = false) val created: String? = null, //"2020-04-22T10:37:22Z",
    @Json(serializeNull = false) val proofPurpose: String? = null, //"assertionMethod",
    @Json(serializeNull = false) val verificationMethod: String? = null, //"did:example:456#key-1",
    @Json(serializeNull = false) val jws: String? = null, //"eyJjcml0IjpbImI2NCJdLCJiNjQiOmZhbHNlLCJhbGciOiJFZERTQSJ9..BhWew0x-txcroGjgdtK-yBCqoetg9DD9SgV4245TmXJi-PmqFzux6Cwaph0r-mbqzlE17yLebjfqbRT275U1AA"
)

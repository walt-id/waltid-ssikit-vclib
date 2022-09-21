import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
    jacoco
    application
    `maven-publish`
}

group = "id.walt"
version = "1.22.0"


repositories {
    mavenCentral()
    maven("https://maven.walt.id/repository/waltid/")
}

dependencies {
    /* JSON */
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
    implementation("com.github.victools:jsonschema-generator:4.26.0")
    implementation("com.networknt:json-schema-validator:1.0.72")
    implementation("net.pwall.json:json-kotlin-schema:0.36")
    implementation("com.beust:klaxon:5.6")

    /* Logging */
    implementation("org.lighthousegames:logging-jvm:1.2.0")
    implementation("org.slf4j:slf4j-simple:2.0.0")

    /* Kotlin */

    // Reflection
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.10")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.7.10")

    /* JWT */
    implementation("com.nimbusds:nimbus-jose-jwt:9.22")

    /* Testing */
    testImplementation("io.kotest:kotest-runner-junit5:5.4.2")
    testImplementation("io.kotest:kotest-assertions-core:5.4.2")
    testImplementation("io.kotest:kotest-assertions-json:5.4.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            pom {
                name.set("waltid-ssikit-vclib")
                description.set("Typesafe implementation of W3C Verifiable Credentials in order to facilitate interoperability among various applications.")
                url.set("https://walt.id")
            }
            from(components["java"])
        }
    }

    repositories {
        maven {
            url = uri("https://maven.walt.id/repository/waltid-ssi-kit/")

            val usernameFile = File("secret_maven_username.txt")
            val passwordFile = File("secret_maven_password.txt")
            val secretMavenUsername = System.getenv()["SECRET_MAVEN_USERNAME"] ?: if (usernameFile.isFile) { usernameFile.readLines()[0] } else { "" }
            val secretMavenPassword = System.getenv()["SECRET_MAVEN_PASSWORD"] ?: if (passwordFile.isFile) { passwordFile.readLines()[0] } else { "" }

            credentials {
                username = secretMavenUsername
                password = secretMavenPassword
            }
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(16))
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

jacoco.toolVersion = "0.8.8"

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
}

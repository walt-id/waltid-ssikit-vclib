plugins {
    kotlin("jvm") version "1.5.21"
    kotlin("plugin.serialization") version "1.5.21"
    jacoco
    application
    `maven-publish`
}

group = "id.walt"
version = "1.3.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.letstrust.io/repository/waltid/")

        credentials {
            username = "letstrust-build"
            password = "naidohTeiraG9ouzoo0"
        }
    }
}

dependencies {
    /* JSON */
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

    /* TODO: to discuss and validate with Phil
    http://json-schema.org/ <- schema spec used in EBSI https://api.preprod.ebsi.eu/trusted-schemas-registry/v1/schemas/0x312e332e362e312e342e312e313730312e372e3130372e322e322e332e3235
    https://github.com/victools/jsonschema-generator recommended here: http://json-schema.org/implementations.html
    */
    implementation("com.github.victools:jsonschema-generator:4.18.0")
    implementation("com.beust:klaxon:5.5")

    /* Logging */
    implementation("org.lighthousegames:logging-jvm:1.0.0")
    implementation("org.slf4j:slf4j-simple:1.7.32")

    /* Kotlin */

    // Reflection
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.21")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.21")

    /* Testing */
    testImplementation("io.kotest:kotest-runner-junit5:4.6.1")
    testImplementation("io.kotest:kotest-assertions-core:4.6.1")
    testImplementation("io.kotest:kotest-assertions-json:4.6.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            pom {
                name.set("Walt.ID SSIKit-VCLib")
                description.set("Typesafe implementation of W3C Verifiable Credentials in order to facilitate interoperability among various applications.")
                url.set("https://walt.id")
            }
            from(components["java"])
        }
    }

    repositories {
        maven {
            url = uri("https://maven.letstrust.io/repository/waltid/")

            credentials {
                username = "letstrust-build"
                password = "naidohTeiraG9ouzoo0"
            }
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "13"
}

jacoco.toolVersion = "0.8.7"

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
}

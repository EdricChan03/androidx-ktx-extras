plugins {
    org.jetbrains.kotlin.jvm
    io.github.edricchan03.androidx.library
}

androidxKtx {
    mavenCoordinates {
        version = "0.1.0-SNAPSHOT"
    }

    name = "androidx-ktx-extras-kotest-common-enums"
    description = "Kotest factory functions for androidx-ktx-extras' common-enums module"
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

dependencies {
    api(libs.kotest.assertions.core)
    api(libs.kotest.property)
    api(libs.kotest.framework.api)
    api(libs.kotest.framework.datatest)
    api(libs.kotlin.reflect) {
        because("Kotlin extension support for enum tests")
    }

    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.kotest.property)
}

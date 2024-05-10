plugins {
    org.jetbrains.kotlin.multiplatform
    io.github.edricchan03.androidx.library
}

androidxKtx {
    mavenCoordinates.version = "0.2.0"

    name = "androidx-ktx-extras-common-enums"
    description = "Common enum utilities for androidx-ktx-extras"
}

kotlin {
    jvm {
        testRuns["test"].executionTask {
            useJUnitPlatform()
        }
    }

    sourceSets {
        commonTest {
            dependencies {
                implementation(libs.kotest.runner.junit5)
                implementation(libs.kotest.assertions.core)
                implementation(libs.kotest.property)
            }
        }
    }
}

plugins {
    org.jetbrains.kotlin.multiplatform
    io.github.edricchan03.androidx.library
    alias(libs.plugins.kotest.multiplatform)
    alias(libs.plugins.ksp)
}

androidxKtx {
    mavenCoordinates.version = "0.3.0-SNAPSHOT"

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
                implementation(libs.kotest.framework.engine)
                implementation(libs.kotest.assertions.core)
                implementation(libs.kotest.property)
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.kotest.runner.junit5)
            }
        }
    }
}

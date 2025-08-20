plugins {
    com.android.kotlin.multiplatform.library
    org.jetbrains.kotlin.multiplatform
    io.github.edricchan03.androidx.library
    alias(libs.plugins.kotest.multiplatform)
    alias(libs.plugins.ksp)
}

androidxKtx {
    mavenCoordinates.version = "0.3.0"

    name = "androidx-ktx-extras-common-enums"
    description = "Common enum utilities for androidx-ktx-extras"
}

kotlin {
    jvm {
        testRuns["test"].executionTask {
            useJUnitPlatform()
        }
    }

    androidLibrary {
        namespace = "io.github.edricchan03.androidx.common.enums"
        compileSdk = 36
    }

    wasmJs {
        d8()
    }
    macosX64()
    macosArm64()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    watchosArm32()
    watchosArm64()
    watchosX64()
    watchosSimulatorArm64()
    watchosDeviceArm64()
    tvosArm64()
    tvosX64()
    tvosSimulatorArm64()
    mingwX64 {
        binaries.getTest(DEBUG).linkerOpts = mutableListOf("-Wl,--subsystem,windows")
    }
    linuxX64()
    linuxArm64()

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

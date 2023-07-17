import io.github.edricchan03.plugin.library.extensions.LibraryType

plugins {
    `android-library`
    `kotlin-android`
    io.github.edricchan03.androidx.library
}

@Suppress("UnstableApiUsage") // Kotlin DSL assignment
androidxKtx {
    mavenCoordinates {
        version = "0.0.2-SNAPSHOT"
    }

    libraryType = LibraryType.Android

    name = "androidx-ktx-extras-browser"
    description = "Kotlin extensions for AndroidX Browser"
}

@Suppress("UnstableApiUsage")
android {
    namespace = "io.github.edricchan03.androidx.browser.ktx"

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}

dependencies {
    api(projects.androidx.common.commonEnums)

    api(androidLibs.androidx.annotation)
    api(androidLibs.androidx.browser)

    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.kotest.property)
    testImplementation(libs.kotest.framework.datatest)
    testImplementation(libs.kotlin.reflect) {
        because("Kotlin extension support for enum tests")
    }
}

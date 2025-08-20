import io.github.edricchan03.plugin.library.extensions.LibraryType

plugins {
    `android-library`
    `kotlin-android`
    io.github.edricchan03.androidx.library
}

androidxKtx {
    mavenCoordinates {
        version = "0.2.0"
    }

    libraryType = LibraryType.Android

    name = "androidx-ktx-extras-browser"
    description = "Kotlin extensions for AndroidX Browser"
}

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

    testImplementation(projects.androidx.common.kotestCommonEnums)
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.kotest.property)
    testImplementation(libs.kotest.framework.engine)
    testImplementation(libs.kotlin.reflect) {
        because("Kotlin extension support for enum tests")
    }
}

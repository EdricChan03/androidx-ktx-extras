import io.github.edricchan03.plugin.library.extensions.LibraryType

plugins {
    `android-library`
    `kotlin-android`
    io.github.edricchan03.androidx.library
}

androidxKtx {
    mavenCoordinates.version = "0.2.0"

    libraryType = LibraryType.Android

    name = "androidx-ktx-extras-recyclerview"
    description = "Kotlin extensions for AndroidX RecyclerView"
}

@Suppress("UnstableApiUsage")
android {
    namespace = "io.github.edricchan03.androidx.recyclerview.ktx"

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}

dependencies {
    api(androidLibs.androidx.annotation)
    api(androidLibs.androidx.recyclerview)
    implementation(androidLibs.androidx.core.ktx)

    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.kotest.property)
    testImplementation(libs.mockk)
}

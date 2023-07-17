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

    name = "androidx-ktx-extras-common-enums"
    description = "Common enum utilities for androidx-ktx-extras"
}

@Suppress("UnstableApiUsage")
android {
    namespace = "io.github.edricchan03.androidx.common.enums"

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}

dependencies {
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.kotest.property)
}

publishing.publications {
    // TODO: Remove in 0.1.0, see https://github.com/EdricChan03/androidx-ktx-extras/issues/6
    val relocation by registering(MavenPublication::class) {
        pom {
            // Old artifact coordinates
            groupId = "io.github.edricchan03.androidx.common.enums"

            distributionManagement {
                relocation {
                    // New artifact coordinates
                    groupId.set("io.github.edricchan03.androidx.common")
                    message.set("groupId has moved")
                }
            }
        }
    }
}

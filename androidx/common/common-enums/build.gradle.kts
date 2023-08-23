import io.github.edricchan03.plugin.library.setConventions

plugins {
    `kotlin-multiplatform`
    io.github.edricchan03.androidx.library
}

@Suppress("UnstableApiUsage") // Kotlin DSL assignment
androidxKtx {
    mavenCoordinates {
        version = "0.1.0-SNAPSHOT"
    }

    name = "androidx-ktx-extras-common-enums"
    description = "Common enum utilities for androidx-ktx-extras"
}

kotlin {
    jvm {
        testRuns["test"].executionTask.configure {
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

@Suppress("UnstableApiUsage")
publishing.publications {
    // TODO: Remove in 0.1.0, see https://github.com/EdricChan03/androidx-ktx-extras/issues/6
    val relocation by registering(MavenPublication::class) {
        pom.setConventions(project, androidxKtx)
        pom {
            description = androidxKtx.description
                .map { "[Notice: Moved to new group, see relocation for more info] $it" }
            // Old artifact coordinates
            groupId = "io.github.edricchan03.androidx.common.enums"

            distributionManagement {
                relocation {
                    // New artifact coordinates
                    groupId = "io.github.edricchan03.androidx.common"
                    message = "groupId has moved"
                }
            }
        }
    }
}

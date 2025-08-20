pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    // See https://youtrack.jetbrains.com/issue/KT-68533
    repositoriesMode = RepositoriesMode.PREFER_SETTINGS
    repositories {
        mavenCentral {
            content {
                excludeGroup("com.yarnpkg")
                excludeGroup("com.github.webassembly")
                excludeGroup("org.nodejs")
                excludeGroup("google.d8")
            }
        }
        google {
            content {
                excludeGroup("com.yarnpkg")
                excludeGroup("com.github.webassembly")
                excludeGroup("org.nodejs")
                excludeGroup("google.d8")
            }
        }
        exclusiveContent {
            forRepository {
                ivy("https://nodejs.org/dist/") {
                    name = "Node Distributions at $url"
                    patternLayout { artifact("v[revision]/[artifact](-v[revision]-[classifier]).[ext]") }
                    metadataSources { artifact() }
                    content { includeModule("org.nodejs", "node") }
                }
            }
            filter { includeGroup("org.nodejs") }
        }
        exclusiveContent {
            forRepository {
                ivy("https://github.com/yarnpkg/yarn/releases/download") {
                    name = "Yarn Distributions at $url"
                    patternLayout { artifact("v[revision]/[artifact](-v[revision]).[ext]") }
                    metadataSources { artifact() }
                    content { includeModule("com.yarnpkg", "yarn") }
                }
            }
            filter { includeGroup("com.yarnpkg") }
        }
        exclusiveContent {
            forRepository {
                ivy("https://github.com/WebAssembly/binaryen/releases/download") {
                    name = "Binaryen Distributions at $url"
                    patternLayout { artifact("version_[revision]/[module]-version_[revision]-[classifier].[ext]") }
                    metadataSources { artifact() }
                    content { includeModule("com.github.webassembly", "binaryen") }
                }
            }
            filter { includeGroup("com.github.webassembly") }
        }
        exclusiveContent {
            forRepository {
                ivy("https://storage.googleapis.com/chromium-v8/official/canary") {
                    name = "D8 Distributions at $url"
                    patternLayout {
                        artifact("[artifact]-[revision].[ext]")
                    }
                    metadataSources { artifact() }
                    content { includeModule("google.d8", "v8") }
                }
            }
            filter { includeGroup("google.d8") }
        }
    }

    versionCatalogs {
        val androidLibs by creating {
            from(files("gradle/android-libs.versions.toml"))
        }
    }
}

plugins {
    // Apply the foojay-resolver plugin to allow automatic download of JDKs
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

// Enable type-safe project accessors
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "androidx-ktx-extras"

include(
    ":androidx:common:common-enums",
    ":androidx:common:kotest-common-enums",
    ":androidx:browser:browser-ktx",
    ":androidx:recyclerview:recyclerview-ktx"
)

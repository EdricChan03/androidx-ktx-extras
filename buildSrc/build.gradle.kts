plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        val workaroundExplicitApiModePlugin by creating {
            id = "io.github.edricchan03.kotlin-explicit-api"
            displayName = "Kotlin Explicit API mode workaround"
            description =
                "Plugin which works-around https://youtrack.jetbrains.com/issue/KT-37652," +
                    "adding Explicit API support to Android source-sets"
            implementationClass = "io.github.edricchan03.plugin.explicit_api.ExplicitApiModePlugin"
        }

        val libraryPlugin by creating {
            id = "io.github.edricchan03.androidx.library"
            displayName = "Convention plugin for androidx-ktx-extras libraries"
            implementationClass = "io.github.edricchan03.plugin.library.LibraryPlugin"
        }
    }
}

dependencies {
    // See https://issuetracker.google.com/issues/176079157#comment14
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.android.gradle.plugin)
    implementation(libs.plugins.dokkatoo.html.asDependency)
    implementation(libs.plugins.dokkatoo.javadoc.asDependency)
    implementation(libs.plugins.kotlinx.bcv.asDependency)
}

private val Provider<PluginDependency>.asDependency
    get() = map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }

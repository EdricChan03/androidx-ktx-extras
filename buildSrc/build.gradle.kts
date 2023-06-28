plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        val workaroundExplicitApiModePlugin by creating {
            id = "io.github.edricchan03.kotlin-explicit-api"
            displayName = "Kotlin Explicit API mode workaround"
            description = "Plugin which works-around https://youtrack.jetbrains.com/issue/KT-37652," +
                "adding Explicit API support to Android source-sets"
            implementationClass = "io.github.edricchan03.plugin.explicit_api.ExplicitApiModePlugin"
        }
    }
}

dependencies {
    implementation(libs.plugins.android.gradle.library.asDependency)
    implementation(libs.plugins.kotlin.android.asDependency)
    implementation(libs.plugins.dokkatoo.html.asDependency)
    implementation(libs.plugins.dokkatoo.javadoc.asDependency)
    implementation(libs.plugins.kotlinx.bcv.asDependency)
}

private val Provider<PluginDependency>.asDependency
  get() = map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }

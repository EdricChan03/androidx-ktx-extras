plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
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
    implementation(libs.dokka.gradle.plugin)
    implementation(libs.plugins.kotlinx.bcv.asDependency)
}

private val Provider<PluginDependency>.asDependency
    get() = map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }

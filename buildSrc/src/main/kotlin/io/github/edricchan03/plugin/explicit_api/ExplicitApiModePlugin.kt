package io.github.edricchan03.plugin.explicit_api

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

private const val EXPLICIT_API = "-Xexplicit-api=strict"

/**
 * Configures the Kotlin explicit API mode to use.
 */
// TODO: Remove when https://youtrack.jetbrains.com/issue/KT-37652 is fixed
class ExplicitApiModePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension =
            project.extensions.create<ExplicitApiModePluginExtension>("explicitApiOptions")

        val explicitApi = extension.explicitApi.getOrElse(ExplicitApiMode.Strict).toCompilerArg()

        project.tasks
            .matching { it is KotlinCompile<*> && !it.name.contains("test", ignoreCase = true) }
            .configureEach {
                if (!project.hasProperty("kotlin.optOutExplicitApi")) {
                    val kotlinCompile = this as KotlinCompile<*>
                    if (explicitApi !in kotlinCompile.kotlinOptions.freeCompilerArgs) {
                        kotlinCompile.kotlinOptions.freeCompilerArgs += explicitApi
                    }
                }
            }
    }
}

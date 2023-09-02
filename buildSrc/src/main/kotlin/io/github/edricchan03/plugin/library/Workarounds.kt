package io.github.edricchan03.plugin.library

import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.exclude

/**
 * Workaround for [Dokkatoo issue #117](https://github.com/adamko-dev/dokkatoo/issues/117)
 */
fun Project.dokka19Dependencies(
    dokkaVersion: Provider<String> = provider { LibraryPlugin.DEFAULT_DOKKA_VERSION }
) =
    configurations.matching { it.name.startsWith("dokkatooPlugin") }.configureEach {
        listOf(
            "org.jetbrains.dokka" to "dokka-analysis",
            "org.jetbrains.dokka" to "kotlin-analysis-intellij",
            "org.jetbrains.dokka" to "kotlin-analysis-compiler"
        ).forEach { (group, module) ->
            logger.info("${project.name} - Configuration $name: excluding $group:$module")
            exclude(group = group, module = module)
        }
        this@dokka19Dependencies.dependencies.add(
            name,
            dokkaVersion.map { "org.jetbrains.dokka:analysis-kotlin-descriptors:$it" }
        )
    }

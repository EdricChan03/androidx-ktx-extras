package io.github.edricchan03.publishing

import java.util.Locale

plugins {
    com.android.library
    dev.adamko.`dokkatoo-javadoc`
    dev.adamko.`dokkatoo-html`
}

android.libraryVariants.configureEach {
    // Add Javadoc Jar tasks
    tasks {
        register<Jar>(computeJavadocTaskName(name, isHtml = false)) {
            dependsOn(dokkatooGenerateModuleJavadoc)
            from(dokkatooGenerateModuleJavadoc.map { it.outputs })
            archiveClassifier.set(DocsType.JAVADOC)
        }
        register<Jar>(computeJavadocTaskName(name, isHtml = true)) {
            dependsOn(dokkatooGenerateModuleHtml)
            from(dokkatooGenerateModuleHtml.map { it.outputs })
            archiveClassifier.set("html-docs")
        }
    }
}

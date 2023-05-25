package io.github.edricchan03.docs

import org.gradle.util.Path as GradleProjectPath
import org.jetbrains.dokka.gradle.DokkaTaskPartial
import org.jetbrains.dokka.gradle.AbstractDokkaTask
import java.net.URL

plugins {
    org.jetbrains.dokka // TODO: Remove
    dev.adamko.`dokkatoo-html`
}

// Path to the Module.md file, or null if it doesn't exist
val moduleReadme = file("Module.md").takeIf { it.exists() }

//<editor-fold desc="Dokkatoo extensions">
// Copied from Dokkatoo
fun Project.pathAsFilePath() = path
    .removePrefix(GradleProjectPath.SEPARATOR)
    .replace(GradleProjectPath.SEPARATOR, "/")

val AbstractDokkaTask.modulePath get() = provider { pathAsFilePath() }
//</editor-fold>

dokkatoo {
    dokkatooSourceSets.configureEach {
        reportUndocumented.set(true)

        // Link to source
        sourceLink {
            localDirectory.set(file("src/${name}/kotlin"))
            remoteUrl(
                modulePath.map {
                    "https://github.com/EdricChan03/androidx-ktx-extras/" +
                            "tree/main/${it}/src/${name}/kotlin"
                }
            )
        }

        // Only include the module docs if it exists
        moduleReadme?.let {
            includes.from(it)
        }
    }

    // TODO: Remove
    dokkatooPublicationDirectory.set(layout.buildDirectory.dir("dokkatoo"))
    dokkatooConfigurationsDirectory.set(layout.buildDirectory.dir("dokkatoo-config"))
}

// TODO: Remove below Dokka config once https://github.com/adamko-dev/dokkatoo/issues/71 is fixed
tasks.withType<DokkaTaskPartial>().configureEach {
    dokkaSourceSets.configureEach {
        reportUndocumented.set(true)

        // Link to source
        sourceLink {
            localDirectory.set(file("src/${name}/kotlin"))
            remoteUrl.set(
                modulePath.map {
                    URL(
                        "https://github.com/EdricChan03/androidx-ktx-extras/" +
                                "tree/main/${it}/src/${name}/kotlin"
                    )
                }
            )
        }

        moduleReadme?.let {
            includes.from(it)
        }
    }
}

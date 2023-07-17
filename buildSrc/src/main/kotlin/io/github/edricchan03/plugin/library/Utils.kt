package io.github.edricchan03.plugin.library

import io.github.edricchan03.plugin.library.extensions.LibraryPluginExtension
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPom
import org.gradle.util.Path as GradleProjectPath

internal val Project.pathAsFilePath
    get() = path
        .removePrefix(GradleProjectPath.SEPARATOR)
        .replace(GradleProjectPath.SEPARATOR, "/")

internal fun String.substringBeforeLastColon(): String {
    val lastColonIndex = lastIndexOf(":")
    return substring(0, lastColonIndex)
}

/** Sets the conventions on the given receiver Maven POM. */
fun MavenPom.setConventions(project: Project, extension: LibraryPluginExtension) {
    name.convention(extension.name)
    description.convention(extension.description)
    url.convention(project.sourceUrl)
    inceptionYear.convention(extension.inceptionYear.orElse("2023"))

    licenses {
        license {
            name.set("GPLv3")
            url.set("$SOURCE_URL/blob/main/LICENSE")
        }

        // AndroidX license
        license {
            name.set("Apache License 2.0")
            url.set("https://github.com/androidx/androidx/blob/androidx-main/LICENSE.txt")
        }
    }

    developers {
        developer {
            id.set("EdricChan03")
            name.set("Edric Chan")
            email.set("edric.chan.1997@gmail.com")
            url.set("https://github.com/EdricChan03")
            timezone.set("UTC+8")
        }
    }

    scm {
        connection.set("scm:git:github.com/EdricChan03/androidx-ktx-extras.git")
        developerConnection.set("scm:git:ssh://github.com/EdricChan03/androidx-ktx-extras.git")
        url.set(SOURCE_URL)
    }

    issueManagement {
        system.set("GitHub Issues")
        url.set("$SOURCE_URL/issues")
    }

    ciManagement {
        system.set("GitHub Actions")
        url.set("$SOURCE_URL/actions")
    }
}

/** The GitHub repository for the project. */
const val SOURCE_URL = "https://github.com/EdricChan03/androidx-ktx-extras"

internal val Project.sourceUrl
    get() = "$SOURCE_URL/tree/main/${pathAsFilePath.drop(1)}"

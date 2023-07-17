package io.github.edricchan03.plugin.library.extensions.publish

import org.gradle.api.provider.Property

abstract class LibraryMavenCoordinates {
    /** Sets the group ID for the Maven GAV coordinate. */
    abstract val groupId: Property<String>

    /** Sets the artifact ID for the Maven GAV coordinate. */
    abstract val artifactId: Property<String>

    /** Sets the version for the Maven GAV coordinate. */
    abstract val version: Property<String>

    /** Retrieves all 3 values as a Maven GAV coordinate. */
    val gav = groupId
        .flatMap { g ->
            artifactId
                .flatMap { a -> version.map { v -> "$g:$a:$v" } }
        }

    /**
     * Sets the Maven GAV coordinates to use.
     *
     * This method allows for all 3 parts to be specified as arguments.
     */
    fun coordinates(
        groupId: String,
        artifactId: String,
        version: String
    ) {
        this.groupId.set(groupId)
        this.artifactId.set(artifactId)
        this.version.set(version)
    }
}

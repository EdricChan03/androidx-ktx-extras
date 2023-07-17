package io.github.edricchan03.plugin.library.extensions.publish

import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory

/**
 * A read-only version of the [LibraryMavenCoordinates] class.
 * @property groupId The group ID for the Maven GAV coordinate.
 * @property artifactId The artifact ID for the Maven GAV coordinate.
 * @property version The version for the Maven GAV coordinate.
 * @see ReleaseVersionSpec
 */
class ReadOnlyLibraryMavenCoordinates(
    val groupId: Provider<String>,
    val artifactId: Provider<String>,
    val version: Provider<String>
) {
    /** Retrieves all 3 values as a Maven GAV coordinate. */
    val gav = groupId
        .flatMap { g ->
            artifactId
                .flatMap { a -> version.map { v -> "$g:$a:$v" } }
        }

    /** Retrieves the [groupId], or `null` if not set. */
    operator fun component1() = groupId.orNull

    /** Retrieves the [artifactId], or `null` if not set. */
    operator fun component2() = artifactId.orNull

    /** Retrieves the [version], or `null` if not set. */
    operator fun component3() = version.orNull

    override fun toString() =
        "LibraryMavenCoordinates(" +
            "groupId=${groupId.orNull}, " +
            "artifactId=${artifactId.orNull}, " +
            "version=${version.orNull}" +
            ")"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ReadOnlyLibraryMavenCoordinates

        if (groupId.orNull != other.groupId.orNull) return false
        if (artifactId.orNull != other.artifactId.orNull) return false
        return version.orNull == other.version.orNull
    }

    override fun hashCode(): Int {
        var result = groupId.orNull?.hashCode() ?: 0
        result = 31 * result + (artifactId.orNull?.hashCode() ?: 0)
        result = 31 * result + (version.orNull?.hashCode() ?: 0)
        return result
    }

    fun copy(
        groupId: Provider<String> = this.groupId,
        artifactId: Provider<String> = this.artifactId,
        version: Provider<String> = this.version
    ) = ReadOnlyLibraryMavenCoordinates(
        groupId, artifactId, version
    )
}

internal fun LibraryMavenCoordinates.asReadOnly() =
    ReadOnlyLibraryMavenCoordinates(groupId, artifactId, version)

internal fun LibraryMavenCoordinates.asReadOnlyProvider(factory: ProviderFactory) =
    factory.provider { ReadOnlyLibraryMavenCoordinates(groupId, artifactId, version) }

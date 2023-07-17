package io.github.edricchan03.plugin.library.extensions.publish

import org.gradle.api.specs.Spec

/**
 * Whether the version is a release version.
 * By default, this value is `true` if [LibraryMavenCoordinates.version] ends with
 * `"SNAPSHOT"`.
 */
typealias ReleaseVersionSpec = Spec<ReadOnlyLibraryMavenCoordinates>

/**
 * The default [ReleaseVersionSpec] to use if not specified.
 *
 * `true` is returned if [LibraryMavenCoordinates.version] ends with `"SNAPSHOT"`.
 */
val DefaultReleaseVersionSpec = ReleaseVersionSpec {
    !it.version.get().endsWith("SNAPSHOT")
}

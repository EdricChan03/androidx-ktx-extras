package io.github.edricchan03.plugin.library.extensions.publish.maven

import io.github.edricchan03.plugin.library.extensions.publish.ReadOnlyLibraryMavenCoordinates
import io.github.edricchan03.plugin.library.extensions.publish.ReleaseVersionSpec
import org.gradle.api.Action
import org.gradle.api.Named
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Nested
import org.gradle.kotlin.dsl.invoke
import java.net.URI
import java.util.Locale

abstract class MavenRepository : Named {
    /** The capitalised name for this Maven repository. For use with [RepositoryHandler].  */
    internal val capitalizedName by lazy {
        name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
    }

    /** URL to use for deploying snapshots. */
    abstract val snapshotUrl: Property<URI>

    /** URL to use for deploying releases. */
    abstract val releaseUrl: Property<URI>

    /** Sets the [snapshot URL][snapshotUrl] to use. */
    fun snapshotUrl(url: String) {
        snapshotUrl.set(URI(url))
    }

    /** Sets the [snapshot URL][snapshotUrl] to use. */
    fun snapshotUrl(url: Provider<String>) {
        snapshotUrl.set(url.map { URI(it) })
    }

    /** Sets the [release URL][releaseUrl] to use. */
    fun releaseUrl(url: String) {
        releaseUrl.set(URI(url))
    }

    /** Sets the [release URL][releaseUrl] to use. */
    fun releaseUrl(url: Provider<String>) {
        releaseUrl.set(url.map { URI(it) })
    }

    /** Sets the URL for **both** the [snapshotUrl] and [releaseUrl]. */
    fun url(url: String) {
        snapshotUrl(url)
        releaseUrl(url)
    }

    /** Sets the URL for **both** the [snapshotUrl] and [releaseUrl]. */
    @JvmName("urlAsStringProvider")
    fun url(url: Provider<String>) {
        snapshotUrl(url)
        releaseUrl(url)
    }

    /**
     * Sets the URL to use for deploying snapshots/releases depending upon the
     * value of [isRelease].
     */
    fun url(url: String, isRelease: Boolean) {
        url(URI(url), isRelease)
    }

    /**
     * Sets the URL to use for deploying snapshots/releases depending upon the
     * value of [isRelease].
     */
    @JvmName("urlAsStringPRoviderWithRelease")
    fun url(url: Provider<String>, isRelease: Boolean) {
        url(url.map { URI(it) }, isRelease)
    }

    /** Sets the URL for **both** the [snapshotUrl] and [releaseUrl]. */
    fun url(url: URI) {
        snapshotUrl.set(url)
        releaseUrl.set(url)
    }

    /** Sets the URL for **both** the [snapshotUrl] and [releaseUrl]. */
    @JvmName("urlAsURIProvider")
    fun url(url: Provider<URI>) {
        snapshotUrl.set(url)
        releaseUrl.set(url)
    }

    /**
     * Sets the URL to use for deploying snapshots/releases depending upon the
     * value of [isRelease].
     */
    fun url(url: URI, isRelease: Boolean) {
        if (isRelease) releaseUrl.set(url)
        else snapshotUrl.set(url)
    }

    /**
     * Sets the URL to use for deploying snapshots/releases depending upon the
     * value of [isRelease].
     */
    @JvmName("urlAsURIPRoviderWithRelease")
    fun url(url: Provider<URI>, isRelease: Boolean) {
        if (isRelease) releaseUrl.set(url)
        else snapshotUrl.set(url)
    }

    /** Retrieves the Maven URL given the arguments. */
    fun getUrl(
        mavenCoordinates: Provider<ReadOnlyLibraryMavenCoordinates>,
        isRelease: Provider<ReleaseVersionSpec>
    ) =
        isRelease.flatMap { if (it(mavenCoordinates.get())) releaseUrl else snapshotUrl }

    /** The password credentials for this Maven repository. */
    @get:Nested
    abstract val credentials: PasswordCredentials

    /** Configures the password credentials for this Maven repository. */
    fun credentials(action: Action<in PasswordCredentials>) {
        action(credentials)
    }
}

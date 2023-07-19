package io.github.edricchan03.plugin.library.extensions

import com.android.build.api.dsl.ComposeOptions
import io.github.edricchan03.plugin.library.extensions.compose.LibraryComposeExtension
import io.github.edricchan03.plugin.library.extensions.docs.LibraryDocsExtension
import io.github.edricchan03.plugin.library.extensions.publish.LibraryMavenCoordinates
import io.github.edricchan03.plugin.library.extensions.publish.ReleaseVersionSpec
import io.github.edricchan03.plugin.library.extensions.publish.maven.LibraryMavenPublishingExtension
import org.gradle.api.Action
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.tasks.Nested
import org.gradle.kotlin.dsl.invoke

abstract class LibraryPluginExtension : ExtensionAware {
    /** The GAV coordinate for this library. */
    @get:Nested
    abstract val mavenCoordinates: LibraryMavenCoordinates

    /** Configures the GAV coordinate for this library. */
    fun mavenCoordinates(action: Action<in LibraryMavenCoordinates>) {
        action(mavenCoordinates)
    }

    /**
     * Configures the Maven GAV coordinate for this library.
     *
     * This method allows for Kotlin's named arguments to be used, and accepts [Provider]s
     * of [String]s.
     */
    fun coordinates(
        groupId: Provider<String>,
        artifactId: Provider<String>,
        version: Provider<String>
    ) {
        mavenCoordinates {
            this.groupId.set(groupId)
            this.artifactId.set(artifactId)
            this.version.set(version)
        }
    }

    /**
     * Configures the Maven GAV coordinate for this library.
     *
     * This method allows for Kotlin's named arguments to be used, and accepts [String]s.
     */
    fun coordinates(groupId: String, artifactId: String, version: String) {
        mavenCoordinates {
            this.groupId.set(groupId)
            this.artifactId.set(artifactId)
            this.version.set(version)
        }
    }

    /**
     * Whether the version is a release version.
     * By default, this value is `true` if [LibraryMavenCoordinates.version] ends with
     * `"SNAPSHOT"`.
     */
    // FIXME: https://github.com/gradle/gradle/issues/18543
    abstract fun getIsReleaseVersion(): Property<ReleaseVersionSpec>

    /**
     * Sets whether the version is a release version based on the value of [isRelease].
     *
     * To conditionally determine if the version is a release version, use the
     * [isReleaseVersion] `Property` delegate, passing in a [ReleaseVersionSpec] for
     * the [Property.set] call.
     * @param isRelease Whether the version is a release version.
     */
    fun setReleaseVersion(isRelease: Boolean) {
        getIsReleaseVersion().set(ReleaseVersionSpec { isRelease })
    }

    /** The library type. */
    abstract val libraryType: Property<LibraryType>

    /**
     * The name of this library.
     * @see MavenPom.getName
     */
    abstract val name: Property<String>

    /**
     * The year the library was created.
     * @see MavenPom.getInceptionYear
     */
    abstract val inceptionYear: Property<String>

    /**
     * The year the library was created. This method allows for an [Int] to
     * be passed.
     * @see MavenPom.getInceptionYear
     */
    fun inceptionYear(year: Int) {
        inceptionYear.set(year.toString())
    }

    /**
     * A short, human-readable description for the library.
     * @see MavenPom.getDescription
     */
    abstract val description: Property<String>

    /** Maven publishing for this library module. */
    @get:Nested
    abstract val mavenPublishing: LibraryMavenPublishingExtension

    /** Configures Maven publishing for this library module. */
    fun mavenPublishing(action: Action<in LibraryMavenPublishingExtension>) {
        action(mavenPublishing)
    }

    /** Documentation options for this library module. */
    @get:Nested
    abstract val docs: LibraryDocsExtension

    /** Configures documentation for this library module. */
    fun docs(action: Action<in LibraryDocsExtension>) {
        action(docs)
    }

    /** Jetpack Compose options for this library module. */
    @get:Nested
    abstract val compose: LibraryComposeExtension

    /** Configures the Jetpack Compose options for this library module. */
    fun compose(action: Action<in LibraryComposeExtension>) {
        action(compose)
    }

    /**
     * Enables Jetpack Compose for this library module based on the value of
     * [enabled].
     * @param enabled Whether to enable Jetpack Compose.
     * @see LibraryComposeExtension.enabled
     */
    fun withCompose(enabled: Boolean = true) {
        compose.enabled.set(enabled)
    }

    /**
     * Enables Jetpack Compose for this library module based on the value of
     * [enabled].
     * @param enabled Whether to enable Jetpack Compose as a [Provider].
     * @see LibraryComposeExtension.enabled
     */
    @JvmName("withComposeEnabledProvider")
    fun withCompose(enabled: Provider<Boolean>) {
        compose.enabled.set(enabled)
    }

    /**
     * Enables Jetpack Compose for this library module, and sets the
     * [LibraryComposeExtension.kotlinCompilerExtensionVersion] value to [version].
     * @param version The [ComposeOptions.kotlinCompilerExtensionVersion] to use as a
     * [Provider].
     * @see ComposeOptions
     * @see compose
     */
    fun withCompose(version: Provider<String>) {
        compose {
            enabled.set(true)
            kotlinCompilerExtensionVersion.set(version)
        }
    }

    /**
     * Enables Jetpack Compose for this library module, and sets the
     * [LibraryComposeExtension.kotlinCompilerExtensionVersion] value to [version].
     * @param version The [ComposeOptions.kotlinCompilerExtensionVersion] to use.
     * @see ComposeOptions
     * @see compose
     */
    fun withCompose(version: String) {
        compose {
            enabled.set(true)
            kotlinCompilerExtensionVersion.set(version)
        }
    }

    companion object {
        const val EXTENSION_NAME = "androidxKtx"
        const val MAVEN_GROUP_ID_PREFIX = "io.github.edricchan03"
    }
}

/**
 * Whether the version is a release version.
 * By default, this value is `true` if [LibraryMavenCoordinates.version] ends with
 * `"SNAPSHOT"`.
 */
// TODO: Remove when https://github.com/gradle/gradle/issues/18543 is fixed
inline val LibraryPluginExtension.isReleaseVersion
    get() = getIsReleaseVersion()

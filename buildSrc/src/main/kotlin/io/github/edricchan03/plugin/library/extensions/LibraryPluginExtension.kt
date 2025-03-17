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
import java.time.Year

/** Gradle extension for the [io.github.edricchan03.plugin.library.LibraryPlugin]. */
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
     * @see mavenCoordinates
     * @see LibraryMavenCoordinates
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
     *
     * The default convention values are used if `null` is specified:
     * - `groupId`: `project.getLibraryGroupFromProjectPath()`
     * - `artifactId`: `project.name`
     * - `version`: `"0.0.1-SNAPSHOT"`
     */
    fun coordinates(
        groupId: String? = null,
        artifactId: String? = null,
        version: String? = null
    ) {
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
    abstract val releaseVersionSpec: Property<ReleaseVersionSpec>

    /**
     * Sets whether the version is a release version based on the value of [isRelease].
     *
     * To conditionally determine if the version is a release version, use the
     * [isReleaseVersion] `Property` delegate, passing in a [ReleaseVersionSpec] for
     * the [Property.set] call.
     * @param isRelease Whether the version is a release version.
     */
    fun setReleaseVersion(isRelease: Boolean) {
        releaseVersionSpec.set { isRelease }
    }

    /**
     * The library type.
     *
     * If not specified, the value is automatically determined based on the following
     * conditions:
     *
     * Enum | Condition
     * ---|---
     * [LibraryType.Android] | Whether the [Android Gradle library Plugin][com.android.build.gradle.LibraryPlugin] is applied
     * [LibraryType.Jvm] | Whether the [Kotlin JVM plugin][org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin] is applied
     * [LibraryType.Multiplatform] | Whether the [Kotlin Multiplatform Plugin][org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper] is applied
     */
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
     * The year the library was created. This method allows for a [java.time Year][Year] to
     * be passed.
     * @see MavenPom.getInceptionYear
     */
    fun inceptionYear(year: Year) {
        inceptionYear(year.value)
    }

    /**
     * The year the library was created. This method allows for a [Provider] (of
     * type [Int]) to be passed.
     * @see MavenPom.getInceptionYear
     */
    @JvmName("inceptionYearIntProvider")
    fun inceptionYear(yearProvider: Provider<Int>) {
        inceptionYear.set(yearProvider.map(Int::toString))
    }

    /**
     * The year the library was created. This method allows for a [Provider] (of
     * type [java.time Year][Year]) to be passed.
     * @see MavenPom.getInceptionYear
     */
    @JvmName("inceptionYearJTimeProvider")
    fun inceptionYear(yearProvider: Provider<Year>) {
        inceptionYear(yearProvider.map(Year::getValue))
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
    @Deprecated("The specified version has no effect; apply the Compose Compiler plugin instead")
    @Suppress("DEPRECATION")
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
    @Deprecated("The specified version has no effect; apply the Compose Compiler plugin instead")
    @Suppress("DEPRECATION")
    fun withCompose(version: String) {
        compose {
            enabled.set(true)
            kotlinCompilerExtensionVersion.set(version)
        }
    }

    companion object {
        /** The current year, for use for [LibraryPluginExtension.inceptionYear]. */
        val CURRENT_YEAR = Year.now()

        /** The current year, for use for [LibraryPluginExtension.inceptionYear], as a [String]. */
        val CURRENT_YEAR_STRING = CURRENT_YEAR.value.toString()

        /** The name to apply this extension class to. */
        const val EXTENSION_NAME = "androidxKtx"

        /** The default groupId prefix for Maven GAV coordinates. */
        const val MAVEN_GROUP_ID_PREFIX = "io.github.edricchan03"
    }
}

/**
 * Whether the version is a release version.
 * By default, this value is `true` if [LibraryMavenCoordinates.version] ends with
 * `"SNAPSHOT"`.
 */
@Deprecated(
    "Use releaseVersionSpec",
    ReplaceWith(
        "this.releaseVersionSpec"
    )
)
inline val LibraryPluginExtension.isReleaseVersion
    get() = releaseVersionSpec

package io.github.edricchan03.plugin.library.extensions.publish.maven

import io.github.edricchan03.plugin.library.extensions.LibraryType
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.component.SoftwareComponent
import org.gradle.api.provider.Property
import java.net.URI

abstract class LibraryMavenPublishingExtension {
    /** The Sonatype staging URL to publish to. */
    abstract val sonatypeStagingUrl: Property<URI>

    /** The Sonatype snapshot URL to publish to. */
    abstract val sonatypeSnapshotUrl: Property<URI>

    /** The GitHub Packages URL to publish to. */
    abstract val gitHubPackagesUrl: Property<URI>

    /** The publication name to use for [Kotlin/JVM projects][LibraryType.Jvm]. */
    abstract val jvmPublicationName: Property<String>

    /** Whether to skip publication for [Kotlin/JVM projects][LibraryType.Jvm]. Defaults to `false`. */
    abstract val skipJvmPublication: Property<Boolean>

    /**
     * The JVM publication component to be passed to [MavenPublication.from].
     *
     * If not specified, defaults to `components["java"]`.
     * @see MavenPublication.from
     */
    abstract val jvmPublicationComponent: Property<SoftwareComponent>

    /** The repositories container. */
    abstract val repositories: NamedDomainObjectContainer<MavenRepository>
//
//    /**
//     * The publications container.
//     * @see PublishingExtension.getPublications
//     */
//    abstract val publications: PublicationContainer
//
//    /**
//     * Configures the publications container.
//     * @see PublishingExtension.publications
//     */
//    fun publications(action: Action<in PublicationContainer>) {
//        action(publications)
//    }

    companion object {
        /** The default name for [jvmPublicationName] if not specified. */
        const val DEFAULT_JVM_PUBLICATION_NAME = "main"

        /** The default value for [skipJvmPublication] if not specified. */
        const val DEFAULT_SKIP_JVM_PUBLICATION = false
    }
}

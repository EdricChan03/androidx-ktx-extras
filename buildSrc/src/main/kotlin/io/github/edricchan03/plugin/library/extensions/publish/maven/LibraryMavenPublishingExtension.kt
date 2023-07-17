package io.github.edricchan03.plugin.library.extensions.publish.maven

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.provider.Property
import java.net.URI

abstract class LibraryMavenPublishingExtension {
    /** The Sonatype staging URL to publish to. */
    abstract val sonatypeStagingUrl: Property<URI>

    /** The Sonatype snapshot URL to publish to. */
    abstract val sonatypeSnapshotUrl: Property<URI>

    /** The GitHub Packages URL to publish to. */
    abstract val gitHubPackagesUrl: Property<URI>

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
}

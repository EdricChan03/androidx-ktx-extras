package io.github.edricchan03.plugin.library.extensions.docs

import dev.adamko.dokkatoo.dokka.parameters.DokkaExternalDocumentationLinkSpec
import dev.adamko.dokkatoo.dokka.parameters.DokkaSourceSetSpec
import dev.adamko.dokkatoo.formats.DokkatooHtmlPlugin
import dev.adamko.dokkatoo.formats.DokkatooJavadocPlugin
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.provider.SetProperty

abstract class LibraryDocsExtension {
    /**
     * Whether Javadocs should be published. If enabled, the
     * [javadoc plugin][DokkatooJavadocPlugin] is applied.
     */
    abstract val publishJavadoc: Property<Boolean>

    /**
     * Whether Dokka HTML docs should be published. If enabled, the
     * [HTML plugin][DokkatooHtmlPlugin] is applied.
     */
    abstract val publishHtmlDoc: Property<Boolean>

    /**
     * Whether to only set the [DokkaSourceSetSpec.sourceLink] on the
     * `main` source-set.
     */
    abstract val onlyMainSourceLink: Property<Boolean>

    /**
     * Whether to [suppress][DokkaSourceSetSpec.suppress] all source-sets that
     * are not `main`.
     */
    abstract val suppressNonMain: Property<Boolean>

    /**
     * The list of enabled [external documentation links][DokkaExternalDocumentationLinkSpec],
     * by their registered accessor name(s), if any.
     * @see ExternalDocLinks
     */
    abstract val enabledExternalDocumentationLinks: SetProperty<String>

    /**
     * [Dokka module and package documentation](https://kotlinlang.org/docs/dokka-module-and-package-docs.html)
     * for this module.
     *
     * This option will be applied to _all_ source-sets.
     * @see DokkaSourceSetSpec.includes
     * */
    abstract val moduleDocs: ConfigurableFileCollection

    /**
     * Path to the default `Module.md` file.
     * @see moduleDocs
     */
    abstract val moduleDoc: RegularFileProperty
}

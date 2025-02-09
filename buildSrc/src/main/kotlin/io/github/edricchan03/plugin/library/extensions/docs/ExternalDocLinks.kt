package io.github.edricchan03.plugin.library.extensions.docs

import org.jetbrains.dokka.gradle.engine.parameters.DokkaExternalDocumentationLinkSpec
import org.jetbrains.dokka.gradle.engine.parameters.DokkaSourceSetSpec

/**
 * This object contains the names of the
 * [external documentation links][DokkaSourceSetSpec.externalDocumentationLinks]
 * to easily reference one for customisation/
 * [enabling][DokkaExternalDocumentationLinkSpec.enabled].
 */
object ExternalDocLinks {
    object Kotlinx {
        /**
         * Name of the
         * [external documentation accessor][DokkaSourceSetSpec.externalDocumentationLinks]
         * for [kotlinx.coroutines](https://kotlinlang.org/api/kotlinx.coroutines).
         */
        const val coroutines = "kotlinxCoroutines"

        /**
         * Name of the
         * [external documentation accessor][DokkaSourceSetSpec.externalDocumentationLinks]
         * for [kotlinx.serialization](https://kotlinlang.org/api/kotlinx.serialization).
         */
        const val serialization = "kotlinxSerialization"

        /**
         * Name of the
         * [external documentation accessor][DokkaSourceSetSpec.externalDocumentationLinks]
         * for [kotlinx.serialization](https://kotlinlang.org/api/kotlinx.serialization).
         *
         * This is a British naming alias for the [serialization] variable.
         */
        const val serialisation = serialization

        /**
         * Name of the
         * [external documentation accessor][DokkaSourceSetSpec.externalDocumentationLinks]
         * for [kotlinx-datetime](https://kotlinlang.org/api/kotlinx-datetime).
         */
        const val dateTime = "kotlinxDateTime"
    }

    /**
     * Name of the
     * [external documentation accessor][DokkaSourceSetSpec.externalDocumentationLinks]
     * for [Ktor](https://api.ktor.io).
     */
    const val ktor = "ktor"
}

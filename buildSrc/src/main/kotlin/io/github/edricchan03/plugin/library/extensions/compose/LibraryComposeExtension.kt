package io.github.edricchan03.plugin.library.extensions.compose

import com.android.build.api.dsl.BuildFeatures
import org.gradle.api.provider.Property

/** Compose options for this library module. */
abstract class LibraryComposeExtension {
    /**
     * Whether this project uses Jetpack Compose.
     * @see BuildFeatures.compose
     */
    abstract val enabled: Property<Boolean>
}

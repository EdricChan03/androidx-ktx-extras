package io.github.edricchan03.plugin.explicit_api

import org.gradle.api.provider.Property
import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode

public abstract class ExplicitApiModePluginExtension {
    /**
     * Option that tells the compiler if and how to report issues on all
     * public API declarations without explicit visibility or return type.
     */
    abstract val explicitApi: Property<ExplicitApiMode>

    /** Sets [explicitApi] option to report issues as errors. */
    fun explicitApi() {
        explicitApi.set(ExplicitApiMode.Strict)
    }

    /** Sets [explicitApi] option to report issues as warnings. */
    fun explicitApiWarning() {
        explicitApi.set(ExplicitApiMode.Warning)
    }
}

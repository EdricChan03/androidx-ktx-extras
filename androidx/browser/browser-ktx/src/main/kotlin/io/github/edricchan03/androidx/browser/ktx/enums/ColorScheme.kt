package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent
import io.github.edricchan03.androidx.common.enums.EnumFromValue

/**
 * The colour scheme to use.
 *
 * ## Numerical representations
 *
 * The numerical representation of this enum's entries are as listed below:
 *
 * Enum value | [Int] equivalent (accessible via [value])
 * ---|---
 * [ColorScheme.System] | [CustomTabsIntent.COLOR_SCHEME_SYSTEM]
 * [ColorScheme.Light] | [CustomTabsIntent.COLOR_SCHEME_LIGHT]
 * [ColorScheme.Dark] | [CustomTabsIntent.COLOR_SCHEME_DARK]
 *
 * @property value The numerical representation.
 *
 * @since 0.0.1
 * @see io.github.edricchan03.androidx.browser.ktx.setColorScheme
 * @see CustomTabsIntent.Builder.setColorScheme
 */
public enum class ColorScheme(public val value: Int) {
    /**
     * Applies either a light or dark colour scheme to the user interface in
     * the custom tab depending on the user's system settings.
     * @since 0.0.1
     * @see CustomTabsIntent.COLOR_SCHEME_SYSTEM
     */
    System(CustomTabsIntent.COLOR_SCHEME_SYSTEM),

    /**
     * Applies a light colour scheme to the user interface in the custom tab.
     * @since 0.0.1
     * @see CustomTabsIntent.COLOR_SCHEME_LIGHT
     */
    Light(CustomTabsIntent.COLOR_SCHEME_LIGHT),

    /**
     * Applies a dark colour scheme to the user interface in the custom tab.
     * Colours set through [CustomTabsIntent.EXTRA_TOOLBAR_COLOR] may be darkened to
     * match user expectations.
     * @since 0.0.1
     * @see CustomTabsIntent.COLOR_SCHEME_DARK
     */
    Dark(CustomTabsIntent.COLOR_SCHEME_DARK);

    /**
     * Companion object exposing methods to retrieve a [ColorScheme]
     * given its numerical representation.
     * @since 0.0.1
     */
    public companion object : EnumFromValue<Int, ColorScheme>(System) {
        override fun fromValueOrNull(value: Int): ColorScheme? = when (value) {
            CustomTabsIntent.COLOR_SCHEME_SYSTEM -> System
            CustomTabsIntent.COLOR_SCHEME_LIGHT -> Light
            CustomTabsIntent.COLOR_SCHEME_DARK -> Dark
            else -> null
        }
    }
}

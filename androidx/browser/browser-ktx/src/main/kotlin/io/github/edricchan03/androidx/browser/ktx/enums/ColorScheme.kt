package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent

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
 * @see CustomTabsIntent.Builder.setColorScheme
 */
@Suppress("unused")
public enum class ColorScheme(public val value: Int) {
    /**
     * Applies either a light or dark colour scheme to the user interface in
     * the custom tab depending on the user's system settings.
     * @see CustomTabsIntent.COLOR_SCHEME_SYSTEM
     */
    System(CustomTabsIntent.COLOR_SCHEME_SYSTEM),

    /**
     * Applies a light colour scheme to the user interface in the custom tab.
     * @see CustomTabsIntent.COLOR_SCHEME_LIGHT
     */
    Light(CustomTabsIntent.COLOR_SCHEME_LIGHT),

    /**
     * Applies a dark colour scheme to the user interface in the custom tab.
     * Colours set through [CustomTabsIntent.EXTRA_TOOLBAR_COLOR] may be darkened to
     * match user expectations.
     * @see CustomTabsIntent.COLOR_SCHEME_DARK
     */
    Dark(CustomTabsIntent.COLOR_SCHEME_DARK);

    public companion object {
        /**
         * Gets the [ColorScheme] representation of [value], or `null` if no such
         * representation exists.
         */
        public fun fromValue(value: Int): ColorScheme? = when (value) {
            CustomTabsIntent.COLOR_SCHEME_SYSTEM -> System
            CustomTabsIntent.COLOR_SCHEME_LIGHT -> Light
            CustomTabsIntent.COLOR_SCHEME_DARK -> Dark
            else -> null
        }

        /**
         * Gets the [ColorScheme] representation of [value], or [default] if no such
         * representation exists.
         */
        public fun fromValueOrElse(
            value: Int, default: ColorScheme = System
        ): ColorScheme = fromValue(value) ?: default
    }
}

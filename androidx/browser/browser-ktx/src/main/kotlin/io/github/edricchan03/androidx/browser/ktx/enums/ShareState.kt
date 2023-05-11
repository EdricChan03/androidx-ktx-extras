package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent

/**
 * The share state to use.
 * @see CustomTabsIntent.Builder.setShareState
 */
@Suppress("unused")
public enum class ShareState(public val value: Int) {
    /**
     * Applies the default share settings depending on the browser.
     * @see CustomTabsIntent.SHARE_STATE_DEFAULT
     */
    Default(CustomTabsIntent.SHARE_STATE_DEFAULT),

    /**
     * Shows a share option in the tab.
     * @see CustomTabsIntent.SHARE_STATE_ON
     */
    On(CustomTabsIntent.SHARE_STATE_ON),

    /**
     * Explicitly does not show a share option in the tab.
     * @see CustomTabsIntent.SHARE_STATE_OFF
     */
    Off(CustomTabsIntent.SHARE_STATE_OFF);

    public companion object {
        /**
         * Gets the [ShareState] representation of [value], or `null` if no such
         * representation exists.
         */
        public fun fromValue(value: Int): ShareState? = when (value) {
            CustomTabsIntent.SHARE_STATE_DEFAULT -> Default
            CustomTabsIntent.SHARE_STATE_ON -> On
            CustomTabsIntent.SHARE_STATE_OFF -> Off
            else -> null
        }

        /**
         * Gets the [ShareState] representation of [value], or [default] if no such
         * representation exists.
         */
        public fun fromValueOrElse(value: Int, default: ShareState = Default): ShareState =
            fromValue(value) ?: default
    }
}

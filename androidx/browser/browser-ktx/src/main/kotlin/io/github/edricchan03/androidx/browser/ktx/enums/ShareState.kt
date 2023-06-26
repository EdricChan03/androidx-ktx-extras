package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent
import io.github.edricchan03.androidx.common.enums.EnumFromValue

/**
 * The share state to use.
 *
 * ## Numerical representations
 *
 * The numerical representation of this enum's entries are as listed below:
 *
 * Enum value | [Int] equivalent (accessible via [value])
 * ---|---
 * [ShareState.Default] | [CustomTabsIntent.SHARE_STATE_DEFAULT]
 * [ShareState.On] | [CustomTabsIntent.SHARE_STATE_ON]
 * [ShareState.Off] | [CustomTabsIntent.SHARE_STATE_OFF]
 *
 * @property value The numerical representation.
 *
 * @see io.github.edricchan03.androidx.browser.ktx.setShareState
 * @see CustomTabsIntent.Builder.setShareState
 */
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

    /**
     * Companion object exposing methods to retrieve a [ShareState]
     * given its numerical representation.
     */
    public companion object : EnumFromValue<Int, ShareState>(Default) {
        override fun fromValueOrNull(value: Int): ShareState? = when (value) {
            CustomTabsIntent.SHARE_STATE_DEFAULT -> Default
            CustomTabsIntent.SHARE_STATE_ON -> On
            CustomTabsIntent.SHARE_STATE_OFF -> Off
            else -> null
        }
    }
}

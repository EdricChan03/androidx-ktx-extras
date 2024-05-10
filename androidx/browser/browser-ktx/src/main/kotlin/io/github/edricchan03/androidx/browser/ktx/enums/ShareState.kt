package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent
import io.github.edricchan03.androidx.common.enums.EnumFromValue
import io.github.edricchan03.androidx.common.enums.ValueEnum

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
 * From version 0.3.0, this enum class implements the [ValueEnum] interface, which
 * contains the [ValueEnum.value] property.
 * @since 0.0.1
 * @see io.github.edricchan03.androidx.browser.ktx.setShareState
 * @see CustomTabsIntent.Builder.setShareState
 */
public enum class ShareState(
    @CustomTabsIntent.ShareState public override val value: Int
) : ValueEnum<Int> {
    /**
     * Applies the default share settings depending on the browser.
     * @since 0.0.1
     * @see CustomTabsIntent.SHARE_STATE_DEFAULT
     */
    Default(CustomTabsIntent.SHARE_STATE_DEFAULT),

    /**
     * Shows a share option in the tab.
     * @since 0.0.1
     * @see CustomTabsIntent.SHARE_STATE_ON
     */
    On(CustomTabsIntent.SHARE_STATE_ON),

    /**
     * Explicitly does not show a share option in the tab.
     * @since 0.0.1
     * @see CustomTabsIntent.SHARE_STATE_OFF
     */
    Off(CustomTabsIntent.SHARE_STATE_OFF);

    /**
     * Companion object exposing methods to retrieve a [ShareState]
     * given its numerical representation.
     * @since 0.0.1
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

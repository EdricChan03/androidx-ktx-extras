package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent

/**
 * The position of the close button.
 *
 * ## Numerical representations
 *
 * The numerical representation of this enum's entries are as listed below:
 *
 * Enum value | [Int] equivalent (accessible via [value])
 * ---|---
 * [CloseButtonPosition.Default] | [CustomTabsIntent.CLOSE_BUTTON_POSITION_DEFAULT]
 * [CloseButtonPosition.Start] | [CustomTabsIntent.CLOSE_BUTTON_POSITION_START]
 * [CloseButtonPosition.End] | [CustomTabsIntent.CLOSE_BUTTON_POSITION_END]
 *
 * @property value The numerical representation.
 *
 * @see io.github.edricchan03.androidx.browser.ktx.setCloseButtonPosition
 * @see CustomTabsIntent.Builder.setCloseButtonPosition
 */
public enum class CloseButtonPosition(public val value: Int) {
    /**
     * Same as [CloseButtonPosition.Start].
     * @see CustomTabsIntent.CLOSE_BUTTON_POSITION_DEFAULT
     */
    Default(CustomTabsIntent.CLOSE_BUTTON_POSITION_DEFAULT),

    /**
     * Positions the close button at the start of the toolbar.
     * @see CustomTabsIntent.CLOSE_BUTTON_POSITION_START
     */
    Start(CustomTabsIntent.CLOSE_BUTTON_POSITION_START),

    /**
     * Positions the close button at the end of the toolbar.
     * @see CustomTabsIntent.CLOSE_BUTTON_POSITION_END
     */
    End(CustomTabsIntent.CLOSE_BUTTON_POSITION_END);

    public companion object {
        /**
         * Gets the [CloseButtonPosition] representation of [value], or `null` if
         * no such representation exists.
         */
        public fun fromValue(value: Int): CloseButtonPosition? = when (value) {
            CustomTabsIntent.CLOSE_BUTTON_POSITION_DEFAULT -> Default
            CustomTabsIntent.CLOSE_BUTTON_POSITION_START -> Start
            CustomTabsIntent.CLOSE_BUTTON_POSITION_END -> End
            else -> null
        }

        /**
         * Gets the [CloseButtonPosition] representation of [value], or [default] if
         * no such representation exists.
         */
        public fun fromValueOrElse(
            value: Int, default: CloseButtonPosition = Default
        ): CloseButtonPosition = fromValue(value) ?: default
    }
}

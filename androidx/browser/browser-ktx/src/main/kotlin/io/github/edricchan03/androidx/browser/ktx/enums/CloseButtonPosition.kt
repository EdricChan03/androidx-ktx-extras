package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent
import io.github.edricchan03.androidx.common.enums.EnumFromValue
import io.github.edricchan03.androidx.common.enums.ValueEnum

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
 * From version 0.3.0, this enum class implements the [ValueEnum] interface, which
 * contains the [ValueEnum.value] property.
 * @since 0.0.1
 * @see io.github.edricchan03.androidx.browser.ktx.setCloseButtonPosition
 * @see CustomTabsIntent.Builder.setCloseButtonPosition
 */
public enum class CloseButtonPosition(
    @CustomTabsIntent.CloseButtonPosition public override val value: Int
) : ValueEnum<Int> {
    /**
     * Same as [CloseButtonPosition.Start].
     * @since 0.0.1
     * @see CustomTabsIntent.CLOSE_BUTTON_POSITION_DEFAULT
     */
    Default(CustomTabsIntent.CLOSE_BUTTON_POSITION_DEFAULT),

    /**
     * Positions the close button at the start of the toolbar.
     * @since 0.0.1
     * @see CustomTabsIntent.CLOSE_BUTTON_POSITION_START
     */
    Start(CustomTabsIntent.CLOSE_BUTTON_POSITION_START),

    /**
     * Positions the close button at the end of the toolbar.
     * @since 0.0.1
     * @see CustomTabsIntent.CLOSE_BUTTON_POSITION_END
     */
    End(CustomTabsIntent.CLOSE_BUTTON_POSITION_END);

    /**
     * Companion object exposing methods to retrieve a [CloseButtonPosition]
     * given its numerical representation.
     * @since 0.0.1
     */
    public companion object : EnumFromValue<Int, CloseButtonPosition>(Default) {
        override fun fromValueOrNull(value: Int): CloseButtonPosition? = when (value) {
            CustomTabsIntent.CLOSE_BUTTON_POSITION_DEFAULT -> Default
            CustomTabsIntent.CLOSE_BUTTON_POSITION_START -> Start
            CustomTabsIntent.CLOSE_BUTTON_POSITION_END -> End
            else -> null
        }
    }
}

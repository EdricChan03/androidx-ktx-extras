package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent
import io.github.edricchan03.androidx.common.enums.EnumFromValue

/**
 * Specifies the position of the side sheet.
 *
 * ## Numerical representations
 *
 * The numerical representation of this enum's entries are as listed below:
 *
 * Enum value | [Int] equivalent (accessible via [value])
 * ---|---
 * [ActivitySideSheetPosition.Default] | [CustomTabsIntent.ACTIVITY_SIDE_SHEET_POSITION_DEFAULT]
 * [ActivitySideSheetPosition.Start] | [CustomTabsIntent.ACTIVITY_SIDE_SHEET_POSITION_START]
 * [ActivitySideSheetPosition.End] | [CustomTabsIntent.ACTIVITY_SIDE_SHEET_POSITION_END]
 *
 * @property value The numerical representation.
 *
 * @since 0.3.0
 * @see CustomTabsIntent.EXTRA_ACTIVITY_SIDE_SHEET_POSITION
 * @see CustomTabsIntent.Builder.setActivitySideSheetPosition
 * @see io.github.edricchan03.androidx.browser.ktx.setActivitySideSheetPosition
 * @see io.github.edricchan03.androidx.browser.ktx.activitySideSheetPosition
 */
public enum class ActivitySideSheetPosition(
    @CustomTabsIntent.ActivitySideSheetPosition public val value: Int
) {
    /**
     * Applies the default position for the Custom Tab Activity when it behaves as a side sheet.
     * Same as [End].
     * @since 0.3.0
     * @see CustomTabsIntent.ACTIVITY_SIDE_SHEET_POSITION_DEFAULT
     */
    Default(CustomTabsIntent.ACTIVITY_SIDE_SHEET_POSITION_DEFAULT),

    /**
     * Position the side sheet on the start side of the screen.
     * @since 0.3.0
     * @see CustomTabsIntent.ACTIVITY_SIDE_SHEET_POSITION_START
     */
    Start(CustomTabsIntent.ACTIVITY_SIDE_SHEET_POSITION_START),

    /**
     * Position the side sheet on the end side of the screen.
     * @since 0.3.0
     * @see CustomTabsIntent.ACTIVITY_SIDE_SHEET_POSITION_END
     */
    End(CustomTabsIntent.ACTIVITY_SIDE_SHEET_POSITION_END);

    public companion object : EnumFromValue<Int, ActivitySideSheetPosition>(Default) {
        override fun fromValueOrNull(value: Int): ActivitySideSheetPosition? = when (value) {
            CustomTabsIntent.ACTIVITY_SIDE_SHEET_POSITION_DEFAULT -> Default
            CustomTabsIntent.ACTIVITY_SIDE_SHEET_POSITION_START -> Start
            CustomTabsIntent.ACTIVITY_SIDE_SHEET_POSITION_END -> End
            else -> null
        }
    }
}

package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent
import io.github.edricchan03.androidx.common.enums.EnumFromValue

/**
 * The type of rounded corners that will be used for the side sheet.
 *
 * ## Numerical representations
 *
 * The numerical representation of this enum's entries are as listed below:
 *
 * Enum value | [Int] equivalent (accessible via [value])
 * ---|---
 * [ActivitySideSheetRoundedCornersPosition.Default] | [CustomTabsIntent.ACTIVITY_SIDE_SHEET_ROUNDED_CORNERS_POSITION_DEFAULT]
 * [ActivitySideSheetRoundedCornersPosition.None] | [CustomTabsIntent.ACTIVITY_SIDE_SHEET_ROUNDED_CORNERS_POSITION_NONE]
 * [ActivitySideSheetRoundedCornersPosition.Top] | [CustomTabsIntent.ACTIVITY_SIDE_SHEET_ROUNDED_CORNERS_POSITION_TOP]
 *
 * @property value The numerical representation.
 *
 * @since 0.3.0
 * @see io.github.edricchan03.androidx.browser.ktx.activitySideSheetRoundedCornersPosition
 * @see io.github.edricchan03.androidx.browser.ktx.setActivitySideSheetRoundedCornersPosition
 * @see CustomTabsIntent.Builder.setActivitySideSheetRoundedCornersPosition
 */
public enum class ActivitySideSheetRoundedCornersPosition(
    @CustomTabsIntent.ActivitySideSheetRoundedCornersPosition public val value: Int
) {
    /**
     * Side sheet's default rounded corner configuration.
     * Same as [None].
     * @since 0.3.0
     * @see CustomTabsIntent.ACTIVITY_SIDE_SHEET_ROUNDED_CORNERS_POSITION_DEFAULT
     */
    Default(CustomTabsIntent.ACTIVITY_SIDE_SHEET_ROUNDED_CORNERS_POSITION_DEFAULT),

    /**
     * Side sheet with no rounded corners.
     * @since 0.3.0
     * @see CustomTabsIntent.ACTIVITY_SIDE_SHEET_ROUNDED_CORNERS_POSITION_NONE
     */
    None(CustomTabsIntent.ACTIVITY_SIDE_SHEET_ROUNDED_CORNERS_POSITION_NONE),

    /**
     * Side sheet with the inner top corner rounded (if positioned on the
     * right of the screen, this will be the top left corner)
     * @since 0.3.0
     * @see CustomTabsIntent.ACTIVITY_SIDE_SHEET_ROUNDED_CORNERS_POSITION_TOP
     */
    Top(CustomTabsIntent.ACTIVITY_SIDE_SHEET_ROUNDED_CORNERS_POSITION_TOP);

    /**
     * Companion object exposing methods to retrieve a [ActivitySideSheetRoundedCornersPosition]
     * given its numerical representation.
     * @since 0.3.0
     */
    public companion object : EnumFromValue<Int, ActivitySideSheetRoundedCornersPosition>(Default) {
        override fun fromValueOrNull(value: Int): ActivitySideSheetRoundedCornersPosition? =
            when (value) {
                CustomTabsIntent.ACTIVITY_SIDE_SHEET_ROUNDED_CORNERS_POSITION_DEFAULT -> Default
                CustomTabsIntent.ACTIVITY_SIDE_SHEET_ROUNDED_CORNERS_POSITION_NONE -> None
                CustomTabsIntent.ACTIVITY_SIDE_SHEET_ROUNDED_CORNERS_POSITION_TOP -> Top
                else -> null
            }
    }
}

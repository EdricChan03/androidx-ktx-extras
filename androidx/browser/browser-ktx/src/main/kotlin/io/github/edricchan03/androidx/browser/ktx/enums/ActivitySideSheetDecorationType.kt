package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent
import io.github.edricchan03.androidx.common.enums.EnumFromValue

/**
 * The type of the decoration that will be used to separate the side sheet from the
 * Custom Tabs embedder.
 *
 * ## Numerical representations
 *
 * The numerical representation of this enum's entries are as listed below:
 *
 * Enum value | [Int] equivalent (accessible via [value])
 * ---|---
 * [ActivitySideSheetDecorationType.Default] | [CustomTabsIntent.ACTIVITY_SIDE_SHEET_DECORATION_TYPE_DEFAULT]
 * [ActivitySideSheetDecorationType.None] | [CustomTabsIntent.ACTIVITY_SIDE_SHEET_DECORATION_TYPE_NONE]
 * [ActivitySideSheetDecorationType.Shadow] | [CustomTabsIntent.ACTIVITY_SIDE_SHEET_DECORATION_TYPE_SHADOW]
 * [ActivitySideSheetDecorationType.Divider] | [CustomTabsIntent.ACTIVITY_SIDE_SHEET_DECORATION_TYPE_DIVIDER]
 *
 * @property value The numerical representation.
 *
 * @since 0.3.0
 * @see CustomTabsIntent.EXTRA_ACTIVITY_SIDE_SHEET_DECORATION_TYPE
 * @see CustomTabsIntent.Builder.setActivitySideSheetDecorationType
 * @see io.github.edricchan03.androidx.browser.ktx.setActivitySideSheetDecorationType
 * @see io.github.edricchan03.androidx.browser.ktx.activitySideSheetDecorationType
 */
public enum class ActivitySideSheetDecorationType(
    @CustomTabsIntent.ActivitySideSheetDecorationType public val value: Int
) {
    /**
     * Side sheet's default decoration type.
     * Same as [Shadow].
     * @since 0.3.0
     * @see CustomTabsIntent.ACTIVITY_SIDE_SHEET_DECORATION_TYPE_DEFAULT
     */
    Default(CustomTabsIntent.ACTIVITY_SIDE_SHEET_DECORATION_TYPE_DEFAULT),

    /**
     * Side sheet with no decorations - the activity is not bordered by any
     * shadow or divider line.
     * @since 0.3.0
     * @see CustomTabsIntent.ACTIVITY_SIDE_SHEET_DECORATION_TYPE_NONE
     */
    None(CustomTabsIntent.ACTIVITY_SIDE_SHEET_DECORATION_TYPE_NONE),

    /**
     * Side sheet with shadow decoration - the activity is bordered by a shadow effect.
     * @since 0.3.0
     * @see CustomTabsIntent.ACTIVITY_SIDE_SHEET_DECORATION_TYPE_SHADOW
     */
    Shadow(CustomTabsIntent.ACTIVITY_SIDE_SHEET_DECORATION_TYPE_SHADOW),

    /**
     * Side sheet with a divider line - the activity is bordered by a thin opaque line.
     * @since 0.3.0
     * @see CustomTabsIntent.ACTIVITY_SIDE_SHEET_DECORATION_TYPE_DIVIDER
     */
    Divider(CustomTabsIntent.ACTIVITY_SIDE_SHEET_DECORATION_TYPE_DIVIDER);

    public companion object : EnumFromValue<Int, ActivitySideSheetDecorationType>(Default) {
        override fun fromValueOrNull(value: Int): ActivitySideSheetDecorationType? = when (value) {
            CustomTabsIntent.ACTIVITY_SIDE_SHEET_DECORATION_TYPE_DEFAULT -> Default
            CustomTabsIntent.ACTIVITY_SIDE_SHEET_DECORATION_TYPE_NONE -> None
            CustomTabsIntent.ACTIVITY_SIDE_SHEET_DECORATION_TYPE_SHADOW -> Shadow
            CustomTabsIntent.ACTIVITY_SIDE_SHEET_DECORATION_TYPE_DIVIDER -> Divider
            else -> null
        }
    }
}

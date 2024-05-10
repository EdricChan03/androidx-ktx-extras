package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent
import io.github.edricchan03.androidx.common.enums.EnumFromValue
import io.github.edricchan03.androidx.common.enums.ValueEnum

/**
 * Sets the resize behaviour for the custom tab activity.
 *
 * ## Numerical representations
 *
 * The numerical representation of this enum's entries are as listed below:
 *
 * Enum value | [Int] equivalent (accessible via [value])
 * ---|---
 * [ActivityHeightResizeBehavior.Default] | [CustomTabsIntent.ACTIVITY_HEIGHT_DEFAULT]
 * [ActivityHeightResizeBehavior.Adjustable] | [CustomTabsIntent.ACTIVITY_HEIGHT_ADJUSTABLE]
 * [ActivityHeightResizeBehavior.Fixed] | [CustomTabsIntent.ACTIVITY_HEIGHT_FIXED]
 *
 * @property value The numerical representation.
 *
 * From version 0.3.0, this enum class implements the [ValueEnum] interface, which
 * contains the [ValueEnum.value] property.
 * @since 0.0.1
 * @see io.github.edricchan03.androidx.browser.ktx.setInitialActivityHeightPx
 * @see CustomTabsIntent.Builder.setInitialActivityHeightPx
 */
public enum class ActivityHeightResizeBehavior(
    @CustomTabsIntent.ActivityHeightResizeBehavior public override val value: Int
) : ValueEnum<Int> {
    /**
     * Applies the default height resize behavior for the Custom Tab Activity
     * when it behaves as a bottom sheet.
     * @since 0.0.1
     * @see CustomTabsIntent.ACTIVITY_HEIGHT_DEFAULT
     */
    Default(CustomTabsIntent.ACTIVITY_HEIGHT_DEFAULT),

    /**
     * The Custom Tab Activity, when it behaves as a bottom sheet, can have its
     * height manually resized by the user.
     * @since 0.0.1
     * @see CustomTabsIntent.ACTIVITY_HEIGHT_ADJUSTABLE
     */
    Adjustable(CustomTabsIntent.ACTIVITY_HEIGHT_ADJUSTABLE),

    /**
     * The Custom Tab Activity, when it behaves as a bottom sheet, cannot have its
     * height manually resized by the user.
     * @since 0.0.1
     * @see CustomTabsIntent.ACTIVITY_HEIGHT_FIXED
     */
    Fixed(CustomTabsIntent.ACTIVITY_HEIGHT_FIXED);

    /**
     * Companion object exposing methods to retrieve a [ActivityHeightResizeBehavior]
     * given its numerical representation.
     * @since 0.0.1
     */
    public companion object : EnumFromValue<Int, ActivityHeightResizeBehavior>(Default) {
        override fun fromValueOrNull(value: Int): ActivityHeightResizeBehavior? =
            when (value) {
                CustomTabsIntent.ACTIVITY_HEIGHT_DEFAULT -> Default
                CustomTabsIntent.ACTIVITY_HEIGHT_ADJUSTABLE -> Adjustable
                CustomTabsIntent.ACTIVITY_HEIGHT_FIXED -> Fixed
                else -> null
            }
    }
}

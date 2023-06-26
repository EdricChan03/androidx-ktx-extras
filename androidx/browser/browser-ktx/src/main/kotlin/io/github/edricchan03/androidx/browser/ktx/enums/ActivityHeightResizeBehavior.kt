package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent

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
 * @see io.github.edricchan03.androidx.browser.ktx.setInitialActivityHeightPx
 * @see CustomTabsIntent.Builder.setInitialActivityHeightPx
 */
@Suppress("unused")
public enum class ActivityHeightResizeBehavior(public val value: Int) {
    /**
     * Applies the default height resize behavior for the Custom Tab Activity
     * when it behaves as a bottom sheet.
     * @see CustomTabsIntent.ACTIVITY_HEIGHT_DEFAULT
     */
    Default(CustomTabsIntent.ACTIVITY_HEIGHT_DEFAULT),

    /**
     * The Custom Tab Activity, when it behaves as a bottom sheet, can have its
     * height manually resized by the user.
     * @see CustomTabsIntent.ACTIVITY_HEIGHT_ADJUSTABLE
     */
    Adjustable(CustomTabsIntent.ACTIVITY_HEIGHT_ADJUSTABLE),

    /**
     * The Custom Tab Activity, when it behaves as a bottom sheet, cannot have its
     * height manually resized by the user.
     * @see CustomTabsIntent.ACTIVITY_HEIGHT_FIXED
     */
    Fixed(CustomTabsIntent.ACTIVITY_HEIGHT_FIXED);

    public companion object {
        /**
         * Gets the [ActivityHeightResizeBehavior] representation of [value], or `null` if
         * no such representation exists.
         */
        public fun fromValue(value: Int): ActivityHeightResizeBehavior? = when (value) {
            CustomTabsIntent.ACTIVITY_HEIGHT_DEFAULT -> Default
            CustomTabsIntent.ACTIVITY_HEIGHT_ADJUSTABLE -> Adjustable
            CustomTabsIntent.ACTIVITY_HEIGHT_FIXED -> Fixed
            else -> null
        }

        /**
         * Gets the [ActivityHeightResizeBehavior] representation of [value], or [default] if
         * no such representation exists.
         */
        public fun fromValueOrElse(
            value: Int, default: ActivityHeightResizeBehavior = Default
        ): ActivityHeightResizeBehavior = fromValue(value) ?: default
    }
}

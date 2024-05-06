package io.github.edricchan03.androidx.browser.ktx

import android.content.Intent
import android.util.Size
import androidx.annotation.Dimension
import androidx.browser.customtabs.CustomTabsIntent
import io.github.edricchan03.androidx.browser.ktx.annotations.ExperimentalBrowserApi
import io.github.edricchan03.androidx.browser.ktx.enums.ActivityHeightResizeBehavior
import io.github.edricchan03.androidx.browser.ktx.enums.ActivitySideSheetDecorationType
import io.github.edricchan03.androidx.browser.ktx.enums.ActivitySideSheetPosition
import io.github.edricchan03.androidx.browser.ktx.enums.ActivitySideSheetRoundedCornersPosition

/**
 * Extra that, if set, makes the Custom Tab Activity's width to be x pixels, the Custom Tab
 * will behave as a side sheet. A minimum width will be enforced, thus the width will be
 * clamped as such (based on the window size classes as defined by the Android documentation):
 *
 * * Compact, window width <600dp - a side sheet will not be displayed.
 * * Medium, window width >=600dp and <840 dp - between 50% and 100% of the window's
 *   width.
 * * Expanded, window width >=840dp - between 33% and 100% of the window's width.
 *
 * [Android Size Classes](https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes#window_size_classes)
 * @since 0.3.0
 * @see CustomTabsIntent.EXTRA_INITIAL_ACTIVITY_WIDTH_PX
 * @see CustomTabsIntent.getInitialActivityWidthPx
 * @see CustomTabsIntent.Builder.setInitialActivityWidthPx
 */
@set:ExperimentalBrowserApi
@get:Dimension(unit = Dimension.PX)
@setparam:Dimension(unit = Dimension.PX)
public var Intent.initialActivityWidthPx: Int
    get() = CustomTabsIntent.getInitialActivityWidthPx(this)
    set(value) {
        putExtra(CustomTabsIntent.EXTRA_INITIAL_ACTIVITY_WIDTH_PX, value)
    }

/**
 * Sets the Custom Tab Activity's initial width/height in pixels.
 * The Custom Tab will behave as a side sheet if the screen's width is
 * bigger than the breakpoint value set by
 * [CustomTabsIntent.Builder.setActivitySideSheetBreakpointDp]
 * and the screen is big enough, see doc for [CustomTabsIntent.EXTRA_INITIAL_ACTIVITY_WIDTH_PX].
 *
 * This is equivalent to calling [CustomTabsIntent.Builder.setInitialActivityWidthPx]
 * and [setInitialActivityHeightPx] with [widthPx] and [heightPx] + [activityHeightResizeBehavior]
 * respectively.
 * @param widthPx The Custom Tab Activity's initial width in pixels.
 * @param heightPx The Custom Tab Activity's initial height in pixels.
 * @param activityHeightResizeBehavior Desired height behavior.
 * @since 0.3.0
 * @see CustomTabsIntent.Builder.setInitialActivityWidthPx
 * @see CustomTabsIntent.Builder.setInitialActivityHeightPx
 * @see setInitialActivityHeightPx
 */
public fun CustomTabsIntent.Builder.setInitialActivitySizePx(
    @Dimension(unit = Dimension.PX) widthPx: Int,
    @Dimension(unit = Dimension.PX) heightPx: Int,
    activityHeightResizeBehavior: ActivityHeightResizeBehavior = ActivityHeightResizeBehavior.Default
): CustomTabsIntent.Builder = apply {
    setInitialActivityWidthPx(widthPx)
    setInitialActivityHeightPx(heightPx, activityHeightResizeBehavior)
}

/**
 * Sets the Custom Tab Activity's initial width/height in pixels.
 * The Custom Tab will behave as a side sheet if the screen's width is
 * bigger than the breakpoint value set by
 * [CustomTabsIntent.Builder.setActivitySideSheetBreakpointDp]
 * and the screen is big enough, see doc for [CustomTabsIntent.EXTRA_INITIAL_ACTIVITY_WIDTH_PX].
 *
 * This is equivalent to calling [setInitialActivitySizePx] with `widthPx` and `heightPx`
 * set to [sizePx]'s first and second entries respectively, as well as
 * [activityHeightResizeBehavior].
 * @param sizePx The Custom Tab Activity's initial width + height in pixels.
 * @param activityHeightResizeBehavior Desired height behavior.
 * @since 0.3.0
 * @see CustomTabsIntent.Builder.setInitialActivityWidthPx
 * @see CustomTabsIntent.Builder.setInitialActivityHeightPx
 * @see setInitialActivityHeightPx
 */
public fun CustomTabsIntent.Builder.setInitialActivitySizePx(
    @Dimension(unit = Dimension.PX) sizePx: Pair<Int, Int>,
    activityHeightResizeBehavior: ActivityHeightResizeBehavior = ActivityHeightResizeBehavior.Default
): CustomTabsIntent.Builder =
    setInitialActivitySizePx(sizePx.first, sizePx.second, activityHeightResizeBehavior)

/**
 * Sets the Custom Tab Activity's initial width/height in pixels.
 * The Custom Tab will behave as a side sheet if the screen's width is
 * bigger than the breakpoint value set by
 * [CustomTabsIntent.Builder.setActivitySideSheetBreakpointDp]
 * and the screen is big enough, see doc for [CustomTabsIntent.EXTRA_INITIAL_ACTIVITY_WIDTH_PX].
 *
 * This is equivalent to calling [setInitialActivitySizePx] with `widthPx` and `heightPx`
 * set to [size]'s [Size.getWidth] and [Size.getHeight] values respectively, as well as
 * [activityHeightResizeBehavior].
 * @param size The Custom Tab Activity's initial width + height in pixels.
 * @param activityHeightResizeBehavior Desired height behavior.
 * @since 0.3.0
 * @see CustomTabsIntent.Builder.setInitialActivityWidthPx
 * @see CustomTabsIntent.Builder.setInitialActivityHeightPx
 * @see setInitialActivityHeightPx
 */
public fun CustomTabsIntent.Builder.setInitialActivitySizePx(
    size: Size,
    activityHeightResizeBehavior: ActivityHeightResizeBehavior = ActivityHeightResizeBehavior.Default
): CustomTabsIntent.Builder =
    setInitialActivitySizePx(size.width, size.height, activityHeightResizeBehavior)

/**
 * Extra that, if set, allows you to set a custom breakpoint for the Custom Tab -
 * a value, x, for which if the screen's width is higher than x, the Custom Tab will behave as a
 * side sheet (if [CustomTabsIntent.EXTRA_INITIAL_ACTIVITY_WIDTH_PX] is set), otherwise
 * it will behave as a bottom sheet (if
 * [CustomTabsIntent.EXTRA_INITIAL_ACTIVITY_HEIGHT_PX] is set).
 *
 * If this Intent Extra is not set the browser implementation should set as default value
 * 840dp. If x is set to <600dp the browser implementation should default it to 600dp.
 * @since 0.3.0
 * @see CustomTabsIntent.EXTRA_ACTIVITY_SIDE_SHEET_BREAKPOINT_DP
 * @see CustomTabsIntent.getActivitySideSheetBreakpointDp
 * @see CustomTabsIntent.Builder.setActivitySideSheetBreakpointDp
 */
@set:ExperimentalBrowserApi
@get:Dimension(unit = Dimension.DP)
@setparam:Dimension(unit = Dimension.DP)
public var Intent.activitySideSheetBreakpointDp: Int
    get() = CustomTabsIntent.getActivitySideSheetBreakpointDp(this)
    set(value) {
        putExtra(CustomTabsIntent.EXTRA_ACTIVITY_SIDE_SHEET_BREAKPOINT_DP, value)
    }

/**
 * Extra that specifies the position of the side sheet.
 * By default it is set to [ActivitySideSheetPosition.End],
 * which is on the right side in left-to-right layout.
 * @since 0.3.0
 * @see ActivitySideSheetPosition
 * @see CustomTabsIntent.EXTRA_ACTIVITY_SIDE_SHEET_POSITION
 * @see CustomTabsIntent.getActivitySideSheetPosition
 * @see CustomTabsIntent.Builder.setActivitySideSheetPosition
 */
@set:ExperimentalBrowserApi
public var Intent.activitySideSheetPosition: ActivitySideSheetPosition
    get() = ActivitySideSheetPosition.fromValue(
        CustomTabsIntent.getActivitySideSheetPosition(this)
    )
    set(position) {
        putExtra(CustomTabsIntent.EXTRA_ACTIVITY_SIDE_SHEET_POSITION, position.value)
    }

/**
 * Sets the Custom Tab Activity's position when acting as a side sheet.
 *
 * This variant allows for the enum equivalent [ActivitySideSheetPosition] to be specified.
 * @param position The Custom Tab Activity's position.
 * @since 0.3.0
 * @see ActivitySideSheetPosition
 * @see activitySideSheetPosition
 * @see CustomTabsIntent.Builder.setActivitySideSheetPosition
 * @see CustomTabsIntent.EXTRA_ACTIVITY_SIDE_SHEET_POSITION
 */
public fun CustomTabsIntent.Builder.setActivitySideSheetPosition(
    position: ActivitySideSheetPosition
): CustomTabsIntent.Builder = setActivitySideSheetPosition(position.value)

/**
 * Extra that, if set, allows you to set how you want to distinguish the
 * Partial Custom Tab side sheet from the rest of the display.
 *
 * Options include shadow, a divider line, or no decoration.
 * @since 0.3.0
 * @see ActivitySideSheetDecorationType
 * @see CustomTabsIntent.EXTRA_ACTIVITY_SIDE_SHEET_DECORATION_TYPE
 * @see CustomTabsIntent.getActivitySideSheetDecorationType
 * @see CustomTabsIntent.Builder.setActivitySideSheetDecorationType
 */
@set:ExperimentalBrowserApi
public var Intent.activitySideSheetDecorationType: ActivitySideSheetDecorationType
    get() = ActivitySideSheetDecorationType.fromValue(
        CustomTabsIntent.getActivitySideSheetDecorationType(this)
    )
    set(type) {
        putExtra(CustomTabsIntent.EXTRA_ACTIVITY_SIDE_SHEET_DECORATION_TYPE, type.value)
    }

/**
 * Sets the Custom Tab Activity's decoration type that will be displayed
 * when it is acting as a side sheet.
 *
 * This variant allows for the enum equivalent [ActivitySideSheetDecorationType]
 * to be specified.
 * @param type The Custom Tab Activity's decoration type.
 * @since 0.3.0
 * @see ActivitySideSheetDecorationType
 * @see activitySideSheetDecorationType
 * @see CustomTabsIntent.Builder.setActivitySideSheetDecorationType
 * @see CustomTabsIntent.EXTRA_ACTIVITY_SIDE_SHEET_DECORATION_TYPE
 */
public fun CustomTabsIntent.Builder.setActivitySideSheetDecorationType(
    type: ActivitySideSheetDecorationType
): CustomTabsIntent.Builder = setActivitySideSheetDecorationType(type.value)

/**
 * Extra that, if set, allows you to choose which side sheet corners should be
 * rounded, if any at all. Options include top or none.
 * @since 0.3.0
 * @see ActivitySideSheetRoundedCornersPosition
 * @see CustomTabsIntent.EXTRA_ACTIVITY_SIDE_SHEET_ROUNDED_CORNERS_POSITION
 * @see CustomTabsIntent.getActivitySideSheetRoundedCornersPosition
 * @see CustomTabsIntent.Builder.setActivitySideSheetRoundedCornersPosition
 */
@set:ExperimentalBrowserApi
public var Intent.activitySideSheetRoundedCornersPosition: ActivitySideSheetRoundedCornersPosition
    get() = ActivitySideSheetRoundedCornersPosition.fromValue(
        CustomTabsIntent.getActivitySideSheetRoundedCornersPosition(this)
    )
    set(type) {
        putExtra(CustomTabsIntent.EXTRA_ACTIVITY_SIDE_SHEET_ROUNDED_CORNERS_POSITION, type.value)
    }

/**
 * Sets the Custom Tab Activity's rounded corners position when it is acting as a side sheet.
 *
 * This variant allows for the enum equivalent [ActivitySideSheetRoundedCornersPosition]
 * to be specified.
 * @param position The Custom Tab Activity's rounded corners position.
 * @since 0.3.0
 * @see ActivitySideSheetRoundedCornersPosition
 * @see activitySideSheetRoundedCornersPosition
 * @see CustomTabsIntent.Builder.setActivitySideSheetRoundedCornersPosition
 * @see CustomTabsIntent.EXTRA_ACTIVITY_SIDE_SHEET_ROUNDED_CORNERS_POSITION
 */
public fun CustomTabsIntent.Builder.setActivitySideSheetRoundedCornersPosition(
    position: ActivitySideSheetRoundedCornersPosition
): CustomTabsIntent.Builder = setActivitySideSheetRoundedCornersPosition(position.value)

/**
 * Extra that enables the maximization button on the side sheet Custom Tab toolbar.
 * @since 0.3.0
 * @see CustomTabsIntent.isActivitySideSheetMaximizationEnabled
 * @see CustomTabsIntent.Builder.setActivitySideSheetMaximizationEnabled
 * @see CustomTabsIntent.EXTRA_ACTIVITY_SIDE_SHEET_ENABLE_MAXIMIZATION
 */
@set:ExperimentalBrowserApi
public var Intent.activitySideSheetEnableMaximization: Boolean
    get() = CustomTabsIntent.isActivitySideSheetMaximizationEnabled(this)
    set(value) {
        putExtra(CustomTabsIntent.EXTRA_ACTIVITY_SIDE_SHEET_ENABLE_MAXIMIZATION, value)
    }

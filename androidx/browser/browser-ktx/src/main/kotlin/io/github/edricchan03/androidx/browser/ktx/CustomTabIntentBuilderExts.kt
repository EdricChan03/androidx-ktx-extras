package io.github.edricchan03.androidx.browser.ktx

import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import io.github.edricchan03.androidx.browser.ktx.enums.ActivityHeightResizeBehavior
import io.github.edricchan03.androidx.browser.ktx.enums.CloseButtonPosition
import io.github.edricchan03.androidx.browser.ktx.enums.ColorScheme
import io.github.edricchan03.androidx.browser.ktx.enums.ShareState

/**
 * Sets the share state that should be applied to the custom tab.
 * This method allows for an enum [ShareState] to be used.
 * @param state The [ShareState] to use.
 * @since 0.0.1
 * @see CustomTabsIntent.Builder.setShareState
 */
public fun CustomTabsIntent.Builder.setShareState(
    state: ShareState
): CustomTabsIntent.Builder = setShareState(state.value)

/**
 * Sets the colour scheme that should be applied to the user interface in the custom tab.
 * This method allows for an enum [ColorScheme] to be used.
 * @param scheme The desired colour scheme to use.
 * @since 0.0.1
 * @see CustomTabsIntent.Builder.setColorScheme
 */
public fun CustomTabsIntent.Builder.setColorScheme(
    scheme: ColorScheme
): CustomTabsIntent.Builder = setColorScheme(scheme.value)

/**
 * Sets [CustomTabColorSchemeParams] for the given colour scheme.
 *
 * This allows specifying two different toolbar colours for light and dark schemes.
 * It can be useful if [ColorScheme.System] is set: Custom Tabs
 * will follow the system settings and apply the corresponding
 * [CustomTabColorSchemeParams] "on the fly" when the settings change.
 *
 * If there is no [CustomTabColorSchemeParams] for the current scheme, or a particular
 * field of it is null, Custom Tabs will fall back to the defaults provided via
 * [setDefaultColorSchemeParams].
 * ---
 * This method allows for the [ColorScheme] enum to be used.
 * @param scheme A constant representing a color scheme (see [setColorScheme]).
 * It should not be [ColorScheme.System], because that represents a behavior rather
 * than a particular color scheme.
 * @param params An instance of [CustomTabColorSchemeParams].
 * @since 0.0.1
 * @see CustomTabsIntent.Builder.setColorSchemeParams
 */
public fun CustomTabsIntent.Builder.setColorSchemeParams(
    scheme: ColorScheme,
    params: CustomTabColorSchemeParams
): CustomTabsIntent.Builder = setColorSchemeParams(scheme.value, params)

/**
 * Sets [CustomTabColorSchemeParams] for the given colour scheme.
 *
 * This allows specifying two different toolbar colours for light and dark schemes.
 * It can be useful if [ColorScheme.System] is set: Custom Tabs
 * will follow the system settings and apply the corresponding
 * [CustomTabColorSchemeParams] "on the fly" when the settings change.
 *
 * If there is no [CustomTabColorSchemeParams] for the current scheme, or a particular
 * field of it is null, Custom Tabs will fall back to the defaults provided via
 * [setDefaultColorSchemeParams].
 * ---
 * This method allows for the [ColorScheme] enum to be used, as well as DSL syntax to
 * configure the [CustomTabColorSchemeParams].
 * @param scheme A constant representing a color scheme (see [setColorScheme]).
 * It should not be [ColorScheme.System], because that represents a behavior rather
 * than a particular color scheme.
 * @param paramsInit Block to be used to configure a [CustomTabColorSchemeParams].
 * @since 0.0.1
 * @see CustomTabsIntent.Builder.setColorSchemeParams
 * @see colorSchemeParams
 */
public fun CustomTabsIntent.Builder.setColorSchemeParams(
    scheme: ColorScheme,
    paramsInit: CustomTabColorSchemeParams.Builder.() -> Unit
): CustomTabsIntent.Builder = setColorSchemeParams(
    scheme.value, colorSchemeParams(paramsInit)
)

/**
 * Sets [CustomTabColorSchemeParams] for the given colour scheme.
 *
 * This allows specifying two different toolbar colours for light and dark schemes.
 * It can be useful if [ColorScheme.System] is set: Custom Tabs
 * will follow the system settings and apply the corresponding
 * [CustomTabColorSchemeParams] "on the fly" when the settings change.
 *
 * If there is no [CustomTabColorSchemeParams] for the current scheme, or a particular
 * field of it is null, Custom Tabs will fall back to the defaults provided via
 * [setDefaultColorSchemeParams].
 * ---
 * This method allows for the [ColorScheme] enum to be used, as well as named arguments to
 * configure the [CustomTabColorSchemeParams].
 * @param scheme A constant representing a color scheme (see [setColorScheme]).
 * It should not be [ColorScheme.System], because that represents a behavior rather
 * than a particular color scheme.
 * @param toolbarColor See [CustomTabColorSchemeParams.toolbarColor].
 * @param secondaryToolbarColor See [CustomTabColorSchemeParams.secondaryToolbarColor].
 * @param navigationBarColor See [CustomTabColorSchemeParams.navigationBarColor].
 * @param navigationBarDividerColor See [CustomTabColorSchemeParams.navigationBarDividerColor].
 * @since 0.0.1
 * @see CustomTabsIntent.Builder.setColorSchemeParams
 * @see colorSchemeParams
 */
public fun CustomTabsIntent.Builder.setColorSchemeParams(
    scheme: ColorScheme,
    @ColorInt toolbarColor: Int? = null,
    @ColorInt secondaryToolbarColor: Int? = null,
    @ColorInt navigationBarColor: Int? = null,
    @ColorInt navigationBarDividerColor: Int? = null
): CustomTabsIntent.Builder = setColorSchemeParams(
    scheme.value,
    colorSchemeParams(
        toolbarColor,
        secondaryToolbarColor,
        navigationBarColor,
        navigationBarDividerColor
    )
)

/**
 * Sets the Custom Tab Activity's initial height in pixels and the desired
 * resize behavior. The Custom Tab will behave as a bottom sheet.
 * ---
 * This method allows for an [ActivityHeightResizeBehavior] enum to be specified
 * for [activityHeightResizeBehavior].
 * @param initialHeightPx The Custom Tab Activity's initial height in pixels.
 * @param activityHeightResizeBehavior Desired height behavior.
 * @since 0.0.1
 * @see CustomTabsIntent.Builder.setInitialActivityHeightPx
 */
public fun CustomTabsIntent.Builder.setInitialActivityHeightPx(
    @Dimension(unit = Dimension.PX) initialHeightPx: Int,
    activityHeightResizeBehavior: ActivityHeightResizeBehavior
): CustomTabsIntent.Builder =
    setInitialActivityHeightPx(initialHeightPx, activityHeightResizeBehavior.value)

/**
 * Sets the position of the close button.
 * This method allows for an enum [CloseButtonPosition] to be used.
 * @param position The desired position.
 * @since 0.0.1
 * @see CustomTabsIntent.Builder.setCloseButtonPosition
 */
public fun CustomTabsIntent.Builder.setCloseButtonPosition(
    position: CloseButtonPosition
): CustomTabsIntent.Builder = setCloseButtonPosition(position.value)

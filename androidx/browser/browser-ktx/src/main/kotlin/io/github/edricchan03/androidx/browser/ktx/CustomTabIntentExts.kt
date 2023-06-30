package io.github.edricchan03.androidx.browser.ktx

import android.content.Intent
import androidx.annotation.Dimension
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsSession
import io.github.edricchan03.androidx.browser.ktx.annotations.ExperimentalBrowserApi
import io.github.edricchan03.androidx.browser.ktx.enums.ActivityHeightResizeBehavior
import io.github.edricchan03.androidx.browser.ktx.enums.CloseButtonPosition
import io.github.edricchan03.androidx.browser.ktx.enums.ColorScheme

/**
 * Creates a [CustomTabsIntent.Builder].
 * Optionally, an existing [session] may be specified.
 * @since 0.0.1
 */
public fun customTabsIntentBuilder(
    session: CustomTabsSession? = null
): CustomTabsIntent.Builder = CustomTabsIntent.Builder(session)

/**
 * Creates a [Custom Tab Intent][CustomTabsIntent] using DSL syntax.
 * Optionally, an existing [session] may be specified.
 * @since 0.0.1
 * @see CustomTabsIntent.Builder
 */
public inline fun customTabsIntent(
    session: CustomTabsSession? = null,
    init: CustomTabsIntent.Builder.() -> Unit
): CustomTabsIntent = customTabsIntentBuilder(session).apply(init).build()

// Intent extension functions

/**
 * Adds the necessary flags and extras to signal any browser supporting custom
 * tabs to use the browser UI at all times and avoid showing custom tab like UI.
 *
 * Calling this with an intent will override any custom tabs related customizations.
 * @since 0.0.1
 * @see CustomTabsIntent.setAlwaysUseBrowserUI
 */
public fun Intent.setAlwaysUseBrowserUI(): Intent =
    CustomTabsIntent.setAlwaysUseBrowserUI(this)

// Copied from CustomTabsIntent as the field is private
private const val EXTRA_USER_OPT_OUT_FROM_CUSTOM_TABS =
    "android.support.customtabs.extra.user_opt_out"

/**
 * Whether a browser receiving the given intent should always use browser UI
 * and avoid using any custom tabs UI.
 *
 * **Note:** The setter is a custom implementation and thus currently requires
 * an experimental warning until further notice.
 * @since 0.0.1
 * @see CustomTabsIntent.shouldAlwaysUseBrowserUI
 * @see CustomTabsIntent.setAlwaysUseBrowserUI
 */
@set:ExperimentalBrowserApi
public var Intent.shouldAlwaysUseBrowserUI: Boolean
    get() = CustomTabsIntent.shouldAlwaysUseBrowserUI(this)
    set(value) {
        // Implementation from CustomTabsIntent#setAlwaysUseBrowserUI
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        putExtra(EXTRA_USER_OPT_OUT_FROM_CUSTOM_TABS, value)
    }

/**
 * Retrieves the instance of [androidx.browser.customtabs.CustomTabColorSchemeParams]
 * from an [Intent] for a given colour scheme. Uses values passed directly into
 * [CustomTabsIntent.Builder] (e.g. via [CustomTabsIntent.Builder.setToolbarColor]) as
 * defaults.
 * @param colorScheme A colour scheme. Should not be [ColorScheme.System].
 * @since 0.0.1
 * @see CustomTabsIntent.getColorSchemeParams
 */
public fun Intent.getColorSchemeParams(colorScheme: ColorScheme): CustomTabColorSchemeParams =
    CustomTabsIntent.getColorSchemeParams(this, colorScheme.value)

/**
 * Gets the Custom Tab Activity's resize behavior.
 * @since 0.0.1
 * @see CustomTabsIntent.getActivityResizeBehavior
 */
public val Intent.activityResizeBehavior: ActivityHeightResizeBehavior
    get() = ActivityHeightResizeBehavior.fromValue(
        CustomTabsIntent.getActivityResizeBehavior(this)
    )

/**
 * Gets the Custom Tab Activity's initial height, or `0` if it is not set.
 * @since 0.0.1
 * @see CustomTabsIntent.getInitialActivityHeightPx
 */
@get:Dimension(unit = Dimension.PX)
public val Intent.initialActivityHeightPx: Int
    get() = CustomTabsIntent.getInitialActivityHeightPx(this)

/**
 * Gets the toolbar's top corner radii in dp.
 * @since 0.0.1
 * @see CustomTabsIntent.getToolbarCornerRadiusDp
 */
@get:Dimension(unit = Dimension.DP)
public val Intent.toolbarCornerRadiusDp: Int
    get() = CustomTabsIntent.getToolbarCornerRadiusDp(this)

/**
 * Gets the position of the close button as a [CloseButtonPosition],
 * or the [default position][CloseButtonPosition.Default] if the extra is not set.
 * @since 0.0.1
 * @see CustomTabsIntent.getCloseButtonPosition
 */
public val Intent.closeButtonPosition: CloseButtonPosition
    get() = CloseButtonPosition.fromValue(
        CustomTabsIntent.getCloseButtonPosition(this)
    )

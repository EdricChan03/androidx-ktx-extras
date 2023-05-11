package io.github.edricchan03.androidx.browser.ktx

import android.content.Intent
import androidx.annotation.Dimension
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsSession
import io.github.edricchan03.androidx.browser.ktx.enums.ActivityHeightResizeBehavior
import io.github.edricchan03.androidx.browser.ktx.enums.CloseButtonPosition
import io.github.edricchan03.androidx.browser.ktx.enums.ColorScheme

/**
 * Creates a [CustomTabsIntent.Builder].
 * Optionally, an existing [session] may be specified.
 */
public fun customTabsIntentBuilder(
    session: CustomTabsSession? = null
): CustomTabsIntent.Builder = CustomTabsIntent.Builder(session)

/**
 * Creates a [Custom Tab Intent][CustomTabsIntent] using DSL syntax.
 * Optionally, an existing [session] may be specified.
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
 */
public fun Intent.setAlwaysUseBrowserUI(): Intent =
    CustomTabsIntent.setAlwaysUseBrowserUI(this)

/**
 * Whether a browser receiving the given intent should always use browser UI
 * and avoid using any custom tabs UI.
 */
public val Intent.shouldAlwaysUseBrowserUI: Boolean
    get() = CustomTabsIntent.shouldAlwaysUseBrowserUI(this)

/**
 * Retrieves the instance of [androidx.browser.customtabs.CustomTabColorSchemeParams]
 * from an [Intent] for a given color scheme. Uses values passed directly into [CustomTabsIntent.Builder]
 * (e.g. via [CustomTabsIntent.Builder.setToolbarColor]) as defaults.
 */
public fun Intent.getColorSchemeParams(colorScheme: ColorScheme): CustomTabColorSchemeParams =
    CustomTabsIntent.getColorSchemeParams(this, colorScheme.value)

/** Gets the Custom Tab Activity's resize behavior. */
public val Intent.activityResizeBehavior: ActivityHeightResizeBehavior
    get() = ActivityHeightResizeBehavior.fromValueOrElse(
        CustomTabsIntent.getActivityResizeBehavior(this),
        ActivityHeightResizeBehavior.Default
    )

/**
 * Gets the Custom Tab Activity's initial height, or `0` if it is not set.
 * @see CustomTabsIntent.EXTRA_INITIAL_ACTIVITY_HEIGHT_PX
 */
@get:Dimension(unit = Dimension.PX)
public val Intent.initialActivityHeightPx: Int
    get() = CustomTabsIntent.getInitialActivityHeightPx(this)

/**
 * Gets the toolbar's top corner radii in dp.
 * @see CustomTabsIntent.EXTRA_TOOLBAR_CORNER_RADIUS_DP
 */
@get:Dimension(unit = Dimension.DP)
public val Intent.toolbarCornerRadiusDp: Int
    get() = CustomTabsIntent.getToolbarCornerRadiusDp(this)

/**
 * Gets the position of the close button as a [CloseButtonPosition],
 * or the [default position][CloseButtonPosition.Default] if the extra is not set.
 * @see CustomTabsIntent.EXTRA_CLOSE_BUTTON_POSITION
 * @see CustomTabsIntent.CLOSE_BUTTON_POSITION_DEFAULT
 * @see CustomTabsIntent.CLOSE_BUTTON_POSITION_START
 * @see CustomTabsIntent.CLOSE_BUTTON_POSITION_END
 */
public val Intent.closeButtonPosition: CloseButtonPosition
    get() = CloseButtonPosition.fromValueOrElse(
        CustomTabsIntent.getCloseButtonPosition(
            this
        ),
        CloseButtonPosition.Default
    )

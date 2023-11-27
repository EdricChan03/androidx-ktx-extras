package io.github.edricchan03.androidx.browser.ktx

import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.Dimension
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsSession
import io.github.edricchan03.androidx.browser.ktx.annotations.ExperimentalBrowserApi
import io.github.edricchan03.androidx.browser.ktx.enums.ActivityHeightResizeBehavior
import io.github.edricchan03.androidx.browser.ktx.enums.CloseButtonPosition
import io.github.edricchan03.androidx.browser.ktx.enums.ColorScheme
import io.github.edricchan03.androidx.browser.ktx.enums.ShareState
import java.util.Locale

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
 * Specifies which color scheme should be applied to the custom tab.
 * @since 0.1.0
 * @see CustomTabsIntent.EXTRA_COLOR_SCHEME
 * @see CustomTabsIntent.Builder.setColorScheme
 */
@ExperimentalBrowserApi
public var Intent.colorScheme: ColorScheme
    get() = ColorScheme.fromValue(
        getIntExtra(
            CustomTabsIntent.EXTRA_COLOR_SCHEME,
            CustomTabsIntent.COLOR_SCHEME_SYSTEM
        )
    )
    set(scheme) {
        putExtra(CustomTabsIntent.EXTRA_COLOR_SCHEME, scheme.value)
    }

/**
 * Specifies whether the URL bar should be hidden as the user scrolls down the page.
 * @since 0.1.0
 * @see CustomTabsIntent.Builder.setUrlBarHidingEnabled
 * @see CustomTabsIntent.EXTRA_ENABLE_URLBAR_HIDING
 */
@ExperimentalBrowserApi
public var Intent.urlBarHidingEnabled: Boolean
    get() = getBooleanExtra(CustomTabsIntent.EXTRA_ENABLE_URLBAR_HIDING, false)
    set(enabled) {
        putExtra(CustomTabsIntent.EXTRA_ENABLE_URLBAR_HIDING, enabled)
    }

/**
 * Specifies the icon bitmap of the back button on the toolbar.
 *
 * If the client chooses not to customize it, a default close button will be used.
 *
 * Setter implementation: If `null` is specified, the extra is **removed**, and no further
 * actions are taken.
 * @since 0.1.0
 * @see CustomTabsIntent.EXTRA_CLOSE_BUTTON_ICON
 * @see CustomTabsIntent.Builder.setCloseButtonIcon
 */
@ExperimentalBrowserApi
public var Intent.closeButtonIcon: Bitmap?
    get() = getParcelableExtraCompat<Bitmap>(
        CustomTabsIntent.EXTRA_CLOSE_BUTTON_ICON
    )
    set(value) {
        if (value == null) removeExtra(CustomTabsIntent.EXTRA_CLOSE_BUTTON_ICON)
        else putExtra(CustomTabsIntent.EXTRA_CLOSE_BUTTON_ICON, value)
    }

/**
 * Whether the title should be shown in the custom tab.
 * @since 0.1.0
 * @see CustomTabsIntent.EXTRA_TITLE_VISIBILITY_STATE
 * @see CustomTabsIntent.SHOW_PAGE_TITLE
 * @see CustomTabsIntent.NO_TITLE
 * @see CustomTabsIntent.Builder.setShowTitle
 */
@ExperimentalBrowserApi
public var Intent.showTitle: Boolean
    get() = when (
        getIntExtra(
            CustomTabsIntent.EXTRA_TITLE_VISIBILITY_STATE, CustomTabsIntent.SHOW_PAGE_TITLE
        )
    ) {
        CustomTabsIntent.SHOW_PAGE_TITLE -> true
        else -> false
    }
    set(value) {
        putExtra(
            CustomTabsIntent.EXTRA_TITLE_VISIBILITY_STATE,
            if (value) CustomTabsIntent.SHOW_PAGE_TITLE else CustomTabsIntent.NO_TITLE
        )
    }

/**
 * The Custom Tab Activity's resize behavior.
 * @since - Getter: 0.0.1
 * - Setter: 0.1.0
 * @see CustomTabsIntent.getActivityResizeBehavior
 * @see CustomTabsIntent.EXTRA_ACTIVITY_HEIGHT_RESIZE_BEHAVIOR
 */
@set:ExperimentalBrowserApi
public var Intent.activityResizeBehavior: ActivityHeightResizeBehavior
    get() = ActivityHeightResizeBehavior.fromValue(
        CustomTabsIntent.getActivityResizeBehavior(this)
    )
    set(behavior) {
        putExtra(CustomTabsIntent.EXTRA_ACTIVITY_HEIGHT_RESIZE_BEHAVIOR, behavior.value)
    }

/**
 * The Custom Tab Activity's initial height, or `0` if it is not set.
 * @since - Getter: 0.0.1
 * - Setter: 0.1.0
 * @see CustomTabsIntent.getInitialActivityHeightPx
 * @see CustomTabsIntent.EXTRA_INITIAL_ACTIVITY_HEIGHT_PX
 */
@get:Dimension(unit = Dimension.PX)
@setparam:Dimension(unit = Dimension.PX)
@set:ExperimentalBrowserApi
public var Intent.initialActivityHeightPx: Int
    get() = CustomTabsIntent.getInitialActivityHeightPx(this)
    set(value) {
        putExtra(CustomTabsIntent.EXTRA_INITIAL_ACTIVITY_HEIGHT_PX, value)
    }

/**
 * The toolbar's top corner radii in dp.
 * @since - Getter: 0.0.1
 * - Setter: 0.1.0
 * @see CustomTabsIntent.getToolbarCornerRadiusDp
 * @see CustomTabsIntent.EXTRA_TOOLBAR_CORNER_RADIUS_DP
 */
@get:Dimension(unit = Dimension.DP)
@setparam:Dimension(unit = Dimension.DP)
@set:ExperimentalBrowserApi
public var Intent.toolbarCornerRadiusDp: Int
    get() = CustomTabsIntent.getToolbarCornerRadiusDp(this)
    set(value) {
        putExtra(CustomTabsIntent.EXTRA_TOOLBAR_CORNER_RADIUS_DP, value)
    }

/**
 * The position of the close button as a [CloseButtonPosition],
 * or the [default position][CloseButtonPosition.Default] if the extra is not set.
 * @since - Getter: 0.0.1
 * - Setter: 0.1.0
 * @see CustomTabsIntent.getCloseButtonPosition
 * @see CustomTabsIntent.EXTRA_CLOSE_BUTTON_POSITION
 */
@set:ExperimentalBrowserApi
public var Intent.closeButtonPosition: CloseButtonPosition
    get() = CloseButtonPosition.fromValue(
        CustomTabsIntent.getCloseButtonPosition(this)
    )
    set(position) {
        putExtra(CustomTabsIntent.EXTRA_CLOSE_BUTTON_POSITION, position.value)
    }

/**
 * The [ShareState] of this intent, or the [default share state][ShareState.Default]
 * if the extra is not set.
 * @since 0.1.0
 * @see CustomTabsIntent.EXTRA_SHARE_STATE
 * @see CustomTabsIntent.Builder.setShareState
 */
@ExperimentalBrowserApi
public var Intent.shareState: ShareState
    get() = ShareState.fromValue(
        getIntExtra(
            CustomTabsIntent.EXTRA_SHARE_STATE,
            ShareState.Default.value
        )
    )
    set(state) {
        putExtra(CustomTabsIntent.EXTRA_SHARE_STATE, state.value)
    }

/**
 * Whether Instant Apps is enabled for this intent.
 * @since 0.1.0
 * @see CustomTabsIntent.EXTRA_ENABLE_INSTANT_APPS
 * @see CustomTabsIntent.Builder.setInstantAppsEnabled
 */
@ExperimentalBrowserApi
public var Intent.instantAppsEnabled: Boolean
    get() = getBooleanExtra(CustomTabsIntent.EXTRA_ENABLE_INSTANT_APPS, true)
    set(value) {
        putExtra(CustomTabsIntent.EXTRA_ENABLE_INSTANT_APPS, value)
    }

//#region Browser 1.7.0 APIs
/**
 * Whether the bookmarks button is enabled. This value is set to `true` by default.
 * @receiver The [Intent] to check.
 * @since 0.2.0
 * @see CustomTabsIntent.EXTRA_DISABLE_BOOKMARKS_BUTTON
 * @see CustomTabsIntent.isBookmarksButtonEnabled
 */
@set:ExperimentalBrowserApi
public var Intent.isBookmarksButtonEnabled: Boolean
    get() = CustomTabsIntent.isBookmarksButtonEnabled(this)
    set(value) {
        putExtra(CustomTabsIntent.EXTRA_DISABLE_BOOKMARKS_BUTTON, !value)
    }

/**
 * Whether the download button is enabled. This value is set to `true` by default.
 * @receiver The [Intent] to check.
 * @since 0.2.0
 * @see CustomTabsIntent.isDownloadButtonEnabled
 * @see CustomTabsIntent.EXTRA_DISABLE_DOWNLOAD_BUTTON
 */
@set:ExperimentalBrowserApi
public var Intent.isDownloadButtonEnabled: Boolean
    get() = CustomTabsIntent.isDownloadButtonEnabled(this)
    set(value) {
        putExtra(CustomTabsIntent.EXTRA_DISABLE_DOWNLOAD_BUTTON, !value)
    }

/**
 * Whether initial URLs are to be sent to external handler apps.
 * @receiver The [Intent] to check.
 * @since 0.2.0
 * @see CustomTabsIntent.isSendToExternalDefaultHandlerEnabled
 * @see CustomTabsIntent.EXTRA_SEND_TO_EXTERNAL_DEFAULT_HANDLER
 */
@set:ExperimentalBrowserApi
public var Intent.isSendToExternalDefaultHandlerEnabled: Boolean
    get() = CustomTabsIntent.isSendToExternalDefaultHandlerEnabled(this)
    set(value) {
        putExtra(CustomTabsIntent.EXTRA_SEND_TO_EXTERNAL_DEFAULT_HANDLER, value)
    }

/**
 * Gets the target locale for the Translate UI.
 *
 * **Setter note:** If `null` is used as the value when setting the extra, the extra
 * will be **cleared** (i.e. [Intent.removeExtra] will be called).
 * @receiver The [Intent] to check.
 * @since 0.2.0
 * @see CustomTabsIntent.getTranslateLocale
 * @see CustomTabsIntent.EXTRA_TRANSLATE_LANGUAGE_TAG
 * @see Locale.toLanguageTag
 */
@set:ExperimentalBrowserApi
public var Intent.translateLocale: Locale?
    get() = CustomTabsIntent.getTranslateLocale(this)
    set(value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && value != null) {
            putExtra(CustomTabsIntent.EXTRA_TRANSLATE_LANGUAGE_TAG, value.toLanguageTag())
        } else {
            removeExtra(CustomTabsIntent.EXTRA_TRANSLATE_LANGUAGE_TAG)
        }
    }

/**
 * Whether the background interaction is enabled.
 * @receiver The [Intent] to check/set the extra on.
 * @since 0.2.0
 * @see CustomTabsIntent.isBackgroundInteractionEnabled
 * @see CustomTabsIntent.EXTRA_DISABLE_BACKGROUND_INTERACTION
 */
@set:ExperimentalBrowserApi
public var Intent.isBackgroundInteractionEnabled: Boolean
    get() = CustomTabsIntent.isBackgroundInteractionEnabled(this)
    set(value) {
        putExtra(CustomTabsIntent.EXTRA_DISABLE_BACKGROUND_INTERACTION, !value)
    }

/**
 * The [PendingIntent] that will be sent when the user swipes up from the secondary toolbar.
 * @receiver The [Intent] to check/set the extra on.
 * @since 0.2.0
 * @see CustomTabsIntent.getSecondaryToolbarSwipeUpGesture
 * @see CustomTabsIntent.EXTRA_SECONDARY_TOOLBAR_SWIPE_UP_GESTURE
 */
@set:ExperimentalBrowserApi
public var Intent.secondaryToolbarSwipeUpGesture: PendingIntent?
    get() = CustomTabsIntent.getSecondaryToolbarSwipeUpGesture(this)
    set(value) {
        putExtra(CustomTabsIntent.EXTRA_SECONDARY_TOOLBAR_SWIPE_UP_GESTURE, value)
    }
//#endregion

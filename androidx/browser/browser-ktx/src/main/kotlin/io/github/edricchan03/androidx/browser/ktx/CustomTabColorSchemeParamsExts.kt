package io.github.edricchan03.androidx.browser.ktx

import androidx.annotation.ColorInt
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent

/** Creates a [CustomTabColorSchemeParams] using DSL syntax. */
public inline fun colorSchemeParams(
    init: CustomTabColorSchemeParams.Builder.() -> Unit
): CustomTabColorSchemeParams = CustomTabColorSchemeParams.Builder().apply(init).build()

/**
 * Creates a [CustomTabColorSchemeParams] using the given colour integers.
 * @param toolbarColor See [CustomTabColorSchemeParams.toolbarColor].
 * @param secondaryToolbarColor See [CustomTabColorSchemeParams.secondaryToolbarColor].
 * @param navigationBarColor See [CustomTabColorSchemeParams.navigationBarColor].
 * @param navigationBarDividerColor See [CustomTabColorSchemeParams.navigationBarDividerColor].
 */
public fun colorSchemeParams(
    @ColorInt toolbarColor: Int? = null,
    @ColorInt secondaryToolbarColor: Int? = null,
    @ColorInt navigationBarColor: Int? = null,
    @ColorInt navigationBarDividerColor: Int? = null
): CustomTabColorSchemeParams = colorSchemeParams {
    toolbarColor?.let { setToolbarColor(it) }
    secondaryToolbarColor?.let { setSecondaryToolbarColor(it) }
    navigationBarColor?.let { setNavigationBarColor(it) }
    navigationBarDividerColor?.let { setNavigationBarDividerColor(it) }
}

/**
 * Sets the default colour scheme to use using DSL syntax.
 */
public inline fun CustomTabsIntent.Builder.setDefaultColorSchemeParams(
    init: CustomTabColorSchemeParams.Builder.() -> Unit
): CustomTabsIntent.Builder = setDefaultColorSchemeParams(colorSchemeParams(init))

/**
 * Sets the default colour scheme to use.
 * @param toolbarColor See [CustomTabColorSchemeParams.toolbarColor].
 * @param secondaryToolbarColor See [CustomTabColorSchemeParams.secondaryToolbarColor].
 * @param navigationBarColor See [CustomTabColorSchemeParams.navigationBarColor].
 * @param navigationBarDividerColor See [CustomTabColorSchemeParams.navigationBarDividerColor].
 */
public fun CustomTabsIntent.Builder.setDefaultColorSchemeParams(
    @ColorInt toolbarColor: Int? = null,
    @ColorInt secondaryToolbarColor: Int? = null,
    @ColorInt navigationBarColor: Int? = null,
    @ColorInt navigationBarDividerColor: Int? = null
): CustomTabsIntent.Builder = setDefaultColorSchemeParams(
    colorSchemeParams(
        toolbarColor,
        secondaryToolbarColor,
        navigationBarColor,
        navigationBarDividerColor
    )
)

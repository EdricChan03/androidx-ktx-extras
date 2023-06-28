package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent
import io.github.edricchan03.androidx.browser.ktx.enums.utils.intEnumTests
import io.kotest.core.spec.style.DescribeSpec

private val schemeIntsMap = mapOf(
    CustomTabsIntent::COLOR_SCHEME_SYSTEM to ColorScheme.System,
    CustomTabsIntent::COLOR_SCHEME_LIGHT to ColorScheme.Light,
    CustomTabsIntent::COLOR_SCHEME_DARK to ColorScheme.Dark
)

class ColorSchemeTest : DescribeSpec({
    include(
        intEnumTests(
            fromValueOrNullFn = ColorScheme::fromValueOrNull,
            enumValuesMap = schemeIntsMap
        )
    )
})

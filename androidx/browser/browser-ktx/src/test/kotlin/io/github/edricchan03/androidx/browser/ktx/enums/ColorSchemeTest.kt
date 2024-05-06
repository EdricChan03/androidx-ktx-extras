package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent
import io.github.edricchan03.androidx.common.enums.fixtures.ExperimentalEnumKotestApi
import io.github.edricchan03.androidx.common.enums.fixtures.intEnumTests
import io.kotest.core.spec.style.DescribeSpec

private val schemeIntsMap = mapOf(
    CustomTabsIntent::COLOR_SCHEME_SYSTEM to ColorScheme.System,
    CustomTabsIntent::COLOR_SCHEME_LIGHT to ColorScheme.Light,
    CustomTabsIntent::COLOR_SCHEME_DARK to ColorScheme.Dark
)

@OptIn(ExperimentalEnumKotestApi::class)
class ColorSchemeTest : DescribeSpec({
    include(
        intEnumTests(
            fromValueOrNullFn = ColorScheme::fromValueOrNull,
            enumValuesMap = schemeIntsMap
        )
    )
})

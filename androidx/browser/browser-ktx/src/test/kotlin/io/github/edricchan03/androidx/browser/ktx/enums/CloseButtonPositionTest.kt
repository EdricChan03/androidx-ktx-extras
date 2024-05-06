package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent
import io.github.edricchan03.androidx.common.enums.fixtures.ExperimentalEnumKotestApi
import io.github.edricchan03.androidx.common.enums.fixtures.intEnumTests
import io.kotest.core.spec.style.DescribeSpec

private val posIntsMap = mapOf(
    CustomTabsIntent::CLOSE_BUTTON_POSITION_DEFAULT to CloseButtonPosition.Default,
    CustomTabsIntent::CLOSE_BUTTON_POSITION_START to CloseButtonPosition.Start,
    CustomTabsIntent::CLOSE_BUTTON_POSITION_END to CloseButtonPosition.End
)

@OptIn(ExperimentalEnumKotestApi::class)
class CloseButtonPositionTest : DescribeSpec({
    include(
        intEnumTests(
            fromValueOrNullFn = CloseButtonPosition::fromValueOrNull,
            enumValuesMap = posIntsMap
        )
    )
})

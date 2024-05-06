package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent
import io.github.edricchan03.androidx.common.enums.fixtures.ExperimentalEnumKotestApi
import io.github.edricchan03.androidx.common.enums.fixtures.intEnumTests
import io.kotest.core.spec.style.DescribeSpec

private val positionIntsMap = mapOf(
    CustomTabsIntent::ACTIVITY_SIDE_SHEET_POSITION_DEFAULT to ActivitySideSheetPosition.Default,
    CustomTabsIntent::ACTIVITY_SIDE_SHEET_POSITION_START to ActivitySideSheetPosition.Start,
    CustomTabsIntent::ACTIVITY_SIDE_SHEET_POSITION_END to ActivitySideSheetPosition.End
)

@OptIn(ExperimentalEnumKotestApi::class)
class ActivitySideSheetPositionTest : DescribeSpec({
    include(
        intEnumTests(
            fromValueOrNullFn = ActivitySideSheetPosition.Companion::fromValueOrNull,
            enumValuesMap = positionIntsMap
        )
    )
})

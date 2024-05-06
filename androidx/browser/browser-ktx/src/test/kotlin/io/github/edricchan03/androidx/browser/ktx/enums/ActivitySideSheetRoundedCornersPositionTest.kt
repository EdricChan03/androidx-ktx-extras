package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent
import io.github.edricchan03.androidx.common.enums.fixtures.ExperimentalEnumKotestApi
import io.github.edricchan03.androidx.common.enums.fixtures.intEnumTests
import io.kotest.core.spec.style.DescribeSpec

private val positionIntsMap = mapOf(
    CustomTabsIntent::ACTIVITY_SIDE_SHEET_ROUNDED_CORNERS_POSITION_DEFAULT to ActivitySideSheetRoundedCornersPosition.Default,
    CustomTabsIntent::ACTIVITY_SIDE_SHEET_ROUNDED_CORNERS_POSITION_NONE to ActivitySideSheetRoundedCornersPosition.None,
    CustomTabsIntent::ACTIVITY_SIDE_SHEET_ROUNDED_CORNERS_POSITION_TOP to ActivitySideSheetRoundedCornersPosition.Top
)

@OptIn(ExperimentalEnumKotestApi::class)
class ActivitySideSheetRoundedCornersPositionTest : DescribeSpec({
    include(
        intEnumTests(
            fromValueOrNullFn = ActivitySideSheetRoundedCornersPosition.Companion::fromValueOrNull,
            enumValuesMap = positionIntsMap
        )
    )
})

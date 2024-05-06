package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent
import io.github.edricchan03.androidx.common.enums.fixtures.ExperimentalEnumKotestApi
import io.github.edricchan03.androidx.common.enums.fixtures.intEnumTests
import io.kotest.core.spec.style.DescribeSpec

private val decorationTypeIntsMap = mapOf(
    CustomTabsIntent::ACTIVITY_SIDE_SHEET_DECORATION_TYPE_DEFAULT to ActivitySideSheetDecorationType.Default,
    CustomTabsIntent::ACTIVITY_SIDE_SHEET_DECORATION_TYPE_NONE to ActivitySideSheetDecorationType.None,
    CustomTabsIntent::ACTIVITY_SIDE_SHEET_DECORATION_TYPE_SHADOW to ActivitySideSheetDecorationType.Shadow,
    CustomTabsIntent::ACTIVITY_SIDE_SHEET_DECORATION_TYPE_DIVIDER to ActivitySideSheetDecorationType.Divider
)

@OptIn(ExperimentalEnumKotestApi::class)
class ActivitySideSheetDecorationTypeTest : DescribeSpec({
    include(
        intEnumTests(
            fromValueOrNullFn = ActivitySideSheetDecorationType.Companion::fromValueOrNull,
            enumValuesMap = decorationTypeIntsMap
        )
    )
})

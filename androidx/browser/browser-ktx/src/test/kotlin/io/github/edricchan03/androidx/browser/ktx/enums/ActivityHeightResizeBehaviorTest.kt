package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent
import io.github.edricchan03.androidx.common.enums.fixtures.ExperimentalEnumKotestApi
import io.github.edricchan03.androidx.common.enums.fixtures.intEnumTests
import io.kotest.core.spec.style.DescribeSpec

private val behaviorIntsMap = mapOf(
    CustomTabsIntent::ACTIVITY_HEIGHT_DEFAULT to ActivityHeightResizeBehavior.Default,
    CustomTabsIntent::ACTIVITY_HEIGHT_ADJUSTABLE to ActivityHeightResizeBehavior.Adjustable,
    CustomTabsIntent::ACTIVITY_HEIGHT_FIXED to ActivityHeightResizeBehavior.Fixed
)

@OptIn(ExperimentalEnumKotestApi::class)
class ActivityHeightResizeBehaviorTest : DescribeSpec({
    include(
        intEnumTests(
            fromValueOrNullFn = ActivityHeightResizeBehavior::fromValueOrNull,
            enumValuesMap = behaviorIntsMap
        )
    )
})

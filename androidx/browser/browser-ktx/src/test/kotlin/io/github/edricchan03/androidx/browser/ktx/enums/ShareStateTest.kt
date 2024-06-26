package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent
import io.github.edricchan03.androidx.common.enums.fixtures.ExperimentalEnumKotestApi
import io.github.edricchan03.androidx.common.enums.fixtures.intEnumTests
import io.kotest.core.spec.style.DescribeSpec

private val stateIntsMap = mapOf(
    CustomTabsIntent::SHARE_STATE_DEFAULT to ShareState.Default,
    CustomTabsIntent::SHARE_STATE_ON to ShareState.On,
    CustomTabsIntent::SHARE_STATE_OFF to ShareState.Off
)

@OptIn(ExperimentalEnumKotestApi::class)
class ShareStateTest : DescribeSpec({
    include(
        intEnumTests(
            fromValueOrNullFn = ShareState::fromValueOrNull,
            enumValuesMap = stateIntsMap
        )
    )
})

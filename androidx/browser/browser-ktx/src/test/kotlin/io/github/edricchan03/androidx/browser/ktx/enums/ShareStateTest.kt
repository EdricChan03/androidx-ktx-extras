package io.github.edricchan03.androidx.browser.ktx.enums

import androidx.browser.customtabs.CustomTabsIntent
import io.github.edricchan03.androidx.browser.ktx.enums.utils.intEnumTests
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.property.Arb
import io.kotest.property.Exhaustive
import io.kotest.property.arbitrary.filterNot
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.collection

private val stateIntsMap = mapOf(
    CustomTabsIntent::SHARE_STATE_DEFAULT to ShareState.Default,
    CustomTabsIntent::SHARE_STATE_ON to ShareState.On,
    CustomTabsIntent::SHARE_STATE_OFF to ShareState.Off
)

class ShareStateTest : DescribeSpec({
    include(intEnumTests(
        fromValueOrNullFn = ShareState::fromValueOrNull,
        enumValuesMap = stateIntsMap
    ))
})

package io.github.edricchan03.androidx.browser.ktx.enums.utils

import io.kotest.core.spec.style.describeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.property.Arb
import io.kotest.property.arbitrary.filterNot
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll
import kotlin.reflect.KProperty0

/**
 * Test factory to create tests for the given [enum][E].
 * @param InternalValue The internal value representation.
 * @param E The enum.
 * @param describeName Descriptive name to be passed to the top-level `describe()`
 * function.
 * @param dataNameFn Function to be used for [withData]'s `nameFn`.
 * @param enumValuesMap The map of internal values to their enum representation.
 * @param fromValueOrNullFn Function used to convert the internal value to its enum
 * representation.
 * @param invalidArb Arbitrary used to output invalid internal values. This is used
 * to test that the invalid values from this arbitrary returns `null` from
 * [fromValueOrNullFn].
 */
fun <InternalValue, E : Enum<E>> enumTests(
    describeName: String = "fromValueOrNull",
    dataNameFn: (Map.Entry<EnumMapEntry<InternalValue>, E>) -> String =
        { "${it.key.propertyName}: ${it.value}" },
    enumValuesMap: Map<EnumMapEntry<InternalValue>, E>,
    fromValueOrNullFn: (InternalValue) -> E?,
    invalidArb: Arb<InternalValue>
) = describeSpec {
    describe(describeName) {
        describe("with valid values") {
            withData(nameFn = dataNameFn, ts = enumValuesMap.entries) {
                val value = fromValueOrNullFn(it.key.value).shouldNotBeNull()
                value shouldBeEqual it.value
            }
        }

        it("should return null for invalid values") {
            checkAll(invalidArb) {
                fromValueOrNullFn(it).shouldBeNull()
            }
        }
    }
}

/**
 * Test factory to create tests for the given [enum][E] that is represented by an
 * [Int] for its internal value.
 *
 * This method is an alias for [enumTests] with the following defaults:
 * - `InternalValue` is represented by an [Int]
 * - [invalidArb] is specified with an `Arb<Int>`
 * @param E The enum.
 * @param describeName Descriptive name to be passed to the top-level `describe()`
 * function.
 * @param dataNameFn Function to be used for [withData]'s `nameFn`.
 * @param enumValuesMap The map of internal values to their enum representation.
 * @param fromValueOrNullFn Function used to convert the internal value to its enum
 * representation.
 * @param invalidArb Arbitrary used to output invalid internal values. This is used
 * to test that the invalid values from this arbitrary returns `null` from
 * [fromValueOrNullFn].
 */
fun <E : Enum<E>> intEnumTests(
    describeName: String = "fromValueOrNull",
    dataNameFn: (Map.Entry<EnumMapEntry<Int>, E>) -> String =
        { "${it.key.propertyName}: ${it.value}" },
    enumValuesMap: Map<EnumMapEntry<Int>, E>,
    fromValueOrNullFn: (Int) -> E?,
    invalidArb: Arb<Int> = Arb.int()
        .filterNot { output -> output in enumValuesMap.map { it.key.value } }
) = enumTests(describeName, dataNameFn, enumValuesMap, fromValueOrNullFn, invalidArb)

/**
 * Test factory to create tests for the given [enum][E] that is represented by an
 * [Int] for its internal value.
 *
 * This method is an alias for [enumTests] with the following defaults:
 * - `InternalValue` is represented by an [Int]
 * - [KProperty0] keys can be passed to [enumValuesMap]
 * - [invalidArb] is specified with an `Arb<Int>`
 * @param E The enum.
 * @param describeName Descriptive name to be passed to the top-level `describe()`
 * function.
 * @param dataNameFn Function to be used for [withData]'s `nameFn`.
 * @param enumValuesMap The map of internal values to their enum representation.
 * @param fromValueOrNullFn Function used to convert the internal value to its enum
 * representation.
 * @param invalidArb Arbitrary used to output invalid internal values. This is used
 * to test that the invalid values from this arbitrary returns `null` from
 * [fromValueOrNullFn].
 */
@JvmName("intEnumTestsReflection")
fun <E : Enum<E>> intEnumTests(
    describeName: String = "fromValueOrNull",
    dataNameFn: (Map.Entry<EnumMapEntry<Int>, E>) -> String =
        { "${it.key.propertyName}: ${it.value}" },
    enumValuesMap: Map<KProperty0<Int>, E>,
    fromValueOrNullFn: (Int) -> E?,
    invalidArb: Arb<Int> = Arb.int()
        .filterNot { output -> output in enumValuesMap.map { it.key.get() } }
) = enumTests(
    describeName,
    dataNameFn,
    enumValuesMap.mapKeys { it.key.toEnumEntry() },
    fromValueOrNullFn,
    invalidArb
)

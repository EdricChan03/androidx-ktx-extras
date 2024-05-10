package io.github.edricchan03.androidx.common.enums

import io.github.edricchan03.androidx.common.enums.impl.ValueEnumFromValue
import io.kotest.assertions.throwables.shouldThrowExactlyUnit
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.property.Arb
import io.kotest.property.Exhaustive
import io.kotest.property.arbitrary.filterNot
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.collection

private enum class ExampleValue(override val value: String) : ValueEnum<String> {
    One("custom"),
    Two("custom-2"),
    Three("custom-3"),
    Custom("abc"),
    Custom2("def");

    @OptIn(ExperimentalEnumsApi::class)
    companion object : ValueEnumFromValue<String, ExampleValue>(Custom2, entries)
}

private val default = ExampleValue.Custom2

private val exampleEntries = ExampleValue.entries.associateBy { it.value }

private val Arb.Companion.nonEntries get() = string().filterNot { it in exampleEntries }

class ValueEnumFromValueTest : DescribeSpec({
    describe("fromValueOrNull") {
        it("should return null respectively") {
            checkAll(Exhaustive.collection(exampleEntries.entries)) { (key, value) ->
                val result = ExampleValue.fromValueOrNull(key).shouldNotBeNull()
                result shouldBeEqual value
            }

            checkAll(Arb.nonEntries) {
                ExampleValue.fromValueOrNull(it).shouldBeNull()
            }
        }
    }

    describe("fromValue") {
        it("should return the default value") {
            checkAll(Exhaustive.collection(exampleEntries.keys)) {
                val value = ExampleValue.fromValue(it)
                value shouldBeEqual ExampleValue.fromValueOrNull(it).shouldNotBeNull()
            }

            checkAll(Arb.nonEntries) {
                val value = ExampleValue.fromValue(it)
                value shouldBeEqual default
            }
        }
    }

    describe("fromValueOrElse") {
        it("should return the default value") {
            checkAll(Exhaustive.collection(exampleEntries.keys)) {
                val value = ExampleValue.fromValueOrElse(it, ExampleValue.Custom)
                value shouldBeEqual ExampleValue.fromValueOrNull(it).shouldNotBeNull()
            }

            checkAll(Arb.nonEntries) {
                val value = ExampleValue.fromValueOrElse(it, ExampleValue.Custom)
                value shouldBeEqual ExampleValue.Custom
            }
        }
    }

    describe("requireValue") {
        it("should throw when the value does not exist") {
            checkAll(Exhaustive.collection(exampleEntries.keys)) {
                val value = ExampleValue.requireValue(it)
                value shouldBeEqual requireNotNull(ExampleValue.fromValueOrNull(it))
            }

            checkAll(Arb.nonEntries) {
                shouldThrowExactlyUnit<IllegalArgumentException> {
                    ExampleValue.requireValue(it)
                }
            }
        }
    }

    describe("hasValue") {
        it("should return whether the value exists") {
            checkAll(Exhaustive.collection(exampleEntries.keys)) {
                ExampleValue.hasValue(it) shouldBeEqual true
            }

            checkAll(Arb.nonEntries) {
                ExampleValue.hasValue(it) shouldBeEqual false
            }
        }
    }
})

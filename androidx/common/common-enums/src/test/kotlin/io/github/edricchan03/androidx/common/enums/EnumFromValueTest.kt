package io.github.edricchan03.androidx.common.enums

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

private enum class Example(val value: String) {
    One("custom"),
    Two("custom-2"),
    Three("custom-3"),
    Custom("abc"),
    Custom2("def");

    companion object : EnumFromValue<String, Example>(Custom2) {
        override fun fromValueOrNull(value: String) = exampleEntries[value]

    }
}

private val default = Example.Custom2

private val exampleEntries = mapOf(
    "custom" to Example.One,
    "custom-2" to Example.Two,
    "custom-3" to Example.Three,
    "testing" to Example.One
)

private val Arb.Companion.nonEntries get() = string().filterNot { it in exampleEntries }

class EnumFromValueTest : DescribeSpec({
    describe("fromValueOrNull") {
        it("should return null respectively") {
            checkAll(Exhaustive.collection(exampleEntries.entries)) { (key, value) ->
                val result = Example.fromValueOrNull(key).shouldNotBeNull()
                result shouldBeEqual value
            }

            checkAll(Arb.nonEntries) {
                Example.fromValueOrNull(it).shouldBeNull()
            }
        }
    }

    describe("fromValue") {
        it("should return the default value") {
            checkAll(Exhaustive.collection(exampleEntries.keys)) {
                val value = Example.fromValue(it)
                value shouldBeEqual Example.fromValueOrNull(it).shouldNotBeNull()
            }

            checkAll(Arb.nonEntries) {
                val value = Example.fromValue(it)
                value shouldBeEqual default
            }
        }
    }

    describe("fromValueOrElse") {
        it("should return the default value") {
            checkAll(Exhaustive.collection(exampleEntries.keys)) {
                val value = Example.fromValueOrElse(it, Example.Custom)
                value shouldBeEqual Example.fromValueOrNull(it).shouldNotBeNull()
            }

            checkAll(Arb.nonEntries) {
                val value = Example.fromValueOrElse(it, Example.Custom)
                value shouldBeEqual Example.Custom
            }
        }
    }

    describe("requireValue") {
        it("should throw when the value does not exist") {
            checkAll(Exhaustive.collection(exampleEntries.keys)) {
                val value = Example.requireValue(it)
                value shouldBeEqual requireNotNull(Example.fromValueOrNull(it))
            }

            checkAll(Arb.nonEntries) {
                shouldThrowExactlyUnit<IllegalArgumentException> {
                    Example.requireValue(it)
                }
            }
        }
    }

    describe("hasValue") {
        it("should return whether the value exists") {
            checkAll(Exhaustive.collection(exampleEntries.keys)) {
                Example.hasValue(it) shouldBeEqual true
            }

            checkAll(Arb.nonEntries) {
                Example.hasValue(it) shouldBeEqual false
            }
        }
    }
})

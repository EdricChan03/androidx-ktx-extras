package io.github.edricchan03.androidx.common.enums

import io.github.edricchan03.androidx.common.enums.fixtures.toEnumEntry
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equals.shouldBeEqual

private const val example = "Hello"

internal class Stub {
    val property = 123
}

@Suppress("UnusedReceiverParameter") // Test the base KProperty extension function
internal val Stub.myProperty get() = "Test"

class EnumMapEntryTest : DescribeSpec({
    describe("toEnumEntry") {
        it("should convert a Kotlin property to an EnumMapEntry") {
            // TODO: Improve the test
            val result = ::example.toEnumEntry()

            result.propertyName shouldBeEqual "example"
            result.value shouldBeEqual example

            val stub = Stub()
            val result2 = stub::property.toEnumEntry()

            result2.propertyName shouldBeEqual "property"
            result2.value shouldBeEqual stub.property
        }

        it("should convert a Kotlin property with receiver to an EnumMapEntry") {
            // TODO: Improve the test
            val stub = Stub()
            val result = stub::myProperty.toEnumEntry()

            result.propertyName shouldBeEqual "myProperty"
            result.value shouldBeEqual stub.myProperty
        }
    }
})

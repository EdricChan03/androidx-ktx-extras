# Kotest Common Enums releases

Provides common utilities to make testing with [Kotest](https://kotest.io/) easier.

The following methods are provided:

* `intEnumTests`

See the [Module docs](./Module.md) for more information on how to include this artifact in your
build script, and
the [generated Dokka documentation](https://edricchan03.github.io/androidx-ktx-extras/androidx/common/kotest-common-enums/index.html)
for API usage.

---

<a name="0.1.0"></a>

## 0.1.0 (29 May 2024)

* [Maven Central](https://central.sonatype.com/artifact/io.github.edricchan03.androidx.common/kotest-common-enums/0.1.0)
* [GitHub release](https://github.com/EdricChan03/androidx-ktx-extras/releases/tag/kotest-common-enums@0.1.0)

This is the initial release of this artifact, which provides common utilities for testing
code that
uses [`EnumFromValue`](https://edricchan03.github.io/androidx-ktx-extras/androidx/common/common-enums/io.github.edricchan03.androidx.common.enums/-enum-from-value/index.html)
or similar methods:

```kotlin
// Enum
enum class Example {
  One,
  Two,
  Three;

  companion object {
    fun fromValue(value: String) = when (value.lowercase()) {
      "one" -> One
      "example", "two" -> Two
      else -> Three
    }
  }
}

// Test code
val valuesMap = mapOf(
  "one" to Example.One,
  "two" to Example.Two,
  "example" to Example.Two,
  "other value" to Example.Three
)

class ExampleEnumTest : DescribeSpec({
  include(
    enumTests(
      describeName = "fromValue",
      enumValuesMap = valuesMap,
      fromValueOrNullFn = Example.Companion::fromValue,
      invalidArb = Arb.string()
        .filterNot { output -> output in enumValuesMap.map { it.key.value } }
    )
  )
})
```

# Common Enums releases

Common code for enums. An example usage is as follows:

```kt
enum class Example(val value: String) {
    One("one"),
    Two("two"),
    Three("abc");

    companion object : EnumFromValue<String, Example>(default = Three) {
        override fun fromValueOrNull(value: String) = when (value) {
            "one" -> One
            "two" -> Two
            "abc", "other value" -> Three
            else -> null
  }
}
```

See the [Module docs](./Module.md) for more information on how to include this artifact in your build script, and the [generated Dokka documentation](https://edricchan03.github.io/androidx-ktx-extras/androidx/common/common-enums/index.html) for API usage.

---

## 0.0.1 (29 Jun 2023)

This version adds an abstract [`EnumFromValue`](https://edricchan03.github.io/androidx-ktx-extras/androidx/common/common-enums/io.github.edricchan03.androidx.common.enums/-enum-from-value/index.html) class that all enums' companion object with an internal representation should inherit.

* Initial release

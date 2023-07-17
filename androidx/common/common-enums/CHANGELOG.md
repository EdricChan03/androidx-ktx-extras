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

See the [Module docs](./Module.md) for more information on how to include this artifact in your
build script, and
the [generated Dokka documentation](https://edricchan03.github.io/androidx-ktx-extras/androidx/common/common-enums/index.html)
for API usage.

---

<a name="0.0.2"></a>

## 0.0.2 (17 Jul 2023)

* [GitHub release](https://github.com/EdricChan03/androidx-ktx-extras/releases/tag/common-enums@0.0.2)
* [Full changelog](https://github.com/EdricChan03/androidx-ktx-extras/compare/common-enums@0.0.1...common-enums@0.0.2)

This release does not contain any new features, and should be backwards-compatible
with [0.0.1](#001-29-jun-2023).

### Dependency updates

Artifact | Old version | New version
---|---|---
[Kotlin](https://kotlinlang.org) | [`1.8.21`](https://github.com/JetBrains/kotlin/releases/tag/v1.8.21) | [`1.8.22`](https://github.com/JetBrains/kotlin/releases/tag/v1.8.22)

### Relocation notice

The artifact is now published under the `io.github.edricchan03.androidx.common:common-enums`
artifact:

Old artifact coordinates | New artifact coordinates
---|---
 [`io.github.edricchan03.androidx.common.enums:common-enums`](https://central.sonatype.com/artifact/io.github.edricchan03.androidx.common.enums/common-enums) | [`io.github.edricchan03.androidx.common:common-enums`](https://central.sonatype.com/artifact/io.github.edricchan03.androidx.common/common-enums) 

Your build scripts must be updated to the following:

#### Kotlin/Groovy

```diff
-implementation("io.github.edricchan03.androidx.common.enums:common-enums:<version>")
+implementation("io.github.edricchan03.androidx.common:common-enums:<version>")
```

#### Version Catalogs (TOML)

```diff
[libraries]
-androidxtra-common-enums = "io.github.edricchan03.androidx.common.enums:common-enums:<version>
+androidxtra-common-enums = "io.github.edricchan03.androidx.common:common-enums:<version>
```

New versions of the `common-enums` artifact will still be published to the old
artifact ([`io.github.edricchan.androidx.common.enums:common-enums`](https://central.sonatype.com/artifact/io.github.edricchan03.androidx.common.enums/common-enums))
until the next
minor [SemVer](https://semver.org) release, 0.1.0.

<a name="0.0.1"></a>

## 0.0.1 (29 Jun 2023)

* [GitHub release](https://github.com/EdricChan03/androidx-ktx-extras/releases/tag/common-enums@0.0.1)
* [Full changelog](https://github.com/EdricChan03/androidx-ktx-extras/commits/common-enums@0.0.1)

This version adds an
abstract [`EnumFromValue`](https://edricchan03.github.io/androidx-ktx-extras/androidx/common/common-enums/io.github.edricchan03.androidx.common.enums/-enum-from-value/index.html)
class that all enums' companion object with an internal representation should inherit.

* Initial release

# Browser KTX releases

Kotlin extensions for
the [AndroidX Browser](https://developer.android.com/jetpack/androidx/releases/browser) artifact.

See the [Module docs](./Module.md) for more information on how to include this artifact in your
build script, and
the [generated Dokka documentation](https://edricchan03.github.io/androidx-ktx-extras/androidx/browser/browser-ktx/index.html)
for API usage.

---

<a name="0.0.2"></a>

## 0.0.2 (17 Jul 2023)

* [GitHub release](https://github.com/EdricChan03/androidx-ktx-extras/releases/tag/browser-ktx@0.0.2)
* [Full changelog](https://github.com/EdricChan03/androidx-ktx-extras/compare/browser-ktx@0.0.1...browser-ktx@0.0.2)

This release updates the transitive `common-enums` dependency to `0.0.2`. No other
changes are included, and should be backwards-compatible with [0.0.1](#001-29-jun-2023).

> **Note**
> If you are using the [`common-enums`](../../common/common-enums) artifact in your own
> library code, do note the changes in
[`0.0.2`'s release notes](../../common/common-enums/CHANGELOG.md#002-8-jul-2023), particularly
> the artifact relocation.

### Dependency updates

Artifact | Old version | New version
---|---|---
[Kotlin](https://kotlinlang.org) | [`1.8.21`](https://github.com/JetBrains/kotlin/releases/tag/v1.8.21) | [`1.8.22`](https://github.com/JetBrains/kotlin/releases/tag/v1.8.22)

#### Transitive dependency updates

Old artifact | New artifact
---|---
[`io.github.edricchan03.androidx.common.enums:common-enums:0.0.1`](https://central.sonatype.com/artifact/io.github.edricchan03.androidx.common.enums/common-enums/0.0.1) | [`io.github.edricchan03.androidx.common:common-enums:0.0.2`](https://central.sonatype.com/artifact/io.github.edricchan03.androidx.common/common-enums/0.0.2)

##### Visual diff

```diff
-io.github.edricchan03.androidx.common.enums:common-enums:0.0.1
+io.github.edricchan03.androidx.common:common-enums:0.0.2
```

<a name="0.0.1"></a>

## 0.0.1 (29 Jun 2023)

* [GitHub release](https://github.com/EdricChan03/androidx-ktx-extras/releases/tag/browser-ktx@0.0.1)
* [Full changelog](https://github.com/EdricChan03/androidx-ktx-extras/commits/browser-ktx@0.0.1)

This module adds Kotlin extensions and additional utilities
for [version 1.5.0 of the `androidx.browser:browser` artifact](https://developer.android.com/jetpack/androidx/releases/browser#1.5.0).

### Notable changes

* Initial release


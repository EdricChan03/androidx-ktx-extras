# Browser KTX releases

Kotlin extensions for
the [AndroidX Browser](https://developer.android.com/jetpack/androidx/releases/browser) artifact.

See the [Module docs](./Module.md) for more information on how to include this artifact in your
build script, and
the [generated Dokka documentation](https://edricchan03.github.io/androidx-ktx-extras/androidx/browser/browser-ktx/index.html)
for API usage.

---

<a name="0.1.0"></a>

## 0.1.0 (23 Aug 2023)

* [Maven Central](https://central.sonatype.com/artifact/io.github.edricchan03.androidx.browser/browser-ktx/0.1.0)
* [GitHub release](https://github.com/EdricChan03/androidx-ktx-extras/releases/tag/browser-ktx@0.1.0)
* [Full changelog](https://github.com/EdricChan03/androidx-ktx-extras/compare/browser-ktx@0.0.2...browser-ktx@0.1.0)

### Notable changes

* Kotlin has been bumped to [Kotlin 1.9.0](https://kotlinlang.org/docs/whatsnew19.html). For more info, consult the
  [corresponding JetBrains blog post](https://blog.jetbrains.com/kotlin/2023/07/kotlin-1-9-0-released/). ([`69efc43`](https://github.com/EdricChan03/androidx-ktx-extras/commit/69efc435b43b027083ec92c67ed4a43d2dee8f77))
* Setters for Jetpack Browser's `Intent` extras
  ([`407ce70`](https://github.com/EdricChan03/androidx-ktx-extras/commit/407ce701e7ac4073b7b98c2ecaf126367d3e5bca)) were added.
  See the [API updates section](#api-updates) for a list.
* Annotations used in the Jetpack Browser library for representing a list of integer enums were added to the internal `value` property
  for the corresponding enums. ([`e1cdeb7`](https://github.com/EdricChan03/androidx-ktx-extras/commit/e1cdeb7d37698a55f914461b7f96296e7ddeb48c))

### API updates

#### New extension properties

Property | Docs
---|---
`Intent.colorScheme` | [Documentation](https://edricchan03.github.io/androidx-ktx-extras/androidx/browser/browser-ktx/io.github.edricchan03.androidx.browser.ktx/color-scheme.html)
`Intent.urlBarHidingEnabled` | [Documentation](https://edricchan03.github.io/androidx-ktx-extras/androidx/browser/browser-ktx/io.github.edricchan03.androidx.browser.ktx/url-bar-hiding-enabled.html)
`Intent.closeButtonIcon` | [Documentation](https://edricchan03.github.io/androidx-ktx-extras/androidx/browser/browser-ktx/io.github.edricchan03.androidx.browser.ktx/close-button-icon.html)
`Intent.showTitle` | [Documentation](https://edricchan03.github.io/androidx-ktx-extras/androidx/browser/browser-ktx/io.github.edricchan03.androidx.browser.ktx/show-title.html)
`Intent.shareState` | [Documentation](https://edricchan03.github.io/androidx-ktx-extras/androidx/browser/browser-ktx/io.github.edricchan03.androidx.browser.ktx/share-state.html)
`Intent.instantAppsEnabled` | [Documentation](https://edricchan03.github.io/androidx-ktx-extras/androidx/browser/browser-ktx/io.github.edricchan03.androidx.browser.ktx/instant-apps-enabled.html)

#### New setters for existing extension properties

Property | Docs
---|---
`Intent.activityResizeBehavior` | [Documentation](https://edricchan03.github.io/androidx-ktx-extras/androidx/browser/browser-ktx/io.github.edricchan03.androidx.browser.ktx/activity-resize-behavior.html)
`Intent.initialActivityHeightPx` | [Documentation](https://edricchan03.github.io/androidx-ktx-extras/androidx/browser/browser-ktx/io.github.edricchan03.androidx.browser.ktx/initial-activity-height-px.html)
`Intent.toolbarCornerRadiusDp` | [Documentation](https://edricchan03.github.io/androidx-ktx-extras/androidx/browser/browser-ktx/io.github.edricchan03.androidx.browser.ktx/toolbar-corner-radius-dp.html)
`Intent.closeButtonPosition` | [Documentation](https://edricchan03.github.io/androidx-ktx-extras/androidx/browser/browser-ktx/io.github.edricchan03.androidx.browser.ktx/close-button-position.html)

### Dependency updates

Artifact | Old version | New version
---|---|---
[Kotlin](https://kotlinlang.org) | [`1.8.22`](https://github.com/JetBrains/kotlin/releases/tag/v1.8.22) | [`1.9.0`](https://github.com/JetBrains/kotlin/releases/tag/v1.9.0)
[`io.github.edricchan03.androidx.common:common-enums`](https://central.sonatype.com/artifact/io.github.edricchan03.androidx.common.enums/common-enums) | [`0.0.2`](https://central.sonatype.com/artifact/io.github.edricchan03.androidx.common.enums/common-enums/0.0.2) | [`0.1.0`](https://central.sonatype.com/artifact/io.github.edricchan03.androidx.common/common-enums/0.1.0)
<a name="0.0.2"></a>

## 0.0.2 (17 Jul 2023)

* [Maven Central](https://central.sonatype.com/artifact/io.github.edricchan03.androidx.browser/browser-ktx/0.0.2)
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

* [Maven Central](https://central.sonatype.com/artifact/io.github.edricchan03.androidx.browser/browser-ktx/0.0.1)
* [GitHub release](https://github.com/EdricChan03/androidx-ktx-extras/releases/tag/browser-ktx@0.0.1)
* [Full changelog](https://github.com/EdricChan03/androidx-ktx-extras/commits/browser-ktx@0.0.1)

This module adds Kotlin extensions and additional utilities
for [version 1.5.0 of the `androidx.browser:browser` artifact](https://developer.android.com/jetpack/androidx/releases/browser#1.5.0).

### Notable changes

* Initial release


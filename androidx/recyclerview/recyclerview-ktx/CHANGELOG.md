# RecyclerView KTX releases

Kotlin extensions for
the [AndroidX RecyclerView](https://developer.android.com/jetpack/androidx/releases/recyclerview)
artifact.

See the [Module docs](./Module.md) for more information on how to include this artifact in your
build script, and
the [generated Dokka documentation](https://edricchan03.github.io/androidx-ktx-extras/androidx/recyclerview/recyclerview-ktx/index.html)
for API usage.

---

<a name="0.2.0"></a>

## 0.2.0 (21 March 2025)

* [Maven Central](https://central.sonatype.com/artifact/io.github.edricchan03.androidx.recyclerview/recyclerview-ktx/0.2.0)
* [GitHub release](https://github.com/EdricChan03/androidx-ktx-extras/releases/tag/recyclerview-ktx@0.2.0)
* [Full changelog](https://github.com/EdricChan03/androidx-ktx-extras/compare/recyclerview-ktx@0.1.0...recyclerview-ktx@0.2.0)

### Notable changes

> [!warning]
> Note that this module is built with Kotlin 2.0.0+ and may require
> the [K2 compiler](https://kotlinlang.org/docs/k2-compiler-migration-guide.html).

* Top-level [
  `itemCallback`](https://edricchan03.github.io/androidx-ktx-extras/androidx/recyclerview/recyclerview-ktx/io.github.edricchan03.androidx.recyclerview.ktx/item-callback.html)
  and [
  `callback`](https://edricchan03.github.io/androidx-ktx-extras/androidx/recyclerview/recyclerview-ktx/io.github.edricchan03.androidx.recyclerview.ktx/callback.html)
  methods are added to create a [
  `DiffUtil.ItemCallback`](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/DiffUtil.ItemCallback.html)
  and [
  `DiffUtil.Callback`](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/DiffUtil.Callback.html)
  respectively.

#### Added functions

* [Function
  `callback`](https://edricchan03.github.io/androidx-ktx-extras/androidx/recyclerview/recyclerview-ktx/io.github.edricchan03.androidx.recyclerview.ktx/callback.html)

* [Function
  `itemCallback`](https://edricchan03.github.io/androidx-ktx-extras/androidx/recyclerview/recyclerview-ktx/io.github.edricchan03.androidx.recyclerview.ktx/item-callback.html)

### API dependencies

These are the dependencies marked with the `api` configuration that the library uses:

Artifact | Version
---|---
[`androidx.annotation:annotation`](https://developer.android.com/jetpack/androidx/releases/annotation) | [1.9.1](https://developer.android.com/jetpack/androidx/releases/annotation#1.9.1)
[`androidx.recyclerview:recyclerview`](https://developer.android.com/jetpack/androidx/releases/recyclerview) | [1.4.0](https://developer.android.com/jetpack/androidx/releases/recyclerview#recyclerview-1.4.0)

<a name="0.1.0"></a>

## 0.1.0 (20 March 2025)

* [Maven Central](https://central.sonatype.com/artifact/io.github.edricchan03.androidx.recyclerview/recyclerview-ktx/0.1.0)
* [GitHub release](https://github.com/EdricChan03/androidx-ktx-extras/releases/tag/recyclerview-ktx@0.1.0)
* [Full changelog](https://github.com/EdricChan03/androidx-ktx-extras/commits/recyclerview-ktx@0.1.0)

This module adds Kotlin extensions and additional utilities for [version 1.4.0 of the
`androidx.recyclerview:recyclerview` artifact](https://developer.android.com/jetpack/androidx/releases/recyclerview#recyclerview-1.4.0).

### Notable changes

> [!warning]
> Note that this module is built with Kotlin 2.0.0+ and may require
> the [K2 compiler](https://kotlinlang.org/docs/k2-compiler-migration-guide.html).

* Initial release. See below for the added list of methods/functions:

#### Added extension functions

* [Mutable property
  `RecyclerView.ViewHolder.canRecycle`
  ](https://edricchan03.github.io/androidx-ktx-extras/androidx/recyclerview/recyclerview-ktx/io.github.edricchan03.androidx.recyclerview.ktx/can-recycle.html)
* [Mutable property
  `RecyclerView.hasFixedSize`](https://edricchan03.github.io/androidx-ktx-extras/androidx/recyclerview/recyclerview-ktx/io.github.edricchan03.androidx.recyclerview.ktx/has-fixed-size.html)
* [Mutable property
  `RecyclerView.Adapter<*>.hasStableIds`](https://edricchan03.github.io/androidx-ktx-extras/androidx/recyclerview/recyclerview-ktx/io.github.edricchan03.androidx.recyclerview.ktx/has-stable-ids.html)

##### Experimental functions

> [!WARNING]
> These functions may change or have incompatible behaviour when upgrading versions. You have been
> warned!

* [Function
  `RecyclerView.withNoRecycling`](https://edricchan03.github.io/androidx-ktx-extras/androidx/recyclerview/recyclerview-ktx/io.github.edricchan03.androidx.recyclerview.ktx/with-no-recycling.html)

### API dependencies

These are the dependencies marked with the `api` configuration that the library uses:

Artifact | Version
---|---
[`androidx.annotation:annotation`](https://developer.android.com/jetpack/androidx/releases/annotation) | [1.9.1](https://developer.android.com/jetpack/androidx/releases/annotation#1.9.1)
[`androidx.recyclerview:recyclerview`](https://developer.android.com/jetpack/androidx/releases/recyclerview) | [1.4.0](https://developer.android.com/jetpack/androidx/releases/recyclerview#recyclerview-1.4.0)

# Module recyclerview-ktx

Kotlin extensions for
the [AndroidX RecyclerView](https://developer.android.com/jetpack/androidx/releases/recyclerview)
artifact.

## Usage

<a href="https://central.sonatype.com/artifact/io.github.edricchan03.androidx.recyclerview/recyclerview-ktx"><img src="https://img.shields.io/maven-central/v/io.github.edricchan03.androidx.recyclerview/recyclerview-ktx?style=for-the-badge&logo=apachemaven&logoColor=%23C71A36&label=Maven%20Central" alt="RecyclerView KTX on Maven Central"></a>

RecyclerView KTX is available on [Maven Central](https://central.sonatype.com/) as the Maven
coordinate [
`io.github.edricchan03.androidx.recyclerview:recyclerview-ktx`](https://central.sonatype.com/artifact/io.github.edricchan03.androidx.recyclerview/recyclerview-ktx):

`settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
  // ...
  repositories {
    mavenCentral()
  }
}
```

### Kotlin/Groovy

```kotlin
implementation("io.github.edricchan03.androidx.recyclerview:recyclerview-ktx:0.2.0")
```

### TOML

`gradle/libs.versions.toml`:

```toml
[libaries]
androidxtra-recyclerview-ktx = "io.github.edricchan03.androidx.recyclerview:recyclerview-ktx:0.2.0"
```

`build.gradle.kts`:

```kotlin
implementation(libs.androidxtra.recyclerview.ktx)
```

### Snapshots

<a href="https://s01.oss.sonatype.org/content/repositories/snapshots/io/github/edricchan03/androidx/recyclerview/recyclerview-ktx/"><img src="https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fs01.oss.sonatype.org%2Fcontent%2Frepositories%2Fsnapshots%2Fio%2Fgithub%2Fedricchan03%2Fandroidx%2Frecyclerview%2Frecyclerview-ktx%2Fmaven-metadata.xml&style=for-the-badge&logo=apachemaven&logoColor=%23C71A36&label=Maven%20Central%20(snapshots)" alt="RecyclerView KTX on Maven Central (snapshot)"></a>

Alternatively, you can grab the latest built snapshot from Maven Central's snapshots
repository:

`settings.gradle.kts`

```kotlin
dependencyResolutionManagement {
  // ...
  repositories {
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/") {
      // Optionally, you can specify that only snapshots are to be used
      mavenContent {
        snapshotsOnly()
      }
    }
  }
}
```

#### Kotlin

`build.gradle.kts`:

```kotlin
implementation("io.github.edricchan03.androidx.recyclerview:recyclerview-ktx:0.3.0-SNAPSHOT")
```

#### TOML

`gradle/libs.versions.toml`:

```toml
[libaries]
androidxtra-recyclerview-ktx = "io.github.edricchan03.androidx.recyclerview:recyclerview-ktx:0.3.0-SNAPSHOT"
```

`build.gradle.kts`:

```kotlin
implementation(libs.androidxtra.recyclerview.ktx)
```

## Available extensions/methods

Currently, the following extension functions are available:

### Kotlin Properties

The following extension properties have been added:

Extension function | Getter | Setter
---|---|---
[`RecyclerView.ViewHolder.canRecycle`][io.github.edricchan03.androidx.recyclerview.ktx.canRecycle] | [`RecyclerView.ViewHolder.isRecyclable`][androidx.recyclerview.widget.RecyclerView.ViewHolder.isRecyclable] | [`RecyclerView.ViewHolder.setIsRecyclable`][androidx.recyclerview.widget.RecyclerView.ViewHolder.setIsRecyclable]
[`RecyclerView.hasFixedSize`][io.github.edricchan03.androidx.recyclerview.ktx.hasFixedSize] | [`RecyclerView.hasFixedSize`][androidx.recyclerview.widget.RecyclerView.hasFixedSize] | [`RecyclerView.setHasFixedSize`][androidx.recyclerview.widget.RecyclerView.setHasFixedSize]
[`RecyclerView.Adapter.hasStableIds`][io.github.edricchan03.androidx.recyclerview.ktx.hasStableIds] | [`RecyclerView.Adapter.hasStableIds`][androidx.recyclerview.widget.RecyclerView.Adapter.hasStableIds] | [`RecyclerView.Adapter.setHasStableIds`][androidx.recyclerview.widget.RecyclerView.Adapter.setHasStableIds]

### Top-level methods

* [`callback`][io.github.edricchan03.androidx.recyclerview.ktx.callback] (new in version 0.2.0)
* [`itemCallback`][io.github.edricchan03.androidx.recyclerview.ktx.itemCallback] (new in version
  0.2.0)

# Package io.github.edricchan03.androidx.recyclerview.ktx

Top-level Kotlin extensions/utils
for [AndroidX RecyclerView](https://developer.android.com/jetpack/androidx/releases/recyclerview).

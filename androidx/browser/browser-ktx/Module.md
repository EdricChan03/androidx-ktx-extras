# Module browser-ktx

Kotlin extensions for
the [AndroidX Browser](https://developer.android.com/jetpack/androidx/releases/browser)
artifact.

## Usage

<a href="https://central.sonatype.com/artifact/io.github.edricchan03.androidx.browser/browser-ktx"><img src="https://img.shields.io/maven-central/v/io.github.edricchan03.androidx.browser/browser-ktx?style=for-the-badge&logo=apachemaven&logoColor=%23C71A36&label=Maven%20Central" alt="Browser KTX on Maven Central"></a>

Browser KTX is available on [Maven Central](https://central.sonatype.com/) as the Maven
coordinate [`io.github.edricchan03.androidx.browser:browser-ktx`](https://central.sonatype.com/artifact/io.github.edricchan03.androidx.browser/browser-ktx):

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
implementation("io.github.edricchan03.androidx.browser:browser-ktx:0.1.0")
```

### TOML

`gradle/libs.versions.toml`:

```toml
[libaries]
androidxtra-browser-ktx = "io.github.edricchan03.androidx.browser:browser-ktx:0.1.0"
```

`build.gradle.kts`:

```kotlin
implementation(libs.androidxtra.browser.ktx)
```

### Snapshots

<a href="https://s01.oss.sonatype.org/content/repositories/snapshots/io/github/edricchan03/androidx/browser/browser-ktx/"><img src="https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fs01.oss.sonatype.org%2Fcontent%2Frepositories%2Fsnapshots%2Fio%2Fgithub%2Fedricchan03%2Fandroidx%2Fbrowser%2Fbrowser-ktx%2Fmaven-metadata.xml&style=for-the-badge&logo=apachemaven&logoColor=%23C71A36&label=Maven%20Central%20(snapshots)" alt="Browser KTX on Maven Central (snapshot)"></a>

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
implementation("io.github.edricchan03.androidx.browser:browser-ktx:0.1.1-SNAPSHOT")
```

#### TOML

`gradle/libs.versions.toml`:

```toml
[libaries]
androidxtra-browser-ktx = "io.github.edricchan03.androidx.browser:browser-ktx:0.1.1-SNAPSHOT"
```

`build.gradle.kts`:

```kotlin
implementation(libs.androidxtra.browser.ktx)
```

## Available extensions

Currently, the following extension functions are available for the
[`CustomTabsIntent.Builder`][androidx.browser.customtabs.CustomTabsIntent.Builder]:

### Enum variants

The following extension functions have been added in place of their integer enum-like equivalent
setters:

 Extension function                                                                                | Enum                                                                                                        | Java equivalent                                                                                                                      
-------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------
 [`setShareState`][io.github.edricchan03.androidx.browser.ktx.setShareState]                           | [`ShareState`][io.github.edricchan03.androidx.browser.ktx.enums.ShareState]                                 | [Link](https://developer.android.com/reference/androidx/browser/customtabs/CustomTabsIntent.Builder#setShareState(int))                  
 [`setColorScheme`][io.github.edricchan03.androidx.browser.ktx.setColorScheme]                         | [`ColorScheme`][io.github.edricchan03.androidx.browser.ktx.enums.ColorScheme]                               | [Link](https://developer.android.com/reference/androidx/browser/customtabs/CustomTabsIntent.Builder#setColorScheme(int))                 |
| [`setInitialActivityHeightPx`][io.github.edricchan03.androidx.browser.ktx.setInitialActivityHeightPx] | [`ActivityHeightResizeBehavior`][io.github.edricchan03.androidx.browser.ktx.enums.ActivityHeightResizeBehavior] | [Link](https://developer.android.com/reference/androidx/browser/customtabs/CustomTabsIntent.Builder#setInitialActivityHeightPx(int,int)) |
| [`setCloseButtonPosition`][io.github.edricchan03.androidx.browser.ktx.setCloseButtonPosition]         | [`CloseButtonPosition`][io.github.edricchan03.androidx.browser.ktx.enums.CloseButtonPosition]               | [Link](https://developer.android.com/reference/androidx/browser/customtabs/CustomTabsIntent.Builder#setCloseButtonPosition(int))         |

# Package io.github.edricchan03.androidx.browser.ktx

Top-level Kotlin extensions
for [AndroidX Browser](https://developer.android.com/jetpack/androidx/releases/browser).

# Package io.github.edricchan03.androidx.browser.ktx.enums

Enums for [`CustomTabsIntent.Builder`][androidx.browser.customtabs.CustomTabsIntent.Builder]
extension functions which accept their enum variants:

| **Enum**                                                                                                        | **Method**                                                                                            |
|-----------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|
 [`ActivityHeightResizeBehavior`][io.github.edricchan03.androidx.browser.ktx.enums.ActivityHeightResizeBehavior] | [`setInitialActivityHeightPx`][io.github.edricchan03.androidx.browser.ktx.setInitialActivityHeightPx] 
 [`CloseButtonPosition`][io.github.edricchan03.androidx.browser.ktx.enums.CloseButtonPosition]                   | [`setCloseButtonPosition`][io.github.edricchan03.androidx.browser.ktx.setCloseButtonPosition]         
 [`ColorScheme`][io.github.edricchan03.androidx.browser.ktx.enums.ColorScheme]                                   | [`setColorScheme`][io.github.edricchan03.androidx.browser.ktx.setColorScheme]                         
 [`ShareState`][io.github.edricchan03.androidx.browser.ktx.enums.ShareState]                                     | [`setShareState`][io.github.edricchan03.androidx.browser.ktx.setShareState]                           

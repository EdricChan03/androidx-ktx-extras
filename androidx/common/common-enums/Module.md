# Module common-enums

Common enums utilities for androidx-ktx-extras' modules.

## Usage

<a href="https://central.sonatype.com/artifact/io.github.edricchan03.androidx.common/common-enums"><img src="https://img.shields.io/maven-central/v/io.github.edricchan03.androidx.common/common-enums?style=for-the-badge&logo=apachemaven&logoColor=%23C71A36&label=Maven%20Central" alt="Common Enums on Maven Central"></a>

Common Enums is available on [Maven Central](https://central.sonatype.com) as the Maven coordinate [`io.github.edricchan03.androidx.common:common-enums`](https://central.sonatype.com/artifact/io.github.edricchan03.androidx.common/common-enums):

`settings.gradle.kts`

```kotlin
dependencyResolutionManagement {
  // ...
  repositories {
    mavenCentral()
  }
}
```

### Kotlin/Groovy

`build.gradle.kts`

```kotlin
implementation("io.github.edricchan03.androidx.common:common-enums:0.1.0")
```

### TOML

`gradle/libs.versions.toml`:

```toml
[libraries]
androidxtra-common-enums = "io.github.edricchan03.androidx.common:common-enums:0.1.0"
```

`build.gradle.kts`:

```kotlin
implementation(libs.androidxtra.common.enums)
```

### Snapshots

<a href="https://s01.oss.sonatype.org/content/repositories/snapshots/io/github/edricchan03/androidx/common/common-enums/"><img src="https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fs01.oss.sonatype.org%2Fcontent%2Frepositories%2Fsnapshots%2Fio%2Fgithub%2Fedricchan03%2Fandroidx%2Fcommon%2Fcommon-enums%2Fmaven-metadata.xml&style=for-the-badge&logo=apachemaven&logoColor=%23C71A36&label=Maven%20Central%20(snapshots)" alt="Common Enums on Maven Central (snapshot)"></a>

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
implementation("io.github.edricchan03.androidx.common:common-enums:0.1.1-SNAPSHOT")
```

#### TOML

`gradle/libs.versions.toml`:

```toml
[libaries]
androidxtra-common-enums = "io.github.edricchan03.androidx.common:common-enums:0.1.1-SNAPSHOT"
```

`build.gradle.kts`:

```kotlin
implementation(libs.androidxtra.common.enums)
```

# Package io.github.edricchan03.androidx.common.enums

Common enum utilities such as [`EnumFromValue`][io.github.edricchan03.androidx.common.enums.EnumFromValue]:

```kotlin
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

# Module kotest-common-enums

Common [Kotest](https://kotest.io) factory utilities for androidx-ktx-extras' modules.

## Usage

<a href="https://central.sonatype.com/artifact/io.github.edricchan03.androidx.common/kotest-common-enums"><img src="https://img.shields.io/maven-central/v/io.github.edricchan03.androidx.common/kotest-common-enums?style=for-the-badge&logo=apachemaven&logoColor=%23C71A36&label=Maven%20Central" alt="Kotest Common Enums on Maven Central"></a>

Kotest Common Enums is available on [Maven Central](https://central.sonatype.com) as the Maven
coordinate [`io.github.edricchan03.androidx.common:kotest-common-enums`](https://central.sonatype.com/artifact/io.github.edricchan03.androidx.common/kotest-common-enums):

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
implementation("io.github.edricchan03.androidx.common:kotest-common-enums:0.1.0")
```

### TOML

`gradle/libs.versions.toml`:

```toml
[libraries]
androidxtra-common-enums-kotest = "io.github.edricchan03.androidx.common:kotest-common-enums:0.1.0"
```

`build.gradle.kts`:

```kotlin
implementation(libs.androidxtra.common.enums.kotest)
```

### Snapshots

<a href="https://s01.oss.sonatype.org/content/repositories/snapshots/io/github/edricchan03/androidx/common/kotest-common-enums/"><img src="https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fs01.oss.sonatype.org%2Fcontent%2Frepositories%2Fsnapshots%2Fio%2Fgithub%2Fedricchan03%2Fandroidx%2Fcommon%2Fkotest-common-enums%2Fmaven-metadata.xml&style=for-the-badge&logo=apachemaven&logoColor=%23C71A36&label=Maven%20Central%20(snapshots)" alt="Kotest Common Enums on Maven Central (snapshot)"></a>

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
implementation("io.github.edricchan03.androidx.common:kotest-common-enums:0.1.1-SNAPSHOT")
```

#### TOML

`gradle/libs.versions.toml`:

```toml
[libaries]
androidxtra-common-enums-kotest = "io.github.edricchan03.androidx.common:kotest-common-enums:0.1.1-SNAPSHOT"
```

`build.gradle.kts`:

```kotlin
implementation(libs.androidxtra.common.enums.kotest)
```

# Package io.github.edricchan03.androidx.common.enums.fixtures

[Kotest](https://kotest.io) property functions for testing enums that represent
an internal value. An example is as shown below:

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

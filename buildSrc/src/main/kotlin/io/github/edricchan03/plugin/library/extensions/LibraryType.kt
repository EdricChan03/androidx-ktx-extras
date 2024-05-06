package io.github.edricchan03.plugin.library.extensions

enum class LibraryType {
    /** Denotes a pure Kotlin/JVM project. This is the default value if not specified. */
    Jvm,

    /** Denotes an Android project. */
    Android,

    /** Denotes a Kotlin Multiplatform project. */
    Multiplatform
}

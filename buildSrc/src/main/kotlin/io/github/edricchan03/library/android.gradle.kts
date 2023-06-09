package io.github.edricchan03.library

import io.github.edricchan03.plugin.explicit_api.ExplicitApiModePlugin

plugins {
    `android-library`
    `kotlin-android`
//    id("io.github.edricchan03.kotlin-explicit-api")
    id("io.github.edricchan03.library.common")
}

// TODO: Remove when Kotlin 1.9.0 is released (https://youtrack.jetbrains.com/issue/KT-37652)
apply<ExplicitApiModePlugin>()

kotlin {
    jvmToolchain(11)
    explicitApi()
    println("Explicit API: $explicitApi")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // TODO: Remove compileOptions and kotlinOptions when AGP 8.1.0 is out
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            // TODO: Uncomment when Dokkatoo is merged into Dokka
//            withJavadocJar()
        }
    }
}

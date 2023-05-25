package io.github.edricchan03.library

import io.github.edricchan03.plugin.explicit_api.ExplicitApiModePlugin

plugins {
    `android-library`
    `kotlin-android`
//    id("io.github.edricchan03.kotlin-explicit-api")
    id("io.github.edricchan03.library.common")
}

// TODO: Remove when https://youtrack.jetbrains.com/issue/KT-37652 is fixed
apply<ExplicitApiModePlugin>()

kotlin {
    jvmToolchain(8)
    explicitApi()
    println("Explicit API: $explicitApi")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            // TODO: Uncomment when Dokkatoo gets merged into Dokka
//            withJavadocJar()
        }
    }
}

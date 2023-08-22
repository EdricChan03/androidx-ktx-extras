package io.github.edricchan03.androidx.browser.ktx

import android.content.Intent
import android.os.Build
import android.os.Parcelable

// Ported from
// https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:core/core/src/main/java/androidx/core/content/IntentCompat.java;l=229-237;drc=2d0bac96f2d8f9ef7dd1d988e9c43fc622369977
@Suppress("DEPRECATION")
internal inline fun <reified T : Parcelable> Intent.getParcelableExtraCompat(name: String?): T? =
    if (Build.VERSION.SDK_INT >= 34) getParcelableExtra(name, T::class.java)
    else {
        val extra: T? = getParcelableExtra(name)
        if (T::class.isInstance(extra)) extra else null
    }

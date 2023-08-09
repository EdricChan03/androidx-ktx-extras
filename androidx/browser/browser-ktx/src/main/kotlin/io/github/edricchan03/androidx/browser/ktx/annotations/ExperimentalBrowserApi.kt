package io.github.edricchan03.androidx.browser.ktx.annotations

import android.annotation.SuppressLint

/**
 * Denotes APIs that are experimental. Their implementation is likely to be changed,
 * and no backwards compatibility is guaranteed.
 * @since 0.0.1
 */
// RequiresOptIn annotations can be RUNTIME
// https://kotlinlang.org/docs/opt-in-requirements.html#create-opt-in-requirement-annotations
@SuppressLint("ExperimentalAnnotationRetention")
@RequiresOptIn(
    "This Browser KTX API is experimental and is likely to be changed " +
        "or removed in the future."
)
@MustBeDocumented
public annotation class ExperimentalBrowserApi

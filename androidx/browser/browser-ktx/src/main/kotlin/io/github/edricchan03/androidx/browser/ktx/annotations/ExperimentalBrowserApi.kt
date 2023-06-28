package io.github.edricchan03.androidx.browser.ktx.annotations

/**
 * Denotes APIs that are experimental. Their implementation is likely to be changed,
 * and no backwards compatibility is guaranteed.
 */
@RequiresOptIn(
    "This Browser KTX API is experimental and is likely to be changed " +
        "or removed in the future."
)
@MustBeDocumented
public annotation class ExperimentalBrowserApi

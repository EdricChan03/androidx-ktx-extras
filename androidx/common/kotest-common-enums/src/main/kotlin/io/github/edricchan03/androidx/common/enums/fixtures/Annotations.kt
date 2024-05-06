package io.github.edricchan03.androidx.common.enums.fixtures

/**
 * Denotes APIs that are experimental. Their implementation is likely to be changed,
 * and no backwards compatibility is guaranteed.
 * @since 0.1.0
 */
@RequiresOptIn(
    "This Kotest enum utility API is experimental and is likely to be changed " +
        "or removed in the future."
)
@MustBeDocumented
public annotation class ExperimentalEnumKotestApi

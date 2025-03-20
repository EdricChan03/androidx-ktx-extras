package io.github.edricchan03.androidx.recyclerview.ktx.annotations

/**
 * Denotes APIs that are experimental. Their implementation is likely to be changed,
 * and no backwards compatibility is guaranteed.
 * @since 0.1.0
 */
@RequiresOptIn(
    "This RecyclerView KTX API is experimental and is likely to be changed " +
        "or removed in the future."
)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
public annotation class ExperimentalRecyclerViewApi

package io.github.edricchan03.androidx.recyclerview.ktx

import androidx.recyclerview.widget.RecyclerView

// For https://issuetracker.google.com/issues/204918397

/**
 * Whether to avoid invalidating the whole layout when its adapter contents change.
 * @since 0.1.0
 * @see RecyclerView.hasFixedSize
 * @see RecyclerView.setHasFixedSize
 */
public var RecyclerView.hasFixedSize: Boolean
    get() = hasFixedSize()
    set(value) {
        setHasFixedSize(value)
    }

/**
 * Indicates whether each item in the data set can be represented with a unique
 * identifier of type [Long].
 * @since 0.1.0
 * @see RecyclerView.Adapter.hasStableIds
 * @see RecyclerView.Adapter.setHasStableIds
 */
public var <VH : RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.hasStableIds: Boolean
    get() = hasStableIds()
    set(value) {
        setHasStableIds(value)
    }

/**
 * Informs the recycler whether this item can be recycled.
 * Views which are not recyclable will not be reused for other items until
 * [canRecycle] is later set to true.
 *
 * Calls to [canRecycle] should always be paired (one call to `canRecycle = false`
 * should always be matched with a later call to `canRecycle = true`).
 * Pairs of calls may be nested, as the state is internally reference-counted.
 * @since 0.1.0
 * @see RecyclerView.ViewHolder.isRecyclable
 * @see RecyclerView.ViewHolder.setIsRecyclable
 */
// NB: Named canRecycle to prevent a synthetic clash
public var RecyclerView.ViewHolder.canRecycle: Boolean
    get() = isRecyclable
    set(value) {
        setIsRecyclable(value)
    }

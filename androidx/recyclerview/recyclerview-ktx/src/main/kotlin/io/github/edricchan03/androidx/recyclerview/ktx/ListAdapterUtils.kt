package io.github.edricchan03.androidx.recyclerview.ktx

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * [DiffUtil.Callback] serves two roles - list indexing, and item diffing.
 *
 * [DiffUtil.ItemCallback] handles just the second of these, which allows separation of code
 * that indexes into an array or List from the presentation-layer and content specific diffing code.
 *
 * This top-level method allows creating an [DiffUtil.ItemCallback] without requiring an anonymous
 * object to be created.
 * @param T Type of items to compare.
 * @param areItemsTheSame Called to check whether two objects represent the same item.
 * For example, if your items have unique ids, this method should check their id equality.
 *
 * Note: `null` items in the list are assumed to be the same as another `null` item and are
 * assumed to not be the same as a non-`null` item. This callback will not be invoked for
 * either of those cases.
 * @param areContentsTheSame Called to check whether two items have the same data.
 * This information is used to detect if the contents of an item have changed.
 * This method is used to check equality instead of [Any.equals] so that you can change its
 * behavior depending on your UI.
 *
 * For example, if you are using [DiffUtil] with a [RecyclerView.Adapter], you should return
 * whether the items' visual representations are the same.
 * This method is called only if [areItemsTheSame] returns true for these items.
 *
 * Note: Two `null` items are assumed to represent the same contents. This callback will not
 * be invoked for this case.
 * @param getChangePayload When [areItemsTheSame] returns `true` for two items and
 * [areContentsTheSame] returns `false` for them, this method is called to get a payload
 * about the change.
 *
 * For example, if you are using [DiffUtil] with [RecyclerView], you can return the particular
 * field that changed in the item and your [RecyclerView.ItemAnimator] can use that information
 * to run the correct animation.
 *
 * Default implementation returns `null`.
 * @since 0.2.0
 */
@JvmOverloads
public inline fun <T : Any> itemCallback(
    crossinline areItemsTheSame: (oldItem: T, newItem: T) -> Boolean,
    crossinline areContentsTheSame: (oldItem: T, newItem: T) -> Boolean,
    crossinline getChangePayload: (oldItem: T, newItem: T) -> Any? = { _, _ -> null }
): DiffUtil.ItemCallback<T> = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(old: T, new: T) = areItemsTheSame(old, new)
    override fun areContentsTheSame(old: T, new: T) = areContentsTheSame(old, new)
    override fun getChangePayload(old: T, new: T) = getChangePayload(old, new)
}

/**
 * A Callback class used by [DiffUtil] while calculating the diff between two lists.
 *
 * This top-level method allows creating a [DiffUtil.Callback] without requiring an explicit
 * anonymous object to be created.
 * @param getOldListSize Returns the size of the old list.
 * @param getNewListSize Returns the size of the new list.
 * @param areItemsTheSame Called by the [DiffUtil] to decide whether two object represent the same
 * Item. For example, if your items have unique ids, this method should check their id equality.
 * @param areContentsTheSame Called by the [DiffUtil] when it wants to check whether two items
 * have the same data. [DiffUtil] uses this information to detect if the contents of an item
 * has changed.
 *
 * [DiffUtil] uses this method to check equality instead of [Any.equals] so that you can change
 * tts behavior depending on your UI. For example, if you are using [DiffUtil] with a
 * [RecyclerView.Adapter], you should return whether the items' visual representations are the same.
 * This method is called only if [areItemsTheSame] returns `true` for these items.
 * @param getChangePayload When [areItemsTheSame] returns `true` for two items and
 * [areContentsTheSame] returns `false` for them, [DiffUtil] calls this method to get a payload
 * about the change.
 *
 * For example, if you are using [DiffUtil] with [RecyclerView], you can return the particular
 * field that changed in the item and your [RecyclerView.ItemAnimator] can use that information
 * to run the correct animation.
 *
 * Default implementation returns `null`.
 * @since 0.2.0
 */
@JvmOverloads
public inline fun callback(
    crossinline getOldListSize: () -> Int,
    crossinline getNewListSize: () -> Int,
    crossinline areItemsTheSame: (oldItemPosition: Int, newItemPosition: Int) -> Boolean,
    crossinline areContentsTheSame: (oldItemPosition: Int, newItemPosition: Int) -> Boolean,
    crossinline getChangePayload: (oldItemPosition: Int, newItemPosition: Int) -> Any? = { _, _ -> null }
): DiffUtil.Callback = object : DiffUtil.Callback() {
    override fun getOldListSize() = getOldListSize()

    override fun getNewListSize() = getNewListSize()

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        areItemsTheSame(oldItemPosition, newItemPosition)

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        areContentsTheSame(oldItemPosition, newItemPosition)

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int) =
        getChangePayload(oldItemPosition, newItemPosition)
}

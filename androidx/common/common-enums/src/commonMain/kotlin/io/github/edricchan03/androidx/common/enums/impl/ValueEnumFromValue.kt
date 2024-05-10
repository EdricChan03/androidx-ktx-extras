package io.github.edricchan03.androidx.common.enums.impl

import io.github.edricchan03.androidx.common.enums.EnumFromValue
import io.github.edricchan03.androidx.common.enums.ExperimentalEnumsApi
import io.github.edricchan03.androidx.common.enums.ValueEnum
import kotlin.enums.EnumEntries

/**
 * [EnumFromValue] implementation for enums that implement the [ValueEnum]
 * interface.
 *
 * [fromValueOrNull] is implemented to use the result of the following operation:
 * ```kotlin
 * enumEntries.find { it.value == value }
 * ```
 * Note that [fromValueOrNull] can still be overridden if the default implementation
 * isn't desirable.
 * @param default The default enum value to use if no such value was found from the
 * [List.find] operation.
 * @param enumEntries The list of entries present in the [ValueEnum].
 * @since 0.2.0
 * @see EnumFromValue
 */
@ExperimentalEnumsApi
public open class ValueEnumFromValue<Value, T>(
    default: T,
    private val enumEntries: EnumEntries<T>
) : EnumFromValue<Value, T>(default = default) where T : Enum<T>, T : ValueEnum<Value> {
    override fun fromValueOrNull(value: Value): T? = enumEntries.find { it.value == value }
}

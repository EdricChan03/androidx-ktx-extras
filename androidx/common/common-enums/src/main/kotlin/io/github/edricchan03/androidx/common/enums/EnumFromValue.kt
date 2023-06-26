package io.github.edricchan03.androidx.common.enums

/**
 * Abstract class for enum wrappers that wrap over primitive enum values.
 * The companion object of the enum wrapper should implement this abstract class.
 *
 * [fromValueOrNull] should be implemented to indicate how the given `value` should be parsed
 * to its enum entry equivalent.
 * @param InternalValue The internal value representation of the enum.
 * @param E The enum.
 * @param default The default value to be used for [fromValue].
 */
public abstract class EnumFromValue<InternalValue, E : Enum<E>>(private val default: E) {
    /**
     * Gets the [E] representation of [value], or `null` if no such representation
     * exists.
     */
    public abstract fun fromValueOrNull(value: InternalValue): E?

    /**
     * Gets the [E] representation of [value], or [default] if no such representation
     * exists.
     */
    public fun fromValueOrElse(value: InternalValue, default: E): E =
        fromValueOrNull(value) ?: default

    /**
     * Gets the [E] representation of [value], or [default] if no such representation
     * exists.
     */
    public fun fromValue(value: InternalValue): E = fromValueOrElse(value, default)

    /**
     * Gets the [E] representation of [value], or throws an [IllegalArgumentException]
     * if no such representation exists.
     */
    public fun requireValue(value: InternalValue): E = requireNotNull(fromValueOrNull(value))

    /** Checks if an enum entry with the given [value] exists. */
    public fun hasValue(value: InternalValue): Boolean = fromValueOrNull(value) != null
}

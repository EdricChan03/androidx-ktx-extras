package io.github.edricchan03.androidx.common.enums.fixtures

import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0

/**
 * An enum-map entry, with its property name and value representation.
 * @property propertyName Its property name.
 * @property value Its value representation. Can be of any type as denoted by the
 * [Value] generic.
 * @since 0.1.0
 */
public data class EnumMapEntry<Value>(
    val propertyName: String,
    val value: Value
)

/**
 * Converts a Kotlin property to its [EnumMapEntry] equivalent.
 * @param args Additional arguments to be passed to [KProperty.Getter.call].
 * @since 0.1.0
 * @see enumTests
 */
public fun <Value> KProperty<Value>.toEnumEntry(vararg args: Any?): EnumMapEntry<Value> =
    EnumMapEntry(
        propertyName = name,
        value = getter.call(args)
    )

/**
 * Converts a Kotlin property (without a receiver) to its [EnumMapEntry] equivalent.
 * @since 0.1.0
 * @see enumTests
 */
public fun <Value> KProperty0<Value>.toEnumEntry(): EnumMapEntry<Value> = EnumMapEntry(
    propertyName = name,
    value = get()
)

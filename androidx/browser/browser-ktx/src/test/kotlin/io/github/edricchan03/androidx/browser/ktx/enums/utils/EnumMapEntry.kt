package io.github.edricchan03.androidx.browser.ktx.enums.utils

import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0

data class EnumMapEntry<Value>(
    val propertyName: String,
    val value: Value
)

fun <Value> KProperty<Value>.toEnumEntry(vararg args: Any?) = EnumMapEntry(
    propertyName = name,
    value = getter.call(args)
)

fun <Value> KProperty0<Value>.toEnumEntry() = EnumMapEntry(
    propertyName = name,
    value = get()
)

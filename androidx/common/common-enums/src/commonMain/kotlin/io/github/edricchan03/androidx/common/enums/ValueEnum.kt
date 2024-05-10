package io.github.edricchan03.androidx.common.enums

/**
 * Interface which [Enum]s should implement to indicate that
 * they support the [EnumFromValue] specification.
 * @since 0.2.0
 */
public interface ValueEnum<T> {
    /**
     * The underlying internal value.
     * @since 0.2.0
     */
    public val value: T
}

package io.github.edricchan03.publishing

import java.util.Locale

private fun String.capitalise() =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

/**
 * Computes the Gradle task name to generate Javadocs given the
 * [variantName] and [whether the Javadoc should be Dokka's HTML output][isHtml].
 */
fun computeJavadocTaskName(variantName: String? = null, isHtml: Boolean) =
    "javaDoc${if (isHtml) "Html" else ""}${variantName?.capitalise().orEmpty()}Jar"

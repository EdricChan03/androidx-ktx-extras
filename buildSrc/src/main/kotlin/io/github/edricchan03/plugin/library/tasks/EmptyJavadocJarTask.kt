package io.github.edricchan03.plugin.library.tasks

import org.gradle.api.tasks.CacheableTask
import org.gradle.jvm.tasks.Jar

/**
 * Stub Javadoc [Jar] publishing task for targets which don't support producing
 * a Javadoc jar.
 */
@CacheableTask
abstract class EmptyJavadocJarTask : Jar() {
    init {
        archiveClassifier.set("javadoc")
    }
}

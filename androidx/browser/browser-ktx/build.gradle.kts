import io.github.edricchan03.publishing.computeJavadocTaskName

plugins {
    io.github.edricchan03.library.android
    io.github.edricchan03.docs.`dokka-android`
    io.github.edricchan03.publishing.maven
    io.github.edricchan03.publishing.`dokkatoo-android`
    io.github.edricchan03.publishing.`maven-gh-packages`
    io.github.edricchan03.publishing.`maven-sonatype`
}

group = "io.github.edricchan03.androidx.browser"
version = "0.0.1"

@Suppress("UnstableApiUsage")
android {
    namespace = "io.github.edricchan03.androidx.browser.ktx"

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}

dependencies {
    api(projects.androidx.common.commonEnums)

    api(androidLibs.androidx.annotation)
    api(androidLibs.androidx.browser)

    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.kotest.property)
    testImplementation(libs.kotest.framework.datatest)
    testImplementation(libs.kotlin.reflect) {
        because("Kotlin extension support for enum tests")
    }
}

publishing.publications {
    val release by registering(MavenPublication::class) {
        pom {
            name.set("androidx-ktx-extras-browser")
            description.set("Kotlin extensions for AndroidX Browser")
            url.set("https://github.com/EdricChan03/android-ktx-extras/tree/main/androidx/browser/browser-ktx")
            inceptionYear.set("2023")

            licenses {
                license {
                    name.set("GPLv3")
                    url.set("https://github.com/EdricChan03/androidx-ktx-extras/blob/main/LICENSE")
                }

                // AndroidX license
                license {
                    name.set("Apache License 2.0")
                    url.set("https://github.com/androidx/androidx/blob/androidx-main/LICENSE.txt")
                }
            }

            developers {
                developer {
                    id.set("EdricChan03")
                    name.set("Edric Chan")
                    email.set("edric.chan.1997@gmail.com")
                    url.set("https://github.com/EdricChan03")
                    timezone.set("UTC+8")
                }
            }

            scm {
                connection.set("scm:git:github.com/EdricChan03/androidx-ktx-extras.git")
                developerConnection.set("scm:git:ssh://github.com/EdricChan03/androidx-ktx-extras.git")
                url.set("https://github.com/EdricChan03/androidx-ktx-extras")
            }

            issueManagement {
                system.set("GitHub Issues")
                url.set("https://github.com/EdricChan03/androidx-ktx-extras/issues")
            }

            ciManagement {
                system.set("GitHub Actions")
                url.set("https://github.com/EdricChan03/androidx-ktx-extras/actions")
            }
        }

        afterEvaluate {
            from(components["release"])

            // Add Javadocs as an artifact
            artifact(tasks[computeJavadocTaskName("release", isHtml = false)])
            artifact(tasks[computeJavadocTaskName("release", isHtml = true)])
        }
    }
}

package io.github.edricchan03.publishing

plugins {
    `maven-publish`
}

val sonatypeStagingUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
val sonatypeSnapshotUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"

/** Whether to publish the artifacts to [sonatypeStagingUrl] instead of [sonatypeSnapshotUrl]. */
val isRelease: Boolean by extra { !version.toString().endsWith("SNAPSHOT") }

publishing {
    repositories {
        maven {
            name = "Sonatype"
            url = uri(if (isRelease) sonatypeStagingUrl else sonatypeSnapshotUrl)
            credentials {
                username =
                    project.findProperty("sonatype.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("sonatype.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
}

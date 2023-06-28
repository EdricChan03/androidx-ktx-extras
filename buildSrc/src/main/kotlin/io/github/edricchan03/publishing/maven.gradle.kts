package io.github.edricchan03.publishing

plugins {
    `maven-publish`
    signing
}

signing {
    if (System.getenv("CI") == "true") {
        val signingKeyId: String? by project
        val signingKey: String? by project
        val signingPassword: String? by project
        useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
    } else {
        // Use the gpg-agent if possible
        useGpgCmd()
    }

    sign(publishing.publications)
}

tasks {
    // From https://discuss.gradle.org/t/retrieve-gav-of-application-automatically/29889/7
    val dumpPublications by registering {
        description = "Dumps the publications for project ${project.name}"
        doLast {
            logger.lifecycle("Publications for project ${project.name}\n${"-".repeat(50)}")
            publishing.publications.withType(MavenPublication::class.java) { ->
                logger.lifecycle("$name => $groupId:$artifactId:$version")
            }
        }
    }
}

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
        group = PublishingPlugin.PUBLISH_TASK_GROUP
        description = "Dumps the publications for project ${project.name}"
        doLast {
            logger.lifecycle("Publications for project ${project.name}\n${"-".repeat(50)}")
            publishing.publications.withType<MavenPublication> {
                logger.lifecycle("$name => $groupId:$artifactId:$version")
            }
        }
    }

    val dumpPublicationArtifacts by registering {
        group = PublishingPlugin.PUBLISH_TASK_GROUP
        description = "Dumps the publications and their artifacts for project ${project.name}"
        doLast {
            logger.let {
                it.lifecycle("Publication artifacts for project ${project.name}\n${"-".repeat(50)}")
                publishing.publications.withType<MavenPublication> {
                    it.lifecycle("$name => $groupId:$artifactId:$version")
                    it.lifecycle("Artifacts:")
                    artifacts.all {
                        it.lifecycle("* $file")
                    }
                }
            }
        }
    }

    val dumpMavenRepositories by registering {
        group = PublishingPlugin.PUBLISH_TASK_GROUP
        description = "Dumps the publishing Maven repositories for project ${project.name}"
        doLast {
            logger.lifecycle(
                "Publishing Maven repositories for project ${project.name}\n" +
                    "-".repeat(50)
            )
            publishing.repositories.withType<MavenArtifactRepository> {
                logger.lifecycle("$name => $url")
            }
        }
    }
}

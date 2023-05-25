package io.github.edricchan03.publishing

plugins {
    `maven-publish`
    signing
}

signing {
    val signingKeyId: String? by project
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)

    sign(publishing.publications)
}

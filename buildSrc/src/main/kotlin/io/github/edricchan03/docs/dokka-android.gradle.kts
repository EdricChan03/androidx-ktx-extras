package io.github.edricchan03.docs

plugins {
    id("io.github.edricchan03.docs.dokka")
}

dokkatoo {
    dokkatooSourceSets.configureEach {
        enableAndroidDocumentationLink.set(true)
        suppress.set(true)
    }

    // Only un-suppress the main source-set for now
    // TODO: Remove
    dokkatooSourceSets.named("main") {
        suppress.set(false)
    }
}

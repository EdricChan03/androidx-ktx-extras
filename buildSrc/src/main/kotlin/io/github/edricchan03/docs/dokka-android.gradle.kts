package io.github.edricchan03.docs

plugins {
    id("io.github.edricchan03.docs.dokka")
}

dokkatoo {
    dokkatooSourceSets {
        configureEach {
            suppress.set(true)
        }

        // Only un-suppress the main source-set for now
        // TODO: Remove
        val main by existing {
            suppress.set(false)
        }
    }
}

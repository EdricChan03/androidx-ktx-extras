package io.github.edricchan03.docs

plugins {
    dev.adamko.`dokkatoo-html`
}

// TODO: Add Dokkatoo config
dokkatoo {
    dokkatooSourceSets.configureEach {
        reportUndocumented.set(true)

        // Link to source
        sourceLink {
            localDirectory.set(file("src/${name}/kotlin"))
            remoteUrl(
                modulePath.map {
                    "https://github.com/EdricChan03/androidx-ktx-extras/" +
                            "tree/main/${it}/src/${name}/kotlin"
                }
            )
        }
    }
}

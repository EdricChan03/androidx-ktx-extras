package io.github.edricchan03.docs

plugins {
    dev.adamko.`dokkatoo-html`
}

// Path to the Module.md file, or null if it doesn't exist
val moduleReadme = file("Module.md").takeIf { it.exists() }

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

        // Only include the module docs if it exists
        moduleReadme?.let {
            includes.from(it)
        }
    }
}

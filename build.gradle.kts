plugins {
    org.jetbrains.dokka
}

group = "io.github.edricchan03.androidx"

dependencies {
    dokka(projects.androidx.common.commonEnums)
    dokka(projects.androidx.common.kotestCommonEnums)
    dokka(projects.androidx.browser.browserKtx)
}

dokka {
    pluginsConfiguration.html {
        customAssets.from(rootDir.toPath().resolve("docs/assets/logo-icon.svg"))
        footerMessage.set(
            "&copy; 2023-2024 Edric Chan. androidx-ktx-extras is licensed under the " +
                "<a href=\"https://github.com/EdricChan03/androidx-ktx-extras/blob/main/LICENSE\">" +
                "GNU GPL 3.0</a>. AndroidX is licensed under the " +
                "<a href=\"https://github.com/androidx/androidx/blob/androidx-main/LICENSE.txt\">" +
                "Apache License 2.0</a>."
        )
    }
}

plugins {
    org.jetbrains.dokka
    id("com.gradleup.nmcp.aggregation")
    alias(libs.plugins.kotlin.composeCompiler) apply false
    alias(libs.plugins.kotest.multiplatform) apply false
}

group = "io.github.edricchan03.androidx"

nmcpAggregation {
    centralPortal {
        val usernameEnv = project.providers.environmentVariable("USERNAME")
        val tokenEnv = project.providers.environmentVariable("TOKEN")

        username = usernameEnv.orElse(project.providers.gradleProperty("central.name"))
        password = tokenEnv.orElse(project.providers.gradleProperty("central.token"))

        publishingType = "USER_MANAGED"
    }
}

dependencies {
    dokka(projects.androidx.common.commonEnums)
    dokka(projects.androidx.common.kotestCommonEnums)
    dokka(projects.androidx.browser.browserKtx)
    dokka(projects.androidx.recyclerview.recyclerviewKtx)
    nmcpAggregation(projects.androidx.common.commonEnums)
    nmcpAggregation(projects.androidx.common.kotestCommonEnums)
    nmcpAggregation(projects.androidx.browser.browserKtx)
    nmcpAggregation(projects.androidx.recyclerview.recyclerviewKtx)
}

dokka {
    pluginsConfiguration.html {
        customAssets.from(layout.projectDirectory.file("docs/assets/logo-icon.svg"))
        footerMessage = "&copy; 2023-2025 Edric Chan. androidx-ktx-extras is licensed under the " +
            "<a href=\"https://github.com/EdricChan03/androidx-ktx-extras/blob/main/LICENSE\" class=\"footer--link footer--link_external\">" +
            "GNU GPL 3.0</a>. AndroidX is licensed under the " +
            "<a href=\"https://github.com/androidx/androidx/blob/androidx-main/LICENSE.txt\" class=\"footer--link footer--link_external\">" +
            "Apache License 2.0</a>."
        homepageLink = "https://github.com/EdricChan03/androidx-ktx-extras"
    }
}

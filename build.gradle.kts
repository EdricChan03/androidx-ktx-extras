plugins {
    dev.adamko.`dokkatoo-html`
}

dependencies {
    dokkatoo(projects.androidx.browser.browserKtx)

    // TODO: Remove when https://github.com/adamko-dev/dokkatoo/issues/14 is fixed
    dokkatooPluginHtml(
        dokkatoo.versions.jetbrainsDokka.map { dokkaVersion ->
            "org.jetbrains.dokka:all-modules-page-plugin:$dokkaVersion"
        }
    )
}

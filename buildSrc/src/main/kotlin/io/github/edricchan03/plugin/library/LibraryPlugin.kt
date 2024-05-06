package io.github.edricchan03.plugin.library

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import dev.adamko.dokkatoo.DokkatooExtension
import dev.adamko.dokkatoo.dokka.parameters.DokkaSourceSetSpec
import dev.adamko.dokkatoo.formats.DokkatooHtmlPlugin
import dev.adamko.dokkatoo.formats.DokkatooJavadocPlugin
import dev.adamko.dokkatoo.tasks.DokkatooGenerateTask
import io.github.edricchan03.plugin.library.extensions.LibraryPluginExtension
import io.github.edricchan03.plugin.library.extensions.LibraryType
import io.github.edricchan03.plugin.library.extensions.docs.ExternalDocLinks
import io.github.edricchan03.plugin.library.extensions.docs.LibraryDocsExtension
import io.github.edricchan03.plugin.library.extensions.isReleaseVersion
import io.github.edricchan03.plugin.library.extensions.publish.DefaultReleaseVersionSpec
import io.github.edricchan03.plugin.library.extensions.publish.asReadOnlyProvider
import io.github.edricchan03.publishing.computeJavadocTaskName
import kotlinx.validation.BinaryCompatibilityValidatorPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.attributes.DocsType
import org.gradle.api.logging.Logging
import org.gradle.api.plugins.BasePlugin
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.publish.PublicationContainer
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.api.publish.plugins.PublishingPlugin
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.existing
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.hasPlugin
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.registering
import org.gradle.kotlin.dsl.withType
import org.gradle.plugins.signing.SigningExtension
import org.gradle.plugins.signing.SigningPlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinTopLevelExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import java.net.URI
import com.android.build.gradle.LibraryExtension as AGPLibraryExtension
import com.android.build.gradle.LibraryPlugin as AGPLibraryPlugin
import io.github.edricchan03.plugin.library.extensions.publish.gitHubPackagesUrl as GitHubPackagesUrl
import io.github.edricchan03.plugin.library.extensions.publish.sonatypeSnapshotUrl as SonatypeSnapshotUrl
import io.github.edricchan03.plugin.library.extensions.publish.sonatypeStagingUrl as SonatypeStagingUrl

// TODO: Add Android convention support
// TODO: Add Kotlin/JVM + Kotlin Multiplatform convention support
class LibraryPlugin : Plugin<Project> {
    private lateinit var libs: VersionCatalog
    private lateinit var androidLibs: VersionCatalog

    override fun apply(project: Project) {
        // Set version catalogs
        with(project.extensions.getByType<VersionCatalogsExtension>()) {
            libs = named("libs")
            androidLibs = named("androidLibs")
        }

        val extension = project.extensions
            .create<LibraryPluginExtension>(LibraryPluginExtension.EXTENSION_NAME)

        extension.setConventions(project)

        with(project) {
            applyPlugins(extension)
            applyLibraryExtension(extension)
            applyExtensions(extension)
            registerTasks()
            registerVariantTasks()
            registerKmpTasks()
        }
    }

    // Versions
    private val compilerVersion: String by lazy {
        androidLibs.findVersion("compose-compiler").map { it.requiredVersion }
            .orElse(DEFAULT_KOTLIN_COMPOSE_EXTENSION_VERSION)
    }

    private fun Project.registerTasks() {
        val publishing = extensions.getByType<PublishingExtension>()
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

            val dumpMavenPublicationArtifacts by registering {
                group = PublishingPlugin.PUBLISH_TASK_GROUP
                description =
                    "Dumps the Maven publications and their artifacts for project ${project.name}"
                doLast {
                    logger.let {
                        it.lifecycle(
                            "Publication artifacts for project ${project.name}\n" +
                                "-".repeat(50)
                        )
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
    }

    private fun Project.registerVariantTasks() {
        val android = extensions.findByType<AGPLibraryExtension>()

        if (android == null) return // Skip creation if the AGP Library plugin is not applied

        // Add Javadoc Jar tasks
        tasks {
            val dokkatooGenerateModuleJavadoc by existing(DokkatooGenerateTask::class)
            val dokkatooGenerateModuleHtml by existing(DokkatooGenerateTask::class)

            android.libraryVariants.configureEach {
                register<Jar>(computeJavadocTaskName(name, isHtml = false)) {
                    description =
                        "Generates Javadocs for the ${this@configureEach.name} library variant"
                    dependsOn(dokkatooGenerateModuleJavadoc)
                    from(dokkatooGenerateModuleJavadoc.map { it.outputs })
                    archiveClassifier.set(DocsType.JAVADOC)
                }
                register<Jar>(computeJavadocTaskName(name, isHtml = true)) {
                    description =
                        "Generates Dokka HTML docs for the ${this@configureEach.name} library " +
                            "variant"
                    dependsOn(dokkatooGenerateModuleHtml)
                    from(dokkatooGenerateModuleHtml.map { it.outputs })
                    archiveClassifier.set("html-docs")
                }
            }
        }
    }

    private fun Project.registerKmpTasks() {
        val kmp = extensions.findByType<KotlinMultiplatformExtension>()

        if (kmp == null) return // Skip creation if the KMP plugin is not applied

        // Add Javadoc Jar tasks
        tasks {
            val dokkatooGenerateModuleJavadoc by existing(DokkatooGenerateTask::class)
            val dokkatooGenerateModuleHtml by existing(DokkatooGenerateTask::class)

            kmp.testableTargets.configureEach {
                register<Jar>(computeJavadocTaskName(name, isHtml = false)) {
                    group = BasePlugin.BUILD_GROUP
                    description =
                        "Assembles a jar archive containing the Javadocs for the ${this@configureEach.name} library variant"
                    dependsOn(dokkatooGenerateModuleJavadoc)
                    from(dokkatooGenerateModuleJavadoc.map { it.outputs })
                    archiveClassifier.set(DocsType.JAVADOC)
                }
                register<Jar>(computeJavadocTaskName(name, isHtml = true)) {
                    group = BasePlugin.BUILD_GROUP
                    description =
                        "Assembles a jar archive containing the Dokka HTML docs for the ${this@configureEach.name} library " +
                            "variant"
                    dependsOn(dokkatooGenerateModuleHtml)
                    from(dokkatooGenerateModuleHtml.map { it.outputs })
                    archiveClassifier.set("html-docs")
                }
            }
        }
    }

    private fun Project.applyPlugins(extension: LibraryPluginExtension) {
        plugins.apply {
            apply<SigningPlugin>()
            apply<PublishingPlugin>()
            apply<MavenPublishPlugin>()
            if (extension.docs.publishJavadoc.getOrElse(true)) {
                apply<DokkatooJavadocPlugin>()
            }
            if (extension.docs.publishHtmlDoc.getOrElse(true)) {
                apply<DokkatooHtmlPlugin>()
            }
            apply<BinaryCompatibilityValidatorPlugin>()
        }
    }

    private fun DokkaSourceSetSpec.configureSourceLink(
        project: Project,
        modulePath: Provider<String>
    ) {
        // Link to source
        sourceLink {
            localDirectory.set(project.file("src/${name}/kotlin"))
            remoteUrl(
                modulePath.map {
                    "$SOURCE_URL/tree/main/${it}/src/${name}/kotlin"
                }
            )
        }
    }

    private fun Property<LibraryType>.setConventions(project: Project) {
        with(project) {
            convention(
                when {
                    plugins.hasPlugin(AGPLibraryPlugin::class) ||
                        extensions.hasType<AGPLibraryExtension>() -> LibraryType.Android

                    plugins.hasPlugin(KotlinMultiplatformPluginWrapper::class) ||
                        extensions.hasType<KotlinMultiplatformExtension>() -> LibraryType.Multiplatform

                    plugins.hasPlugin(KotlinPluginWrapper::class) ||
                        extensions.hasType<KotlinJvmProjectExtension>() -> LibraryType.Jvm

                    else -> {
                        logger.warn(
                            "No supported plugin for ${project.name} was found, " +
                                "assuming JVM project as the library type"
                        )
                        LibraryType.Jvm
                    }
                }
            )
        }
        logger.info("Current library type for ${project.name}: $orNull")
    }

    private fun LibraryPluginExtension.setConventions(project: Project) {
        libraryType.setConventions(project)
        mavenCoordinates {
            groupId.convention(project.getLibraryGroupFromProjectPath())
            artifactId.convention(project.name)
            version.convention("0.0.1-SNAPSHOT")
        }
        isReleaseVersion.convention(DefaultReleaseVersionSpec)
        inceptionYear.convention(LibraryPluginExtension.CURRENT_YEAR_STRING)

        mavenPublishing {
            val usernameEnv = project.providers.environmentVariable("USERNAME")
            val tokenEnv = project.providers.environmentVariable("TOKEN")

            sonatypeStagingUrl.convention(URI(SonatypeStagingUrl))
            sonatypeSnapshotUrl.convention(URI(SonatypeSnapshotUrl))
            gitHubPackagesUrl.convention(URI(GitHubPackagesUrl))
            repositories {
                register("sonatype") {
                    snapshotUrl.set(sonatypeSnapshotUrl)
                    releaseUrl.set(sonatypeStagingUrl)
                    credentials {
                        username.convention(
                            project.providers.gradleProperty("sonatype.user")
                                .orElse(usernameEnv)
                        )
                        password.convention(
                            project.providers.gradleProperty("sonatype.key").orElse(
                                tokenEnv
                            )
                        )
                    }
                }

                register("gitHubPackages") {
                    url(gitHubPackagesUrl)
                    credentials {
                        username.convention(
                            project.providers.gradleProperty("gpr.user")
                                .orElse(usernameEnv)
                        )
                        password.convention(
                            project.providers.gradleProperty("gpr.key")
                                .orElse(tokenEnv)
                        )
                    }
                }
            }
        }

        docs {
            publishHtmlDoc.convention(true)
            publishJavadoc.convention(true)

            val isNotMultiplatform = libraryType.map { it != LibraryType.Multiplatform }
            onlyMainSourceLink.convention(isNotMultiplatform)
            suppressNonMain.convention(isNotMultiplatform)

            moduleDoc.convention(project.layout.projectDirectory.file("Module.md"))

            val moduleFile = moduleDoc.asFile.orNull
            // Only add the Module.md file if it exists
            if (moduleFile?.exists() == true) {
                moduleDocs.from(moduleDoc)
            } else logger.warn("The expected Module.md file at ${moduleDoc.asFile.get()} doesn't exist!")
        }

        compose {
            enabled.convention(false)
            kotlinCompilerExtensionVersion.convention(
                compilerVersion
            )
        }
    }

    private fun Project.applyLibraryExtension(extension: LibraryPluginExtension) {
        // Required as the DSL doesn't support lazy configuration
        afterEvaluate {
            version = extension.mavenCoordinates.version.get()
            group = extension.mavenCoordinates.groupId.get()
        }
    }

    private fun Project.applyExtensions(extension: LibraryPluginExtension) {
        // Publishing + signing
        val publishing = extensions.getByType<PublishingExtension>()
        extensions.getByType<SigningExtension>().setConventions(publishing)
        publishing.setConventions(project, extension)

        // androidComponents
        extensions.findByType<LibraryAndroidComponentsExtension>()?.setConventions(extension)

        // AGP library
        extensions.findByType<KotlinAndroidProjectExtension>()?.setConventions()
        extensions.findByType<AGPLibraryExtension>()?.setConventions()

        // Kotlin Multiplatform
        extensions.findByType<KotlinMultiplatformExtension>()?.apply {
            setConventions()
            configureKmpPublications(project)
        }
        // Kotlin/JVM
        extensions.findByType<KotlinJvmProjectExtension>()?.apply {
            setConventions()
        }

        // Dokkatoo
        extensions.findByType<DokkatooExtension>()?.setConventions(project, extension.docs)
    }

    private fun SigningExtension.setConventions(publishing: PublishingExtension) {
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

    private fun PublishingExtension.setConventions(
        project: Project,
        extension: LibraryPluginExtension
    ) {
        // afterEvaluate is required as the repositories DSL doesn't support lazy configuration
        project.afterEvaluate {
            repositories {
                extension.mavenPublishing.repositories.forEach {
                    maven {
                        name = it.capitalizedName
                        url = it.getUrl(
                            extension.mavenCoordinates.asReadOnlyProvider(project.providers),
                            extension.isReleaseVersion
                        ).get()
                        credentials {
                            username = it.credentials.username.orNull
                            password = it.credentials.password.orNull
                        }

                    }
                }
            }

            // Skip on other platforms
            // TODO: Revisit code - is this needed?
            publications {
                val type = extension.libraryType.get()
                if (type == LibraryType.Android) {
                    try {
                        registerVariantPublication("release", project, extension)
                    } catch (e: Exception) {
                        logger.lifecycle(
                            "Release publication container already exists, " +
                                "skipping registration"
                        )
                    }
                } else if (type == LibraryType.Multiplatform) {
                    logger.info("Adding POM metadata to all KMP publications")
                    setKmpConventions(project, extension)
                }
            }
        }
    }

    private fun KotlinMultiplatformExtension.configureKmpPublications(
        project: Project
    ) {
        project.afterEvaluate {
            testableTargets.configureEach {
                mavenPublication {
                    // Add Javadocs as an artifact
                    artifact(tasks[computeJavadocTaskName(this@configureEach.name, isHtml = false)])
                    artifact(tasks[computeJavadocTaskName(this@configureEach.name, isHtml = true)])
                }
            }
        }
    }

    private fun PublishingExtension.setKmpConventions(
        project: Project,
        extension: LibraryPluginExtension
    ) {
        logger.info("Adding POM metadata to all KMP publications")
        publications {
            // Add POM metadata to all KMP publications
            withType<MavenPublication>().configureEach {
                pom.setConventions(project, extension)
            }
        }
    }

    private fun LibraryAndroidComponentsExtension.setConventions(
        extension: LibraryPluginExtension
    ) {
        logger.info("Setting conventions for androidComponents extension")
        finalizeDsl {
            val composeEnabled = extension.compose.enabled.get()
            val kotlinCompilerExtensionVersion =
                extension.compose.kotlinCompilerExtensionVersion.get()
            logger.info(
                "Compose config:\nEnabled: $composeEnabled, " +
                    "Kotlin compiler version: $kotlinCompilerExtensionVersion"
            )
            it.buildFeatures.compose = composeEnabled
            it.composeOptions.kotlinCompilerExtensionVersion =
                kotlinCompilerExtensionVersion
        }
    }

    private fun AGPLibraryExtension.setConventions() {
        logger.info("Setting conventions for AGP library extension")


        compileSdk = 33

        defaultConfig {
            minSdk = 21
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        publishing {
            singleVariant("release") {
                withSourcesJar()
                // TODO: Uncomment when Dokkatoo is merged into Dokka
//            withJavadocJar()
            }
        }
    }

    private fun KotlinTopLevelExtension.setConventions() {
        jvmToolchain(11)
        explicitApi()
    }

    private fun DokkatooExtension.setConventions(
        project: Project,
        extension: LibraryDocsExtension
    ) {
        dokkatooSourceSets {
            if (extension.onlyMainSourceLink.getOrElse(true)) {
                maybeCreate("main").apply {
                    configureSourceLink(project, modulePath)
                }
            } else {
                configureEach {
                    configureSourceLink(project, modulePath)
                }
            }

            if (extension.suppressNonMain.getOrElse(true)) {
                configureEach {
                    suppress.set(true)
                }
                named("main") {
                    suppress.set(false)
                }
            }

            configureEach {
                externalDocumentationLinks {
                    // Add common doc links
                    register(ExternalDocLinks.Kotlinx.coroutines) {
                        url("https://kotlinlang.org/api/kotlinx.coroutines")
                    }

                    register(ExternalDocLinks.Kotlinx.serialization) {
                        url("https://kotlinlang.org/api/kotlinx.serialization")
                        enabled.convention(false)
                    }

                    register(ExternalDocLinks.Kotlinx.dateTime) {
                        url("https://kotlinlang.org/api/kotlinx-datetime")
                        packageListUrl("https://kotlinlang.org/api/kotlinx-datetime/kotlinx-datetime/package-list")
                        enabled.convention(false)
                    }

                    register(ExternalDocLinks.ktor) {
                        url("https://api.ktor.io")
                        enabled.convention(false)
                    }
                }

                includes.from(extension.moduleDocs)

                reportUndocumented.convention(true)
            }
        }
    }

    private fun PublicationContainer.registerVariantPublication(
        variantName: String,
        project: Project, extension: LibraryPluginExtension
    ) {
        register<MavenPublication>(variantName) {
            pom.setConventions(project, extension)
            project.afterEvaluate {
                from(components[variantName])

                // Add Javadocs as an artifact
                artifact(tasks[computeJavadocTaskName(variantName, isHtml = false)])
                artifact(tasks[computeJavadocTaskName(variantName, isHtml = true)])
            }
        }
    }

    private fun Project.getLibraryGroupFromProjectPath(): String {
        return "${LibraryPluginExtension.MAVEN_GROUP_ID_PREFIX}.${
            path.substringBeforeLastColon().drop(1).replace(':', '.')
        }"
    }

    companion object {
        const val DEFAULT_KOTLIN_COMPOSE_EXTENSION_VERSION = "1.5.3"
        private val logger = Logging.getLogger(LibraryPlugin::class.java)
    }
}

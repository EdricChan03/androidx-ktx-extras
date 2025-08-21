package io.github.edricchan03.plugin.library

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import io.github.edricchan03.plugin.library.extensions.LibraryPluginExtension
import io.github.edricchan03.plugin.library.extensions.LibraryType
import io.github.edricchan03.plugin.library.extensions.docs.ExternalDocLinks
import io.github.edricchan03.plugin.library.extensions.docs.LibraryDocsExtension
import io.github.edricchan03.plugin.library.extensions.publish.DefaultReleaseVersionSpec
import io.github.edricchan03.plugin.library.extensions.publish.asReadOnlyProvider
import io.github.edricchan03.plugin.library.extensions.publish.maven.LibraryMavenPublishingExtension
import io.github.edricchan03.plugin.library.tasks.EmptyJavadocJarTask
import io.github.edricchan03.publishing.computeJavadocTaskName
import kotlinx.validation.BinaryCompatibilityValidatorPlugin
import nmcp.NmcpExtension
import nmcp.NmcpPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.attributes.DocsType
import org.gradle.api.logging.Logging
import org.gradle.api.plugins.BasePlugin
import org.gradle.api.plugins.JavaPluginExtension
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
import org.gradle.kotlin.dsl.maybeCreate
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.registering
import org.gradle.kotlin.dsl.withType
import org.gradle.plugins.signing.SigningExtension
import org.gradle.plugins.signing.SigningPlugin
import org.jetbrains.dokka.gradle.DokkaExtension
import org.jetbrains.dokka.gradle.engine.parameters.DokkaSourceSetSpec
import org.jetbrains.dokka.gradle.engine.plugins.DokkaHtmlPluginParameters
import org.jetbrains.dokka.gradle.formats.DokkaHtmlPlugin
import org.jetbrains.dokka.gradle.formats.DokkaJavadocPlugin
import org.jetbrains.dokka.gradle.tasks.DokkaGenerateModuleTask
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import java.net.URI
import com.android.build.gradle.LibraryExtension as AGPLibraryExtension
import com.android.build.gradle.LibraryPlugin as AGPLibraryPlugin
import io.github.edricchan03.plugin.library.extensions.publish.gitHubPackagesUrl as GitHubPackagesUrl
import io.github.edricchan03.plugin.library.extensions.publish.sonatypeSnapshotUrl as SonatypeSnapshotUrl
import io.github.edricchan03.plugin.library.extensions.publish.sonatypeStagingUrl as SonatypeStagingUrl

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
            registerTasks()
            registerVariantTasks()
            registerKmpTasks()
            registerJvmTasks()
            applyExtensions(extension)
        }
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
            val dokkaGenerateModuleJavadoc by existing(DokkaGenerateModuleTask::class)
            val dokkaGenerateModuleHtml by existing(DokkaGenerateModuleTask::class)

            android.libraryVariants.configureEach {
                register<Jar>(computeJavadocTaskName(name, isHtml = false)) {
                    description =
                        "Generates Javadocs for the ${this@configureEach.name} library variant"
                    dependsOn(dokkaGenerateModuleJavadoc)
                    from(dokkaGenerateModuleJavadoc.map { it.outputs })
                    archiveClassifier.set(DocsType.JAVADOC)
                }
                register<Jar>(computeJavadocTaskName(name, isHtml = true)) {
                    description =
                        "Generates Dokka HTML docs for the ${this@configureEach.name} library " +
                            "variant"
                    dependsOn(dokkaGenerateModuleHtml)
                    from(dokkaGenerateModuleHtml.map { it.outputs })
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
            val dokkaGenerateModuleHtml by existing(DokkaGenerateModuleTask::class)

            kmp.targets.configureEach {
                register<Jar>(computeJavadocTaskName(name, isHtml = true)) {
                    group = BasePlugin.BUILD_GROUP
                    description =
                        "Assembles a jar archive containing the Dokka HTML docs for the ${this@configureEach.name} library " +
                            "variant"
                    dependsOn(dokkaGenerateModuleHtml)
                    from(dokkaGenerateModuleHtml.map { it.outputs })
                    archiveClassifier.set("html-docs")
                    archiveAppendix.set(targetName)
                }
            }
        }
    }

    private fun Project.registerJvmTasks() {
        val jvm = extensions.findByType<KotlinJvmProjectExtension>()

        if (jvm == null) return // Skip creation if the Kotlin/JVM plugin is not applied

        // Add Javadoc Jar tasks
        tasks {
            val dokkaGenerateModuleJavadoc by existing(DokkaGenerateModuleTask::class)
            val dokkaGenerateModuleHtml by existing(DokkaGenerateModuleTask::class)

            register<Jar>(computeJavadocTaskName(isHtml = false)) {
                group = BasePlugin.BUILD_GROUP
                description =
                    "Assembles a jar archive containing the Javadocs for this library"
                dependsOn(dokkaGenerateModuleJavadoc)
                from(dokkaGenerateModuleJavadoc.map { it.outputs })
                archiveClassifier.set(DocsType.JAVADOC)
            }
            register<Jar>(computeJavadocTaskName(isHtml = true)) {
                group = BasePlugin.BUILD_GROUP
                description =
                    "Assembles a jar archive containing the Dokka HTML docs for this library"
                dependsOn(dokkaGenerateModuleHtml)
                from(dokkaGenerateModuleHtml.map { it.outputs })
                archiveClassifier.set("html-docs")
            }
        }
    }

    private fun Project.applyPlugins(extension: LibraryPluginExtension) {
        plugins.apply {
            apply<SigningPlugin>()
            apply<PublishingPlugin>()
            apply<MavenPublishPlugin>()
            if (extension.docs.publishJavadoc.getOrElse(true)) {
                apply<DokkaJavadocPlugin>()
            }
            if (extension.docs.publishHtmlDoc.getOrElse(true)) {
                apply<DokkaHtmlPlugin>()
            }
            apply<BinaryCompatibilityValidatorPlugin>()
            apply<NmcpPlugin>()
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
                    // Order matters; KMP libraries may also have the AGP library plugin applied
                    plugins.hasPlugin(KotlinMultiplatformPluginWrapper::class) ||
                        extensions.hasType<KotlinMultiplatformExtension>() -> LibraryType.Multiplatform

                    plugins.hasPlugin(AGPLibraryPlugin::class) ||
                        extensions.hasType<AGPLibraryExtension>() -> LibraryType.Android

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
            version.convention("0.1.0-SNAPSHOT")
        }
        releaseVersionSpec.convention(DefaultReleaseVersionSpec)
        inceptionYear.convention(LibraryPluginExtension.CURRENT_YEAR_STRING)

        mavenPublishing {
            val usernameEnv = project.providers.environmentVariable("USERNAME")
            val tokenEnv = project.providers.environmentVariable("TOKEN")

            sonatypeStagingUrl.convention(URI(SonatypeStagingUrl))
            sonatypeSnapshotUrl.convention(URI(SonatypeSnapshotUrl))
            gitHubPackagesUrl.convention(URI(GitHubPackagesUrl))
            jvmPublicationName.convention(LibraryMavenPublishingExtension.DEFAULT_JVM_PUBLICATION_NAME)
            skipJvmPublication.convention(LibraryMavenPublishingExtension.DEFAULT_SKIP_JVM_PUBLICATION)
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
            val isNotMultiplatform = libraryType.map { it != LibraryType.Multiplatform }

            publishHtmlDoc.convention(true)
            publishJavadoc.convention(isNotMultiplatform)

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
            setKmpConventions(project)
        }

        // Kotlin/JVM
        extensions.findByType<KotlinJvmProjectExtension>()?.setConventions()
        // Java Libraries
        extensions.findByType<JavaPluginExtension>()?.apply {
            withSourcesJar()
        }

        // Dokka
        extensions.findByType<DokkaExtension>()?.setConventions(project, extension.docs)

        // nmcp
        extensions.findByType<NmcpExtension>()?.setConventions(project)
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
                            extension.releaseVersionSpec
                        ).get()
                        credentials {
                            username = it.credentials.username.orNull
                            password = it.credentials.password.orNull
                        }

                    }
                }
            }

            val type = extension.libraryType.getOrElse(LibraryType.Jvm)
            @Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
            when (type) {
                LibraryType.Android -> {
                    try {
                        publications.registerVariantPublication("release", project, extension)
                    } catch (e: Exception) {
                        logger.lifecycle(
                            "Release publication container already exists, " +
                                "skipping registration"
                        )
                    }
                }

                LibraryType.Multiplatform -> {
                    logger.info("Adding POM metadata to all KMP publications")
                    setKmpConventions(project, extension)
                }

                LibraryType.Jvm -> {
                    logger.info("Setting up POM conventions for JVM library")
                    setJvmConventions(project, extension)
                }
            }
        }
    }

    private fun KotlinMultiplatformExtension.setKmpConventions(
        project: Project
    ) {
        // Maven Central requires a Javadoc jar to be supplied, which isn't really
        // a thing for Multiplatform libraries - so use an empty jar file
        targets.configureEach {
            withSourcesJar()

            val javadocJarTaskName = "emptyJavadoc${
                targetName.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase() else it.toString()
                }
            }Jar"
            logger.info("Registering empty javadoc jar for $name with name $javadocJarTaskName")
            val emptyJavadocJar =
                project.tasks.register<EmptyJavadocJarTask>(name = javadocJarTaskName) {
                    archiveAppendix.set(targetName)
                }
            mavenPublication {
                artifact(emptyJavadocJar)
                // Dokka docs are still safe for publishing
                artifact(
                    project.tasks[computeJavadocTaskName(
                        variantName = targetName,
                        isHtml = true
                    )]
                )
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

    private fun PublishingExtension.setJvmConventions(
        project: Project,
        extension: LibraryPluginExtension
    ) {
        if (extension.mavenPublishing.skipJvmPublication.getOrElse(false)) {
            logger.info("Skipping JVM publication setup for ${project.name}")
            return
        }

        logger.info("Setting up default JVM publication for ${project.name}")

        publications {
            maybeCreate(
                extension.mavenPublishing.jvmPublicationName.get(),
                MavenPublication::class
            ).apply {
                pom.setConventions(project, extension)

                extension.mavenPublishing.jvmPublicationComponent.ifPresentOrElse(
                    ifPresent = { from(it) },
                    ifNotPresent = { from(project.components["java"]) }
                )

                // Add Javadocs as an artifact
                artifact(project.tasks[computeJavadocTaskName(isHtml = false)])
                artifact(project.tasks[computeJavadocTaskName(isHtml = true)])
            }
        }
    }

    private fun LibraryAndroidComponentsExtension.setConventions(
        extension: LibraryPluginExtension
    ) {
        logger.info("Setting conventions for androidComponents extension")
        finalizeDsl {
            val composeEnabled = extension.compose.enabled.get()
            logger.info("Compose config:\nEnabled: $composeEnabled")
            it.buildFeatures.compose = composeEnabled
        }
    }

    private fun AGPLibraryExtension.setConventions() {
        logger.info("Setting conventions for AGP library extension")

        compileSdk = 36

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

    private fun KotlinBaseExtension.setConventions() {
        jvmToolchain(11)
        explicitApi()
    }

    private fun DokkaExtension.setConventions(
        project: Project,
        extension: LibraryDocsExtension
    ) {
        dokkaSourceSets {
            // TODO: Remove workaround when https://github.com/Kotlin/dokka/issues/3701
            //  is resolved by that upstream bug
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

        dokkaPublications.configureEach {
            pluginsConfiguration {
                val html by existing(DokkaHtmlPluginParameters::class) {
                    footerMessage.convention(
                        "&copy; 2023-2025 Edric Chan. androidx-ktx-extras is licensed under the " +
                            "<a href=\"https://github.com/EdricChan03/androidx-ktx-extras/blob/main/LICENSE\" class=\"footer--link footer--link_external\">" +
                            "GNU GPL 3.0</a>. AndroidX is licensed under the " +
                            "<a href=\"https://github.com/androidx/androidx/blob/androidx-main/LICENSE.txt\" class=\"footer--link footer--link_external\">" +
                            "Apache License 2.0</a>."
                    )
                    homepageLink.convention("https://github.com/EdricChan03/androidx-ktx-extras")
                    separateInheritedMembers.convention(true)
                }
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

    private fun NmcpExtension.setConventions(
        project: Project
    ) {
        publishAllPublicationsToCentralPortal {
            val usernameEnv = project.providers.environmentVariable("USERNAME")
            val tokenEnv = project.providers.environmentVariable("TOKEN")

            username.set(usernameEnv.orElse(project.providers.gradleProperty("central.name")))
            password.set(tokenEnv.orElse(project.providers.gradleProperty("central.token")))

            publishingType.set("USER_MANAGED")
        }
    }

    private fun Project.getLibraryGroupFromProjectPath(): String {
        return "${LibraryPluginExtension.MAVEN_GROUP_ID_PREFIX}.${
            path.substringBeforeLastColon().drop(1).replace(':', '.')
        }"
    }

    companion object {
        private val logger = Logging.getLogger(LibraryPlugin::class.java)
    }
}

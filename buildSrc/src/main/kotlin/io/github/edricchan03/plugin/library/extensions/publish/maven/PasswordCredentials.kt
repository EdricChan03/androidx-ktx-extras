package io.github.edricchan03.plugin.library.extensions.publish.maven

import org.gradle.api.artifacts.repositories.PasswordCredentials
import org.gradle.api.provider.Property

/**
 * Password credentials for a Maven repository.
 *
 * This variant of [PasswordCredentials] allows for [properties][Property] to be passed
 * instead of JavaBean getters/setters, allowing for lazy configuration.
 */
abstract class PasswordCredentials {
    /**
     * The username for this Maven repository.
     * @see PasswordCredentials.getUsername
     * @see PasswordCredentials.setUsername
     */
    abstract val username: Property<String>

    /**
     * The password for this Maven repository.
     * @see PasswordCredentials.getPassword
     * @see PasswordCredentials.setPassword
     */
    abstract val password: Property<String>
}

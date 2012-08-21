package org.torpidity.core

import static org.junit.Assert.*

import org.codehaus.groovy.grails.plugins.codecs.SHA1Codec

import grails.test.GrailsUnitTestCase
import org.junit.*

/**
 * Test the {@link User} domain class
 */
class UserTests extends GrailsUnitTestCase {
    def userService

    void setUp() {
        // provide access to the user service
        userService = new UserService()

        // codecs are not loaded by default into unit tests
        super.setUp()
        loadCodec(SHA1Codec)
    }

    void testValidationFailsIfAliasIsTooShort() {
        // set up a user with an alias that is too short
        def aliasTestUser = new User(
            alias: "what",
            email: "user@home.com",
            passwordSalt: "actually is pepper",
            passwordHash: "actually is kif"
            // apologies for the dolan jokes
        )

        // mock the domain class
        mockForConstraintsTests(User, [aliasTestUser])

        // test for failure
        assertFalse(aliasTestUser.validate())
        assert(aliasTestUser.hasErrors())
        aliasTestUser.clearErrors()

        // fix the alias length (more dolan)
        aliasTestUser.alias = "gooby pls"

        // test for success
        assert(aliasTestUser.validate())
    }

    void testValidationFailsForInvalidEmail() {
        // set up a user with an invalid email address
        def email = "i don't want to share my email with the likes of you"
        def passwordSalt = "salty goodness"
        def passwordHash = userService.hashPassword(email, "changeme",
            passwordSalt)
        def emailTestUser = new User(
            alias: "emailh8r",
            email: email,
            passwordSalt: passwordSalt,
            passwordHash: passwordHash
        )

        // mock the domain class
        mockForConstraintsTests(User, [emailTestUser])

        // test for failure
        assertFalse(emailTestUser.validate())
        assert(emailTestUser.hasErrors())
        emailTestUser.clearErrors()

        // fix the alias length (more dolan)
        emailTestUser.email = "emailh8r@mailinator.com"

        // test for success
        assert(emailTestUser.validate())
    }

    void testValidationFailsForInvalidPassword() {
        // set up a working user account
        def changePasswordTestUser = new User(
            alias: "cooperative user",
            email: "admin@torpidity.org"
        )
        // mock the domain class
        mockForConstraintsTests(User, [changePasswordTestUser])

        // test for failure
        assertFalse(changePasswordTestUser.validate())
        assert(changePasswordTestUser.hasErrors())
        changePasswordTestUser.clearErrors()

        // assign the user's password (thanks XKCD 936)
        userService.changePassword(changePasswordTestUser,
            "correct horse battery staple")

        // test for success
        assert(changePasswordTestUser.validate())
    }
}

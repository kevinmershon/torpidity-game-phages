package org.torpidity.core

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * Test the {@link User} domain class
 */
@TestMixin(GrailsUnitTestMixin)
class UserTests {

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
}

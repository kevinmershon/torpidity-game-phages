package org.torpidity.core

import static org.junit.Assert.*

import org.codehaus.groovy.grails.plugins.codecs.SHA1Codec

import groovy.util.GroovyTestCase
import org.junit.*

/**
 * Test the {@link UserController} class
 */
class UserControllerTests extends GroovyTestCase {
    def controller
    def requestMap

    // injected
    def messageSource

    void setUp() {
        controller = new UserController()
        controller.request.contentType = "text/json"
        controller.request.method = "POST"

        requestMap = [
            email: "admin@torpidity.org",
            password: "ALSO, THE GAME"
        ]
    }

    void testDoLoginFailsWithNoUsers() {
        // verify no users exist
        assert(User.list().isEmpty())

        // login
        controller.params.putAll(requestMap)
        controller.doLogin()

        // test for failure
        if (!controller.flash.loginFailed)
            fail("User was able to login when no users exist in the database!")
    }

    void testDoLoginFailsWithInvalidCredentials() {
        // create the admin user
        DummyUsers.reset()
        DummyUsers.setUp()

        // verify that only the admin user exists
        assert(User.list().size() == 1)

        // login
        controller.params.putAll([
            email: "bad email address",
            password: "bad password"
        ])
        controller.doLogin()

        // test for failure
        if (!controller.flash.loginFailed)
            fail("User was able to login with bad email/password combination!")
    }

    void testDoLoginFailsWhenUserIsDisabled() {
        // create the admin user
        DummyUsers.reset()
        DummyUsers.setUp()

        // verify that only the admin user exists
        assert(User.list().size() == 1)

        // disable the user
        def admin = User.findByAlias("admin")
        admin.isDisabled = true
        admin.save(flush: true, failOnError: true)
        admin.refresh()

        // login
        controller.params.putAll(requestMap)
        controller.doLogin()

        // test for failure
        if (!controller.flash.loginFailed)
            fail("User was able to login with bad email/password combination!")
    }

}

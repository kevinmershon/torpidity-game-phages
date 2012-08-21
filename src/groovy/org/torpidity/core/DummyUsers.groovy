package org.torpidity.core

class DummyUsers {
    private static boolean isSetUp = false

    static def reset() {
        isSetUp = false
    }

    static def setUp() {
        if (isSetUp)
            return

        def userService = new UserService()

        // Admin user
        def admin = new User(
            alias: "admin",
            email: "admin@torpidity.org"
        )
        userService.changePassword(admin, "ALSO, THE GAME")
        admin.save(flush: true, failOnError: true)

        // prevent redundant definitions in testing
        isSetUp = true
    }
}

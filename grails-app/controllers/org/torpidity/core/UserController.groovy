package org.torpidity.core

class UserController {

    def scope = "singleton"

    // injected
    def userService

    /**
     * Change the password for the logged-in user
     */
    def changePassword(oldPassword, newPassword) {
        def user = session.user

        // Verify the old password
        def oldPasswordHash = userService.hashPassword(user.email, oldPassword,
            user.passwordSalt)
        if (oldPasswordHash != user.passwordHash) {
            flash.passwordChangeFailed = true
        } else {
            // Change the password
            userService.changePassword(user, newPassword)
            user.save(flush: true, failOnError: true)
        }
    }

    /**
     * Show the login page
     */
    def login() {
        // TODO -- Redirect to the logged in page
        if (session.user) {
            // go somewhere else
            //redirect(controller: "afterLoginController", action: "action")
        }
    }

    /**
     * Try to login with the specified email and password
     */
    def doLogin() {
        // Find the user with the specified email
        def user = User.findByEmail(params["username"])

        // Validate the password
        def hashedPassword = (params["password"] + user.passwordSalt +
            params["email"]).encodeAsSHA1()

        if (hashedPassword == user.passwordHash) {
            // Redirect to the logged in page
            session.user = user
            redirect(action: "login")
        } else {
            // Redirect back to the login screen, with an error message
            flash.loginFailed = true
            redirect(controller: "user", action: "login")
        }
    }

    /**
     * Destroy the user's session and redirect to the login page
     */
    def logout() {
        session.invalidate()
        redirect(controller: "user", action: "login")
    }
}

package org.torpidity.core

class AccessFilters {

    def filters = {
        // Require that the user is logged in for all pages (except the login
        // page, naturally)
        loginCheck(controller: "user", action: "login|doLogin", invert: true) {
            before = {
                if (!session.user) {
                    redirect(controller: "user", action: "login")
                    return false
                }
            }
        }
    }
}

package org.torpidity.core

import java.util.UUID

class User {
    String alias
    String email
    String passwordHash
    String passwordSalt

    static constraints = {
        alias(minSize: 5, blank: false, unique: true)
        email(email: true, blank: false, unique: true)
        passwordSalt(blank: false)
        passwordHash(blank: false)
    }

    static hasMany = [
        hosts: Host,
        pathogens: Pathogen,
        phages: Phage
    ]

    static mappedBy = [
        hosts: "owner",
        pathogens: "creator",
        phages: "creator"
    ]

    /**
     * Generate a new password hash and salt
     */
    def changePassword(newPassword) {
        this.passwordSalt = UUID.randomUUID().toString()
        this.passwordHash = (newPassword + this.passwordSalt +
            this.email).encodeAsSHA1()
    }

    String toString() {
        alias
    }
}

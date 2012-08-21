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

    String toString() {
        alias
    }
}

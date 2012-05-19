package org.torpidity.core

class User {
    String alias
    String email
    String passwordHash

    static constraints = {
        alias(minSize: 5, blank: false, unique: true)
        email(email: true, blank: false, unique: true)
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
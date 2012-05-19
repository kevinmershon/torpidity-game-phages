package org.torpidity.core

class Host {
    String name
    User owner
    Cluster cluster

    static constraints = {
        name(unique: ["owner"])
        cluster(nullable: true)
    }

    static hasMany = [
        pathogens: Pathogen,
        phages: Phage
    ]

    String toString() {
        "${name} (${owner.alias})"
    }
}

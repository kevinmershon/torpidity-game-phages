package org.torpidity.core

class Cluster {

    static constraints = {
    }

    static hasMany = [
        hosts: Host
    ]

    static mappedBy = [
        hosts: "cluster"
    ]

    String toString() {
        "Cluster [${hosts.join(', ')}]"
    }
}

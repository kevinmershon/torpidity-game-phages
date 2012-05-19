package org.torpidity.core

class Phage {
    String name
    User creator

    static constraints = {
        name(unique: true, blank: false)
    }

}
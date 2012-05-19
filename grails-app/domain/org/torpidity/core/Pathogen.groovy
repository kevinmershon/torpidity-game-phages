package org.torpidity.core

class Pathogen {
    String name
    User creator

    static constraints = {
        name(unique: true, blank: false)
    }

}
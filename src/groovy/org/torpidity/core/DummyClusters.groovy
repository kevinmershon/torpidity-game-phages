package org.torpidity.core

class DummyClusters {
    private static boolean isSetUp = false

    static def reset() {
        isSetUp = false
    }

    static def setUp() {
        if (isSetUp)
            return

        DummyUsers.setUp()
        def admin = User.findByAlias("admin")

        // Cluster 1: Ramsgate servers
        def ramsgate = new Cluster()
        def web         = new Host(name: "Web Server")
        def prodDb      = new Host(name: "Production Database")
        def nfs         = new Host(name: "NFS Server")
        def workstation = new Host(name: "admin's workstation")
        [web, prodDb, nfs, workstation].each {
            admin.addToHosts(it)
            ramsgate.addToHosts(it)
        }
        ramsgate.save(flush: true, failOnError: true)

        // Cluster 2: Home devices
        def home = new Cluster()
        def vmHost      = new Host(name: "VM Host")
        def gitVm       = new Host(name: "Git")
        def torrentVm   = new Host(name: "Torrents")
        def htpc        = new Host(name: "HTPC")
        def iPad        = new Host(name: "admin's iPad")
        def laptop1     = new Host(name: "Precision M4300")
        def laptop2     = new Host(name: "MacBook Pro")
        [vmHost, gitVm, torrentVm, htpc, iPad, laptop1, laptop2].each {
            admin.addToHosts(it)
            home.addToHosts(it)
        }
        home.save(flush: true, failOnError: true)

        // prevent redundant definitions in testing
        isSetUp = true
    }
}

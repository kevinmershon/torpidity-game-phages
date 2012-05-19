import grails.util.GrailsUtil

import org.torpidity.core.Cluster
import org.torpidity.core.Host
import org.torpidity.core.User

class BootStrap {

    def init = { servletContext ->
        switch (GrailsUtil.environment) {
            case "development":
                setUpDevelopment()
                break
            default:
                break
        }
    }

    def destroy = {
    }

    void setUpDevelopment() {
        // Admin user
        def admin = new User(alias: "admin", email: "admin@torpidity.org", passwordHash: "NO PASSWORD, ALSO THE GAME")
        admin.save(flush: true, failOnError: true)

        // Cluster 1: Ramsgate servers
        def ramsgate = new Cluster()
        def web = new Host(cluster: ramsgate, owner: admin, name: "Web Server")
        def prodDb = new Host(cluster: ramsgate, owner: admin, name: "Production Database")
        def nfs = new Host(cluster: ramsgate, owner: admin, name: "NFS Server")
        def workstation = new Host(cluster: ramsgate, owner: admin, name: "admin's workstation")
        [ramsgate, web, prodDb, nfs, workstation]*.save(flush: true, failOnError: true)

        // Cluster 2: Home devices
        def home = new Cluster()
        def vmHost = new Host(cluster: home, owner: admin, name: "VM Host")
        def gitVm = new Host(cluster: home, owner: admin, name: "Git")
        def torrentVm = new Host(cluster: home, owner: admin, name: "Torrents")
        def htpc = new Host(cluster: home, owner: admin, name: "HTPC")
        def iPad = new Host(cluster: home, owner: admin, name: "admin's iPad")
        def laptop1 = new Host(cluster: home, owner: admin, name: "Precision M4300")
        def laptop2 = new Host(cluster: home, owner: admin, name: "MacBook Pro")
        [home, vmHost, gitVm, torrentVm, htpc, iPad, laptop1, laptop2]*.save(flush: true, failOnError: true)
    }
}

import grails.util.GrailsUtil

import org.torpidity.core.DummyClusters
import org.torpidity.core.DummyUsers
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
        DummyUsers.setUp()
        DummyClusters.setUp()
    }
}

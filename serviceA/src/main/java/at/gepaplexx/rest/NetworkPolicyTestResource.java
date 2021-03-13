package at.gepaplexx.rest;

import at.gepaplexx.rest.clients.ServiceA2Client;
import at.gepaplexx.rest.clients.ServiceBClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static at.gepaplexx.util.Constants.FAILURE;
import static at.gepaplexx.util.Constants.SUCCESS;

@Path("/nwPolicyTest")
public class NetworkPolicyTestResource {

    private static final Logger LOG = Logger.getLogger(NetworkPolicyTestResource.class);

    @Inject
    ServiceA2Client serviceA2;

    @Inject
    ServiceBClient serviceB;

    @GET
    @Path("/reachable")
    @Produces(MediaType.TEXT_PLAIN)
    public String reachable() {
        LOG.debug("NetworkPolicyTestResource#reachable");
        return SUCCESS;
    }

    @GET
    @Path("/callB")
    @Produces(MediaType.TEXT_PLAIN)
    public String callServiceB() {
        LOG.debug("NetworkPolicyTestResource#callServiceB()");
        try {
            LOG.debug("calling serviceB.reachable() ...");
            String answer = serviceB.reachable();
            LOG.debugf("answer from serviceB: %s", answer);
            return SUCCESS;
        } catch (Exception e) {
            LOG.debugf("not able to call service B from service A, reason: %s", e.getMessage());
            return FAILURE;
        }
    }

    @GET
    @Path("/callA2")
    @Produces(MediaType.TEXT_PLAIN)
    public String callServiceA2() {
        LOG.debug("NetworkPolicyTestResource#callServiceA2()");
        try {
            LOG.debug("calling serviceA2.reachable() ...");
            String answer = serviceA2.reachable();
            LOG.debugf("answer from serviceA2: %s", answer);
            return SUCCESS;
        } catch (Exception e) {
            LOG.debugf("not able to call service A2 from service A, reason: %s", e.getMessage());
            return FAILURE;
        }
    }

}

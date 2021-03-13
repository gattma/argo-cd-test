package at.gepaplexx.rest;

import at.gepaplexx.api.ServiceA;
import at.gepaplexx.rest.clients.ServiceA2Client;
import at.gepaplexx.rest.clients.ServiceBClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/serviceA")
public class ServiceAResource {

    private static final Logger LOG = Logger.getLogger(ServiceAResource.class);

    @Inject
    ServiceA serviceA;

    @Inject
    ServiceA2Client serviceA2;

    @Inject
    ServiceBClient serviceB;

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOG.info("ServiceAResource#hello()");
        return "Hello from Service A";
    }

    @GET
    @Path("/callA2")
    @Produces(MediaType.TEXT_PLAIN)
    public String callA2() {
        LOG.info("entering ServiceAResource#callA2()");
        LOG.info("calling serviceA2.hello()...");
        try {
            String answer = serviceA2.hello();
            LOG.infof("answer from serviceA2: %s", answer);
            return String.format("Service A -> Service A2, Answer from A2: %s", answer);
        } catch (Exception e) {
            LOG.errorf("not able to call service A2, reason: %s", e.getMessage());
            return String.format("not able to call service A2 from service A, reason: %s", e.getMessage());
        }
    }

    @GET
    @Path("/callA2Complex")
    @Produces(MediaType.TEXT_PLAIN)
    public String callA2Complex() {
        LOG.info("entering ServiceAResource#callA2Complex()");
        LOG.info("calling serviceA.callA2Complex()...");
        String answer = serviceA.callA2Complex();
        LOG.infof("answer from serviceA2: %s", answer);
        return String.format("Service A REST -> Service A -> Service A2, Answer from A2: %s", answer);

    }

    @GET
    @Path("/callB")
    @Produces(MediaType.TEXT_PLAIN)
    public String callB() {
        LOG.info("entering ServiceAResource#callB()");
        LOG.info("calling serviceB.hello()...");
        try {
            String answer = serviceB.hello();
            LOG.infof("answer from serviceB: %s", answer);
            return String.format("Service A -> Service B, Answer from B: %s", answer);
        } catch (Exception e) {
            LOG.errorf("not able to call service B, reason: %s", e.getMessage());
            return String.format("not able to call service B from service A, reason: %s", e.getMessage());
        }
    }

}
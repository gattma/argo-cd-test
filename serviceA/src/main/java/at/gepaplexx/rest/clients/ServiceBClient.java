package at.gepaplexx.rest.clients;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ServiceBClient {

    @GET
    @Path("/serviceB/hello")
    @Produces(MediaType.TEXT_PLAIN)
    String hello();

    @GET
    @Path("/nwPolicyTest/reachable")
    @Produces(MediaType.TEXT_PLAIN)
    String reachable();

}

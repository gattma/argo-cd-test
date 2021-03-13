package at.gepaplexx.service;

import at.gepaplexx.rest.clients.ServiceA2Client;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class ServiceA implements at.gepaplexx.api.ServiceA {

    private static final Logger LOG = Logger.getLogger(ServiceA.class);

    @Inject
    ServiceA2Client serviceA2;


    @Override
    public String callA2Complex() {
        LOG.info("entering ServiceA#callA2Complex()");
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
}

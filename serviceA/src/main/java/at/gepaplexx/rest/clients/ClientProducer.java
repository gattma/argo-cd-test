package at.gepaplexx.rest.clients;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.net.MalformedURLException;
import java.net.URL;

@ApplicationScoped
public class ClientProducer {

    private static final Logger LOG = Logger.getLogger(ClientProducer.class);

    @ConfigProperty(name = "serviceB.baseURL", defaultValue = "http://localhost:8180")
    String baseURLServiceB;

    @ConfigProperty(name = "serviceA2.baseURL", defaultValue = "http://localhost:8280")
    String baseURLServiceA2;

    @Produces
    public ServiceBClient produceServiceBClient() throws MalformedURLException {
        LOG.infof("producing client for service B, baseURL: %s", baseURLServiceB);
        return RestClientBuilder.newBuilder()
                .baseUrl(new URL(baseURLServiceB))
                .build(ServiceBClient.class);
    }

    @Produces
    public ServiceA2Client produceServiceA2Client() throws MalformedURLException {
        LOG.infof("producing client for service A2, baseURL: %s", baseURLServiceA2);
        return RestClientBuilder.newBuilder()
                .baseUrl(new URL(baseURLServiceA2))
                .build(ServiceA2Client.class);
    }
}

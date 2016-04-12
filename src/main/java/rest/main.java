package rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;
import java.net.URISyntaxException;

public class main {

    public static void main(String[] args) throws URISyntaxException {
        ResourceConfig resourceConfig = new ResourceConfig(AcronymLookup.class);
        resourceConfig.register(JacksonFeature.class);
        JettyHttpContainerFactory.createServer(new URI("http://localhost:7779"), resourceConfig);
    }

}

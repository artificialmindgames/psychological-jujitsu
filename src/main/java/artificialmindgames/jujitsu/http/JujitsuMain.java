package artificialmindgames.jujitsu.http;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import artificialmindgames.jujitsu.rest.PlayerResource;


public class JujitsuMain {

	private static final Logger LOGGER = LoggerFactory.getLogger(JujitsuMain.class);
	
    public static void main(String[] args) throws Exception{
    	
    	String port = System.getenv("PORT");
    	if (port == null) {
    		port = "9998";
    	}
    	
    	LOGGER.info("starting HTTP server on port {}", port);
    	
    	URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        ResourceConfig config = new ResourceConfig(PlayerResource.class);
        config.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);

        server.start();
        Thread.currentThread().join();
    }
}

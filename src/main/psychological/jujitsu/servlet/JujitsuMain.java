package psychological.jujitsu.servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;

import psychological.jujitsu.setup.Setup;


public class JujitsuMain {

    public static void main(String[] args) throws Exception{
    	
    	String port = System.getenv("PORT");
    	if (port == null) {
    		port = "8080";
    	}
    	
        Server server = new Server(Integer.valueOf(port));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(Setup.createServlet()),"/*");
        server.start();
        server.join();
    }
}

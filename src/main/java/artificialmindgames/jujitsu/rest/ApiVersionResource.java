package artificialmindgames.jujitsu.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("api-version")
public class ApiVersionResource {

    /**
     * A small helper method to establish the version of the REST api.
     *
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ApiVersion getIt() {
        return new ApiVersion("Psychological Jujitsu API", "2013-12-21");
    }
}

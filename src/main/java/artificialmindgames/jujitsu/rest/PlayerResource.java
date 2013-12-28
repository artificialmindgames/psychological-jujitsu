package artificialmindgames.jujitsu.rest;

import java.util.Random;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import artificialmindgames.jujitsu.core.state.PlayState;

@Path("player")
public class PlayerResource {

	Random random = new Random();
	
    /**
     * Establish the version of the REST api, for diagnostic purposes only.
     */
    @GET
    @Path("api-version")
    @Produces(MediaType.APPLICATION_JSON)
    public ApiVersion getApiVersion() {
        return new ApiVersion("Psychological Jujitsu API", "2013-12-21");
    }
    
    @POST
    @Path("play")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String play(@Valid @NotNull PlayState playState) {
    	Integer[] cards = playState.getMyCardsRemaining();
    	if (cards.length == 0) {
    		return Integer.toString(0);
    	}
    	else {
    		return cards[random.nextInt(cards.length)].toString();
    	}
    }
    
}

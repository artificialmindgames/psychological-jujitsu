package artificialmindgames.jujitsu.core.player;

import artificialmindgames.jujitsu.core.state.PlayState;

/**
 * 
 * Implement this interface to create your own AIs.
 * 
 * @author aigames
 *
 */
public interface PlayerStrategy {

	/**
	 * 
	 * @return the bid card value for this turn. At the end of the game
	 * the return value does not matter (the starter bots return zero).
	 */
	int move(PlayState playState);
	
}

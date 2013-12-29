package artificialmindgames.jujitsu.core.player;

import artificialmindgames.jujitsu.core.state.PlayState;

/**
 * 
 * Wraps the player's strategy along with information about who the player is.
 * 
 * @author aigames
 *
 */
public interface Player {

	int move(PlayState playState);

	String nickname();
}

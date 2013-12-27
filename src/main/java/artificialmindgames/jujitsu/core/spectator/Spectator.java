package artificialmindgames.jujitsu.core.spectator;

import artificialmindgames.jujitsu.core.state.PlayState;

/**
 * 
 * Reports game state updates with player nickname visibility
 * 
 * @author aigames
 *
 */
public interface Spectator {

	void log(PlayState playState, String myNickname, String opponentNickname);
}

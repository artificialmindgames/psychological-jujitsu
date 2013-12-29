package artificialmindgames.jujitsu.core.player;

import artificialmindgames.jujitsu.core.state.PlayState;

public interface PlayerStrategy {

	int move(PlayState playState);
	
}

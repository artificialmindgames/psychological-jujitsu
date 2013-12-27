package artificialmindgames.jujitsu.core.player;

import artificialmindgames.jujitsu.core.state.PlayState;

public interface Player {

	int move(PlayState playState);

	String nickname();
}

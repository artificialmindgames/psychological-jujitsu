package artificialmindgames.jujitsu.core.player.starterbots;

import artificialmindgames.jujitsu.core.player.PlayerStrategy;
import artificialmindgames.jujitsu.core.state.PlayState;

public class DrawCardStarterBot implements PlayerStrategy {

	@Override
	public int move(PlayState playState) {
		Integer[] lot = playState.getLot();
		if (lot.length == 0) {
			return 0;
		}
		return lot[lot.length-1];
	}

}

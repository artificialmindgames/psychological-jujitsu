package artificialmindgames.jujitsu.core.player.starterbots;

import artificialmindgames.jujitsu.core.player.PlayerStrategy;
import artificialmindgames.jujitsu.core.state.PlayState;

/**
 * 
 * A very naive starter bot that always matches the victory card drawn
 * each turn. This is the last card in the lot array (drawn victory cards
 * are always added to the end of the lot array).
 * 
 * @author aigames
 *
 */
public class DrawCardStarterBot implements PlayerStrategy {

	@Override
	public int move(PlayState playState) {
		
    	if (playState.isGameEnded()) {
    		return 0;
    	}
		
		Integer[] lot = playState.getLot();
		return lot[lot.length-1];
	}

}

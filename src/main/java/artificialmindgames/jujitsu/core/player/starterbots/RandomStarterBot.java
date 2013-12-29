package artificialmindgames.jujitsu.core.player.starterbots;

import java.util.Random;

import artificialmindgames.jujitsu.core.player.PlayerStrategy;
import artificialmindgames.jujitsu.core.state.PlayState;

/**
 * 
 * A naive starter bot that just picks a random card every turn
 * 
 * @author aigames
 *
 */
public class RandomStarterBot implements PlayerStrategy {

	Random random = new Random();
	
	@Override
	public int move(PlayState playState) {
		
    	if (playState.isGameEnded()) {
    		return 0;
    	}
    	
    	Integer[] cards = playState.getMyCardsRemaining();
    	return cards[random.nextInt(cards.length)];
	}
}

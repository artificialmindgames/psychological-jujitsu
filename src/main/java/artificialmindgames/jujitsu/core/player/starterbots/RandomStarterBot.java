package artificialmindgames.jujitsu.core.player.starterbots;

import java.util.Random;

import artificialmindgames.jujitsu.core.player.PlayerStrategy;
import artificialmindgames.jujitsu.core.state.PlayState;

public class RandomStarterBot implements PlayerStrategy {

	Random random = new Random();
	
	@Override
	public int move(PlayState playState) {
		Integer[] cards = playState.getMyCardsRemaining();
    	if (cards.length == 0) {
    		return 0;
    	}
    	else {
    		return cards[random.nextInt(cards.length)];
    	}
	}
}

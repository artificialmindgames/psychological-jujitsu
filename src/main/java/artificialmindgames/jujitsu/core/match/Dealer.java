package artificialmindgames.jujitsu.core.match;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Encapsulates the random elements of the game; useful for testing
 * 
 * @author aigames
 *
 */
public class Dealer {

	private final Random random;
	
	public Dealer(Random random) {
		this.random = random;
	}
	
	/**
	 * 
	 * shuffles the cards. This function may modify the cards parameter or
	 * return a new object reference
	 * 
	 * @param cards to shuffle
	 * @return shuffled cards (may be same or different reference)
	 */
	public List<Integer> shuffle(List<Integer> cards) {
		Collections.shuffle(cards, random);
		return cards;
	}
	
	public int pickRandom(Integer[] cards) {
		if (cards.length == 0) {
    		return 0;
    	}
    	else {
    		return cards[random.nextInt(cards.length)];
    	}
	}
}

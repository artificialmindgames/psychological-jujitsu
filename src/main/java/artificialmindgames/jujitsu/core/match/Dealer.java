package artificialmindgames.jujitsu.core.match;

import java.util.ArrayList;
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
	 * provides a shuffled deck of cards valued 1..highestCardValue
	 *
	 */
	public List<Integer> createShuffledDeck(int highestCardValue) {
		List<Integer> deck = new ArrayList<>(highestCardValue+1);
		for (int i = 1; i <= highestCardValue; i++) {
			deck.add(i);
		}
		Collections.shuffle(deck, random);
		return deck;
	}

	public Integer pickRandom(List<Integer> cards) {
		if (cards.isEmpty()) {
    		return null;
    	}
    	else {
    		return cards.get(random.nextInt(cards.size()));
    	}
	}
	
	public Integer pickRandom(Integer[] cards) {
		if (cards.length == 0) {
    		return null;
    	}
    	else {
    		return cards[random.nextInt(cards.length)];
    	}
	}
}

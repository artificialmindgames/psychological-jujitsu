package artificialmindgames.jujitsu.core.match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Describes the state of the game, including information that should not be visible to players
 * 
 * @author aigames
 *
 */
public class InternalMatchState {

	private final Dealer dealer;
	private final Match match;
	private List<Integer> drawPile;
	
	private int currentRound = 0;
	
	private int highestValueCardThisRound = 0;
	
	private int scoreForPlayer1 = 0;
	private List<Integer> handPlayer1;
	private Integer timeRemainingForPlayer1 = null;
	
	private int scoreForPlayer2 = 0;
	private List<Integer> handPlayer2;
	private Integer timeRemainingForPlayer2 = null;
	
	private int wonCardsPosition = 0;
	private int nextVictoryCardIndex = 0;

	public InternalMatchState(Match match, Dealer dealer) {
		super();
		this.match = match;
		this.dealer = dealer;
		drawPile = new ArrayList<>(match.highestCardValueInMatch()+1);
		startNextRound();
	}

	public Match getMatch() {
		return match;
	}

	public int getCurrentRound() {
		return currentRound;
	}

	public int getHighestValueCardThisRound() {
		return highestValueCardThisRound;
	}
	
	public int getScoreForPlayer1() {
		return scoreForPlayer1;
	}

	public int getScoreForPlayer2() {
		return scoreForPlayer2;
	}
	
	public Integer getTimeRemainingForPlayer1() {
		return timeRemainingForPlayer1;
	}

	public void setTimeRemainingForPlayer1(Integer timeRemainingForPlayer1) {
		this.timeRemainingForPlayer1 = timeRemainingForPlayer1;
	}

	public Integer getTimeRemainingForPlayer2() {
		return timeRemainingForPlayer2;
	}

	public void setTimeRemainingForPlayer2(Integer timeRemainingForPlayer2) {
		this.timeRemainingForPlayer2 = timeRemainingForPlayer2;
	}

	public Integer[] getHandPlayer1() {
		return handPlayer1.toArray(new Integer[handPlayer1.size()]);
	}

	public Integer[] getHandPlayer2() {
		return handPlayer2.toArray(new Integer[handPlayer2.size()]);
	}

	public Integer[] getLot() {
		return drawPile.subList(wonCardsPosition, nextVictoryCardIndex).toArray(new Integer[nextVictoryCardIndex-wonCardsPosition]);
	}
	
	public Integer[] getRemainingDrawPile() {
		Integer[] remainingDrawPile = drawPile.subList(nextVictoryCardIndex, drawPile.size()).toArray(new Integer[drawPile.size()-nextVictoryCardIndex]);
		Arrays.sort(remainingDrawPile);
		return remainingDrawPile;
	}
	
	public Integer getNextVictoryCard() {		
		return nextVictoryCardIndex < drawPile.size() ? drawPile.get(nextVictoryCardIndex) : null;
	}

	public void startNextRound() {
		currentRound++;
		if (!drawPile.isEmpty()) {
			drawPile.clear();
		}		
		wonCardsPosition = 0;
		nextVictoryCardIndex = 1;
		highestValueCardThisRound = match.getRounds()[currentRound-1];
		for (int i = 1; i <= highestValueCardThisRound; i++) {
			drawPile.add(i);
		}
		handPlayer1 = new LinkedList<Integer>(drawPile);
		handPlayer2 = new LinkedList<Integer>(drawPile);
		drawPile = dealer.shuffle(drawPile);
	}
}

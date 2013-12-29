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
	private int currentTurn = 0;
	
	private int highestValueCardThisRound = 0;
	
	private int scoreForPlayer1 = 0;
	private List<Integer> handPlayer1;
	private Integer timeRemainingForPlayer1 = null;
	
	private int scoreForPlayer2 = 0;
	private List<Integer> handPlayer2;
	private Integer timeRemainingForPlayer2 = null;
	
	private int wonCardsPosition = 0;
	
	private List<InternalTurnState> previousTurns;
	private InternalTurnState currentTurnState;

	public InternalMatchState(Match match, Dealer dealer) {
		super();
		this.match = match;
		this.dealer = dealer;
		previousTurns = new ArrayList<>(match.highestCardValueInMatch());
		startNextRound();
	}

	public Match getMatch() {
		return match;
	}

	public int getCurrentRound() {
		return currentRound;
	}
	
	public int getCurrentTurn() {
		return currentTurn;
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
		return drawPile.subList(wonCardsPosition, currentTurn).toArray(new Integer[currentTurn-wonCardsPosition]);
	}
	
	public Integer[] getRemainingDrawPile() {
		Integer[] remainingDrawPile = drawPile.subList(currentTurn, drawPile.size()).toArray(new Integer[drawPile.size()-currentTurn]);
		Arrays.sort(remainingDrawPile);
		return remainingDrawPile;
	}
	
	public Integer getNextVictoryCard() {		
		return currentTurn < drawPile.size() ? drawPile.get(currentTurn) : null;
	}
	
	public List<InternalTurnState> getPreviousTurns() {
		return previousTurns;
	}

	public void startNextRound() {
		currentRound++;	
		wonCardsPosition = 0;
		currentTurn = 0;
		highestValueCardThisRound = match.getRounds()[currentRound-1];
		handPlayer1 = new LinkedList<Integer>();
		handPlayer2 = new LinkedList<Integer>();
		for (int i = 1; i <= highestValueCardThisRound; i++) {
			handPlayer1.add(i);
			handPlayer2.add(i);
		}
		drawPile = dealer.createShuffledDeck(highestValueCardThisRound);
		startNextTurn();
	}
	
	public void startNextTurn() {
		// clear previous round's array of turn information before turn 2 (it is kept
		// in turn 1 so as to provide the final result of the previous round)
		if (currentTurn == 1) {
			previousTurns.clear();
		}
		
		int victoryCard = drawPile.get(currentTurn);
		currentTurn++;
		if (currentTurnState != null) {
			previousTurns.add(currentTurnState);
		}
		currentTurnState = new InternalTurnState(victoryCard);		
	}
	
	public void bidFromPlayer1(int bid, boolean legal) {
		currentTurnState.bidFromPlayer1(bid, legal);
		checkEndTurn();
	}
	
	public void bidFromPlayer2(int bid, boolean legal) {
		currentTurnState.bidFromPlayer2(bid, legal);
		checkEndTurn();
	}

	public boolean isRoundFinished() {
		return currentTurn == getHighestValueCardThisRound() && isTurnFinished();
	}
	
	public boolean isMatchFinished() {
		return isRoundFinished() && currentRound == match.getNumberOfRounds();
	}

	private boolean isTurnFinished() {
		return currentTurnState.isTurnFinished();
	}
	
	private void checkEndTurn() {
		if (isTurnFinished()) {
			if (currentTurnState.getBidPlayer1() > currentTurnState.getBidPlayer2()) {
				currentTurnState.wonByPlayer1(getLot());
				scoreForPlayer1 += currentTurnState.getPointsWonPlayer1();
				wonCardsPosition = currentTurn;
			}
			else if (currentTurnState.getBidPlayer1() < currentTurnState.getBidPlayer2()) {
				currentTurnState.wonByPlayer2(getLot());
				scoreForPlayer2 += currentTurnState.getPointsWonPlayer2();
				wonCardsPosition = currentTurn;
			}
			
			if (!isMatchFinished()) {
				if (isRoundFinished()) {
					startNextRound();
				}
				else {
					startNextTurn();	
				}
			}
 		}
	}
	
}

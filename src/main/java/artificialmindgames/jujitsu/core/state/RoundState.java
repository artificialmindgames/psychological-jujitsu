package artificialmindgames.jujitsu.core.state;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RoundState {

	@Min(value=3)
	private int highestCardValue;
	
	@NotNull
	private List<Integer> lot;
	
	private Integer nextVictoryCard;
	
	@NotNull
	private List<Integer> myCardsPlayed;
	
	@NotNull
	private List<Integer> opponentCardsPlayed;
	
	@NotNull
	private List<Integer> myCardsRemaining;
	
	@NotNull
	private List<Integer> opponentCardsRemaining;

	@Valid
	@NotNull
	private List<TurnState> turns;

	public int getHighestCardValue() {
		return highestCardValue;
	}

	public List<Integer> getLot() {
		return lot;
	}

	public Integer getNextVictoryCard() {
		return nextVictoryCard;
	}

	public List<Integer> getMyCardsPlayed() {
		return myCardsPlayed;
	}

	public List<Integer> getOpponentCardsPlayed() {
		return opponentCardsPlayed;
	}

	public List<Integer> getMyCardsRemaining() {
		return myCardsRemaining;
	}

	public List<Integer> getOpponentCardsRemaining() {
		return opponentCardsRemaining;
	}

	public List<TurnState> getTurns() {
		return turns;
	}

	public int getMyPointsThisRound() {
		int points = 0;
		for (TurnState turn : turns) {
			points += turn.getMyPointsGained();
		}
		
		return points;
	}
	
	public int getOpponentPointsThisRound() {
		int points = 0;
		for (TurnState turn : turns) {
			points += turn.getOpponentPointsGained();
		}
		
		return points;
	}

	public TurnState getLastTurn() {
		if (turns.isEmpty()) {
			return null;
		}
		return turns.get(turns.size()-1);
	}
}

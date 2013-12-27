package artificialmindgames.jujitsu.core.state;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayState {

	@NotEmpty
	private final List<Integer> match;
	
	private final Integer myTimeRemainingMs;
	
	private final Integer opponentTimeRemainingMs;
	
	@Min(value=0)
	private final int myPointsTotal;
	
	@Min(value=0)
	private final int opponentPointsTotal;
	
	@Min(value=1)
	private final int currentRound;
	
	private final boolean gameEnded;
	
	@NotNull
	private final List<Integer> lot;
	
	private final Integer nextVictoryCard;
	
	@NotNull
	private final List<Integer> drawPile;
	
	@NotNull
	private final List<Integer> myCardsRemaining;
	
	@NotNull
	private final List<Integer> opponentCardsRemaining;
	
	@Valid
	@NotNull
	private final List<TurnState> previousTurns;

	@JsonCreator
	public PlayState(
			@JsonProperty("match") List<Integer> match,
			@JsonProperty("myTimeRemainingMs") Integer myTimeRemainingMs,
			@JsonProperty("opponentTimeRemainingMs") Integer opponentTimeRemainingMs,
			@JsonProperty("myPointsTotal") int myPointsTotal,
			@JsonProperty("opponentPointsTotal") int opponentPointsTotal,
			@JsonProperty("currentRound") int currentRound,
			@JsonProperty("gameEnded") boolean gameEnded,
			@JsonProperty("lot") List<Integer> lot,
			@JsonProperty("nextVictoryCard") Integer nextVictoryCard,
			@JsonProperty("drawPile") List<Integer> drawPile,
			@JsonProperty("myCardsRemaining") List<Integer> myCardsRemaining,
			@JsonProperty("opponentCardsRemaining") List<Integer> opponentCardsRemaining,
			@JsonProperty("previousTurns") List<TurnState> previousTurns) {
		super();
		this.match = match;
		this.myTimeRemainingMs = myTimeRemainingMs;
		this.opponentTimeRemainingMs = opponentTimeRemainingMs;
		this.myPointsTotal = myPointsTotal;
		this.opponentPointsTotal = opponentPointsTotal;
		this.currentRound = currentRound;
		this.gameEnded = gameEnded;
		this.lot = lot;
		this.nextVictoryCard = nextVictoryCard;
		this.drawPile = drawPile;
		this.myCardsRemaining = myCardsRemaining;
		this.opponentCardsRemaining = opponentCardsRemaining;
		this.previousTurns = previousTurns;
	}

	public List<Integer> getMatch() {
		return match;
	}

	public Integer getMyTimeRemainingMs() {
		return myTimeRemainingMs;
	}

	public Integer getOpponentTimeRemainingMs() {
		return opponentTimeRemainingMs;
	}

	public int getMyPointsTotal() {
		return myPointsTotal;
	}

	public int getOpponentPointsTotal() {
		return opponentPointsTotal;
	}

	public int getCurrentRound() {
		return currentRound;
	}

	public boolean isGameEnded() {
		return gameEnded;
	}

	public List<Integer> getLot() {
		return lot;
	}
	
	public List<Integer> getDrawPile() {
		return drawPile;
	}

	public Integer getNextVictoryCard() {
		return nextVictoryCard;
	}

	public List<Integer> getMyCardsRemaining() {
		return myCardsRemaining;
	}

	public List<Integer> getOpponentCardsRemaining() {
		return opponentCardsRemaining;
	}

	public List<TurnState> getPreviousTurns() {
		return previousTurns;
	}

	public TurnState getLastTurn() {
		if (previousTurns.isEmpty()) {
			return null;
		}
		return previousTurns.get(previousTurns.size()-1);
	}
	
}

package artificialmindgames.jujitsu.core.state;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import artificialmindgames.jujitsu.core.match.InternalMatchState;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayState {

	@NotEmpty
	private final Integer[] match;
	
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
	private final Integer[] lot;
	
	private final Integer nextVictoryCard;
	
	@NotNull
	private final Integer[] drawPile;
	
	@NotNull
	private final Integer[] myCardsRemaining;
	
	@NotNull
	private final Integer[] opponentCardsRemaining;
	
	@Valid
	@NotNull
	private final TurnState[] previousTurns;

	/**
	 * Creates the player-visible game state from the game broker's internal match state
	 * @param internalMatchState
	 */
	public PlayState(InternalMatchState internalMatchState, boolean player1) {
		this.match = internalMatchState.getMatch().getRounds();
		this.myTimeRemainingMs = null;
		this.opponentTimeRemainingMs = null;
		this.myPointsTotal = player1 ? internalMatchState.getScoreForPlayer1() : internalMatchState.getScoreForPlayer2();
		this.opponentPointsTotal = player1 ? internalMatchState.getScoreForPlayer2() : internalMatchState.getScoreForPlayer1();
		this.currentRound = internalMatchState.getCurrentRound();
		this.gameEnded = false;
		this.lot = internalMatchState.getLot();
		this.nextVictoryCard = internalMatchState.getNextVictoryCard();
		this.drawPile = internalMatchState.getRemainingDrawPile();
		this.myCardsRemaining = player1 ? internalMatchState.getHandPlayer1() : internalMatchState.getHandPlayer2();
		this.opponentCardsRemaining = player1 ? internalMatchState.getHandPlayer2() : internalMatchState.getHandPlayer1();
		this.previousTurns = new TurnState[0];
	}
	
	@JsonCreator
	public PlayState(
			@JsonProperty("match") Integer[] match,
			@JsonProperty("myTimeRemainingMs") Integer myTimeRemainingMs,
			@JsonProperty("opponentTimeRemainingMs") Integer opponentTimeRemainingMs,
			@JsonProperty("myPointsTotal") int myPointsTotal,
			@JsonProperty("opponentPointsTotal") int opponentPointsTotal,
			@JsonProperty("currentRound") int currentRound,
			@JsonProperty("gameEnded") boolean gameEnded,
			@JsonProperty("lot") Integer[] lot,
			@JsonProperty("nextVictoryCard") Integer nextVictoryCard,
			@JsonProperty("drawPile") Integer[] drawPile,
			@JsonProperty("myCardsRemaining") Integer[] myCardsRemaining,
			@JsonProperty("opponentCardsRemaining") Integer[] opponentCardsRemaining,
			@JsonProperty("previousTurns") TurnState[] previousTurns) {
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

	public Integer[] getMatch() {
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
	
	/**
	 * @return the value of 'N' for this round, where the victory cards
	 * and each player has the card values 1..N
	 */
	public int getHighestCardValueThisRound() {
		return match[currentRound-1];
	}
	
	/**
	 * @return true for the first turn of the first round of a new game
	 */
	public boolean isNewGame() {
		return previousTurns.length == 0;
	}
	
	/**
	 * 
	 * @return true on the first turn of each round
	 */
	public boolean isNewRound() {
		// One card will have been drawn into the lot, so the draw pile will have
		// the value of 'N' cards for the round less one card.
		return drawPile.length == getHighestCardValueThisRound() - 1;
	}

	public boolean isGameEnded() {
		return gameEnded;
	}

	public Integer[] getLot() {
		return lot;
	}
	
	public Integer[] getDrawPile() {
		return drawPile;
	}

	public Integer getNextVictoryCard() {
		return nextVictoryCard;
	}

	public Integer[] getMyCardsRemaining() {
		return myCardsRemaining;
	}

	public Integer[] getOpponentCardsRemaining() {
		return opponentCardsRemaining;
	}

	public TurnState[] getPreviousTurns() {
		return previousTurns;
	}

	/**
	 * 
	 * @return the TurnState for the previous turn, or null on the
	 * first turn of the first round.
	 */
	public TurnState getLastTurn() {
		if (previousTurns.length == 0) {
			return null;
		}
		return previousTurns[previousTurns.length-1];
	}
	
}

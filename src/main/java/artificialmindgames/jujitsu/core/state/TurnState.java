package artificialmindgames.jujitsu.core.state;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import artificialmindgames.jujitsu.core.match.InternalTurnState;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TurnState {

	@Min(value=1)
	private final int drawnVictoryCard;
	
	@Min(value=1)
	private final int myBid;
	
	@Min(value=1)
	private final int opponentBid;
	
	@NotNull
	private final Integer[] myCardsWon;
	
	@NotNull
	private final Integer[] opponentCardsWon;
		
	private final boolean myResponseLegal;
	
	private final boolean opponentResponseLegal;

	public TurnState(InternalTurnState internalTurnState, boolean player1) {
		this.drawnVictoryCard = internalTurnState.getDrawnVictoryCard();
		this.myBid = player1 ? internalTurnState.getBidPlayer1() : internalTurnState.getBidPlayer2();
		this.opponentBid = player1 ? internalTurnState.getBidPlayer2() : internalTurnState.getBidPlayer1();
		this.myCardsWon = player1 ?  internalTurnState.getCardsWonPlayer1() : internalTurnState.getCardsWonPlayer2();
		this.opponentCardsWon = player1 ?  internalTurnState.getCardsWonPlayer2() : internalTurnState.getCardsWonPlayer1();
		this.myResponseLegal = player1 ?  internalTurnState.isResponseLegalPlayer1() : internalTurnState.isResponseLegalPlayer2();
		this.opponentResponseLegal = player1 ?  internalTurnState.isResponseLegalPlayer2() : internalTurnState.isResponseLegalPlayer1();
	}
	
	@JsonCreator
	public TurnState(
			@JsonProperty("drawnVictoryCard") int drawnVictoryCard,
			@JsonProperty("myBid") int myBid,
			@JsonProperty("opponentBid") int opponentBid,
			@JsonProperty("myCardsWon") Integer[] myCardsWon,
			@JsonProperty("opponentCardsWon") Integer[] opponentCardsWon,
			@JsonProperty("myResponseLegal") boolean myResponseLegal,
			@JsonProperty("opponentResponseLegal") boolean opponentResponseLegal) {
		super();
		this.drawnVictoryCard = drawnVictoryCard;
		this.myBid = myBid;
		this.opponentBid = opponentBid;
		this.myCardsWon = myCardsWon;
		this.opponentCardsWon = opponentCardsWon;
		this.myResponseLegal = myResponseLegal;
		this.opponentResponseLegal = opponentResponseLegal;
	}

	public int getDrawnVictoryCard() {
		return drawnVictoryCard;
	}

	public int getMyBid() {
		return myBid;
	}

	public int getOpponentBid() {
		return opponentBid;
	}

	public Integer[] getMyCardsWon() {
		return myCardsWon;
	}

	public Integer[] getOpponentCardsWon() {
		return opponentCardsWon;
	}

	public boolean isMyResponseLegal() {
		return myResponseLegal;
	}

	public boolean isOpponentResponseLegal() {
		return opponentResponseLegal;
	}

}

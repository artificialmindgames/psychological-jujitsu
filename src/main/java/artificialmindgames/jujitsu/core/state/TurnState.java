package artificialmindgames.jujitsu.core.state;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
	private final List<Integer> myCardsWon;
	
	@NotNull
	private final List<Integer> opponentCardsWon;
		
	private final boolean myResponseLegal;
	
	private final boolean opponentResponseLegal;

	@JsonCreator
	public TurnState(
			@JsonProperty("drawnVictoryCard") int drawnVictoryCard,
			@JsonProperty("myBid") int myBid,
			@JsonProperty("opponentBid") int opponentBid,
			@JsonProperty("myCardsWon") List<Integer> myCardsWon,
			@JsonProperty("opponentCardsWon") List<Integer> opponentCardsWon,
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

	public List<Integer> getMyCardsWon() {
		return myCardsWon;
	}

	public List<Integer> getOpponentCardsWon() {
		return opponentCardsWon;
	}

	public boolean isMyResponseLegal() {
		return myResponseLegal;
	}

	public boolean isOpponentResponseLegal() {
		return opponentResponseLegal;
	}

}

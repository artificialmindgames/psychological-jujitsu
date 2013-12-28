package artificialmindgames.jujitsu.core.match;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class InternalTurnState {

	private final int drawnVictoryCard;
	
	private int bidPlayer1;
	private Integer[] cardsWonPlayer1;
	private boolean responseLegalPlayer1;
	
	private int bidPlayer2;
	private Integer[] cardsWonPlayer2;
	private boolean responseLegalPlayer2;
	
	public InternalTurnState(int drawnVictoryCard) {
		super();
		this.drawnVictoryCard = drawnVictoryCard;
	}
	
	public void bidFromPlayer1(int bid, boolean legal) {
		bidPlayer1 = bid;
		responseLegalPlayer1 = legal;
	}
	
	public void wonByPlayer1(Integer[] lot) {
		cardsWonPlayer1 = lot;
		cardsWonPlayer2 = new Integer[0];		
	}
	
	public void bidFromPlayer2(int bid, boolean legal) {
		bidPlayer2 = bid;
		responseLegalPlayer2 = legal;
	}
	
	public void wonByPlayer2(Integer[] lot) {
		cardsWonPlayer1 = new Integer[0];
		cardsWonPlayer2 = lot;
	}

	public int getDrawnVictoryCard() {
		return drawnVictoryCard;
	}

	public int getBidPlayer1() {
		return bidPlayer1;
	}

	public Integer[] getCardsWonPlayer1() {
		return cardsWonPlayer1;
	}

	public boolean isResponseLegalPlayer1() {
		return responseLegalPlayer1;
	}

	public int getBidPlayer2() {
		return bidPlayer2;
	}

	public Integer[] getCardsWonPlayer2() {
		return cardsWonPlayer2;
	}

	public boolean isResponseLegalPlayer2() {
		return responseLegalPlayer2;
	}
	
}

package artificialmindgames.jujitsu.rest;

import javax.validation.constraints.Min;

public class TurnState {

	@Min(value=1)
	private int drawnVictoryCard;
	
	@Min(value=1)
	private int myBid;
	
	@Min(value=1)
	private int opponentBid;
	
	@Min(value=0)
	private int myPointsGained;
	
	@Min(value=0)
	private int opponentPointsGained;
	
	private boolean myResponseLegal;
	
	private boolean opponentResponseLegal;

	public int getDrawnVictoryCard() {
		return drawnVictoryCard;
	}

	public int getMyBid() {
		return myBid;
	}

	public int getOpponentBid() {
		return opponentBid;
	}

	public int getMyPointsGained() {
		return myPointsGained;
	}

	public int getOpponentPointsGained() {
		return opponentPointsGained;
	}

	public boolean isMyResponseLegal() {
		return myResponseLegal;
	}

	public boolean isOpponentResponseLegal() {
		return opponentResponseLegal;
	}
	
}

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

	public void setDrawnVictoryCard(int drawnVictoryCard) {
		this.drawnVictoryCard = drawnVictoryCard;
	}

	public int getMyBid() {
		return myBid;
	}

	public void setMyBid(int myBid) {
		this.myBid = myBid;
	}

	public int getOpponentBid() {
		return opponentBid;
	}

	public void setOpponentBid(int opponentBid) {
		this.opponentBid = opponentBid;
	}

	public int getMyPointsGained() {
		return myPointsGained;
	}

	public void setMyPointsGained(int myPointsGained) {
		this.myPointsGained = myPointsGained;
	}

	public int getOpponentPointsGained() {
		return opponentPointsGained;
	}

	public void setOpponentPointsGained(int opponentPointsGained) {
		this.opponentPointsGained = opponentPointsGained;
	}

	public boolean isMyResponseLegal() {
		return myResponseLegal;
	}

	public void setMyResponseLegal(boolean myResponseLegal) {
		this.myResponseLegal = myResponseLegal;
	}

	public boolean isOpponentResponseLegal() {
		return opponentResponseLegal;
	}

	public void setOpponentResponseLegal(boolean opponentResponseLegal) {
		this.opponentResponseLegal = opponentResponseLegal;
	}
	
}

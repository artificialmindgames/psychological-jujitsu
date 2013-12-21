package artificialmindgames.jujitsu.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class PlayState {

	@NotEmpty
	private List<Integer> match;
	
	private Integer myTimeRemainingMs = null;
	
	private Integer opponentTimeRemainingMs = null;
	
	@Valid
	@NotNull
	private List<RoundState> rounds;
	
	@Min(value=0)
	private int myPointsTotal = 0;
	
	@Min(value=0)
	private int opponentPointsTotal = 0;
	
	private boolean gameEnded = false;

	public List<Integer> getMatch() {
		return match;
	}

	public void setMatch(List<Integer> match) {
		this.match = match;
	}

	public Integer getMyTimeRemainingMs() {
		return myTimeRemainingMs;
	}

	public void setMyTimeRemainingMs(Integer myTimeRemainingMs) {
		this.myTimeRemainingMs = myTimeRemainingMs;
	}

	public Integer getOpponentTimeRemainingMs() {
		return opponentTimeRemainingMs;
	}

	public void setOpponentTimeRemainingMs(Integer opponentTimeRemainingMs) {
		this.opponentTimeRemainingMs = opponentTimeRemainingMs;
	}

	public List<RoundState> getRounds() {
		return rounds;
	}

	public void setRounds(List<RoundState> rounds) {
		this.rounds = rounds;
	}

	public int getMyPointsTotal() {
		return myPointsTotal;
	}

	public void setMyPointsTotal(int myPointsTotal) {
		this.myPointsTotal = myPointsTotal;
	}

	public int getOpponentPointsTotal() {
		return opponentPointsTotal;
	}

	public void setOpponentPointsTotal(int opponentPointsTotal) {
		this.opponentPointsTotal = opponentPointsTotal;
	}

	public boolean isGameEnded() {
		return gameEnded;
	}

	public void setGameEnded(boolean gameEnded) {
		this.gameEnded = gameEnded;
	}

}

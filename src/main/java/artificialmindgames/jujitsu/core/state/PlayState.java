package artificialmindgames.jujitsu.core.state;

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

	public Integer getMyTimeRemainingMs() {
		return myTimeRemainingMs;
	}

	public Integer getOpponentTimeRemainingMs() {
		return opponentTimeRemainingMs;
	}

	public List<RoundState> getRounds() {
		return rounds;
	}

	public int getMyPointsTotal() {
		return myPointsTotal;
	}

	public int getOpponentPointsTotal() {
		return opponentPointsTotal;
	}

	public boolean isGameEnded() {
		return gameEnded;
	}

	public RoundState getCurrentRound() {
		return rounds.get(rounds.size()-1);
	}

}

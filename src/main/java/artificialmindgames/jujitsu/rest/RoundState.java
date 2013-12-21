package artificialmindgames.jujitsu.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class RoundState {

	@NotNull
	private List<Integer> lot;
	
	private Integer nextVictoryCard;
	
	@Valid
	@NotNull
	private List<TurnState> turns;

	public List<Integer> getLot() {
		return lot;
	}

	public void setLot(List<Integer> lot) {
		this.lot = lot;
	}

	public Integer getNextVictoryCard() {
		return nextVictoryCard;
	}

	public void setNextVictoryCard(Integer nextVictoryCard) {
		this.nextVictoryCard = nextVictoryCard;
	}

	public List<TurnState> getTurns() {
		return turns;
	}

	public void setTurns(List<TurnState> turns) {
		this.turns = turns;
	}

}

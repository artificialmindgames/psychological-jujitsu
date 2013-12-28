package artificialmindgames.jujitsu.core.match;

import java.util.LinkedList;
import java.util.List;

import artificialmindgames.jujitsu.core.player.LocalPlayer;
import artificialmindgames.jujitsu.core.player.Player;
import artificialmindgames.jujitsu.core.spectator.LoggingSpectator;
import artificialmindgames.jujitsu.core.spectator.Spectator;
import artificialmindgames.jujitsu.rest.PlayerResource;

public class MatchMaker {
	
	private Match match;
	
	public MatchMaker() {
		match = new Match();
		match.spectators = new LinkedList<>();
	}
	
	public static MatchMaker makeMeAMatch() {
		return new MatchMaker();
	}
	
	public MatchMaker betweenPlayer(Player player) {
		match.player1 = player;
		return this;
	}
	
	public MatchMaker andPlayer(Player player) {
		match.player2 = player;
		return this;
	}
	
	public MatchMaker withRounds(Integer... rounds) {
		match.rounds = rounds;
		return this;
	}
	
	public MatchMaker withRounds(List<Integer> rounds) {
		match.rounds = (Integer[])rounds.toArray();
		return this;
	}
	
	public MatchMaker withSpectator(Spectator spectator) {
		match.spectators.add(spectator);
		return this;
	}

	public Match done() {
		if (match.rounds == null || match.rounds.length == 0) {
			match.rounds = new Integer[]{13, 13, 13};
		}
		if (match.spectators.isEmpty()) {
			match.spectators.add(new LoggingSpectator());
		}
		if (match.player1 == null) {
			match.player1 = new LocalPlayer("player1", new PlayerResource());
		}
		if (match.player2 == null) {
			match.player2 = new LocalPlayer("player2", new PlayerResource());
		}
		return match;
	}
}

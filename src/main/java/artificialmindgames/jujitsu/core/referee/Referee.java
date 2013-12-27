package artificialmindgames.jujitsu.core.referee;

import java.util.Collection;
import java.util.List;

import artificialmindgames.jujitsu.core.player.Player;
import artificialmindgames.jujitsu.core.spectator.Spectator;



public class Referee {

	public void runMatch(List<Integer> match, Player player1, Player player2, Spectator spectator) {
		
	}
	
	public void runMatch(List<Integer> match, Player player1, Player player2, Collection<Spectator> spectators) {
	}
	
	public void runMatch(List<Integer> match, Player player1, Player player2, Spectator... spectators) {
		runMatch(match, player1, player2, spectators);
	}

}

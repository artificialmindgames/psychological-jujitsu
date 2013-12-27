package artificialmindgames.jujitsu.core.match;

import java.util.Collection;
import java.util.List;

import artificialmindgames.jujitsu.core.player.Player;
import artificialmindgames.jujitsu.core.spectator.Spectator;

/**
 * 
 * The details of a match between two players, 
 * 
 * @author aigames
 *
 */
public class Match {
	
	List<Integer> rounds;
	
	Player player1;
	
	Player player2;
	
	Collection<Spectator> spectators;

	public List<Integer> getRounds() {
		return rounds;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public Collection<Spectator> getSpectators() {
		return spectators;
	}
	
}

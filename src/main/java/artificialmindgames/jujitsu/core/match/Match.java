package artificialmindgames.jujitsu.core.match;

import java.util.Collection;
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
	
	Integer[] rounds;
	
	Player player1;
	
	Player player2;
	
	Collection<Spectator> spectators;

	public int getNumberOfRounds() {
		return rounds.length;
	}
	
	public Integer[] getRounds() {
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
	
	public int highestCardValueInMatch() {
		int highest = rounds[0];
		for (int i = 1; i < rounds.length; i++) {
			int N = rounds[i];
			if (N > highest) {
				highest = N;
			}
		}
		return highest;
	}
	
}

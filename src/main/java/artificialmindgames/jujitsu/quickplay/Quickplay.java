package artificialmindgames.jujitsu.quickplay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import artificialmindgames.jujitsu.core.match.Match;
import artificialmindgames.jujitsu.core.match.MatchMaker;
import artificialmindgames.jujitsu.core.match.Referee;
import artificialmindgames.jujitsu.core.player.PlayerStrategy;
import artificialmindgames.jujitsu.core.spectator.LoggingSpectator;

public class Quickplay {

	private static final Logger LOGGER = LoggerFactory.getLogger(Quickplay.class);
	
	public static void play(PlayerStrategy playerStrategy1, PlayerStrategy playerStrategy2) {
		Match match = MatchMaker
				.makeMeAMatch()
				.withSpectator(new LoggingSpectator())
				.done();
		Referee referee = new Referee();
		
		LOGGER.info("Quickplay start");
		referee.runMatch(match);
		LOGGER.info("Quickplay finished");
	}
	
	
	
}

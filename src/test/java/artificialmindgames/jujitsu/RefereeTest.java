package artificialmindgames.jujitsu;

import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import artificialmindgames.jujitsu.core.match.Match;
import artificialmindgames.jujitsu.core.match.MatchMaker;
import artificialmindgames.jujitsu.core.match.Referee;
import artificialmindgames.jujitsu.core.spectator.LoggingSpectator;
import artificialmindgames.jujitsu.core.spectator.Spectator;
import artificialmindgames.jujitsu.core.state.PlayState;

public class RefereeTest {

	private final Logger logger = LoggerFactory.getLogger(RefereeTest.class);
	
	@Test
	public void runMatch() {
		Spectator mockSpectator = Mockito.mock(Spectator.class);
		Match match = MatchMaker
				.makeMeAMatch()
				.withSpectator(mockSpectator)
				.withSpectator(new LoggingSpectator())
				.done();
		Referee referee = new Referee();
		
		logger.info("Run match...");
		referee.runMatch(match);
		logger.info("Match finished");
		
		Mockito.verify(mockSpectator, Mockito.atLeast(13*3 + 1)).log(
				Mockito.any(PlayState.class),
				Mockito.eq("player1"),
				Mockito.eq("player2"));
	}
}

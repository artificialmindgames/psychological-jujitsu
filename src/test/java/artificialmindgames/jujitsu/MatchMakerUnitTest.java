package artificialmindgames.jujitsu;

import java.util.Arrays;

import static org.fest.assertions.api.Assertions.*;
import org.junit.Test;

import artificialmindgames.jujitsu.core.match.Match;
import artificialmindgames.jujitsu.core.match.MatchMaker;

public class MatchMakerUnitTest {

	@Test
	public void checkDefaults() {
		Match match = MatchMaker.makeMeAMatch().done();
		assertThat(match.getRounds()).isEqualTo(Arrays.asList(13, 13, 13));
		assertThat(match.getPlayer1().nickname()).isEqualTo("player1");
		assertThat(match.getPlayer2().nickname()).isEqualTo("player2");
		assertThat(match.getSpectators()).hasSize(1);
	}
	
	@Test
	public void specifyRounds() {
		Match match = MatchMaker.makeMeAMatch().withRounds(5, 6, 7).done();
		assertThat(match.getRounds()).isEqualTo(Arrays.asList(5, 6, 7));
		assertThat(match.getPlayer1().nickname()).isEqualTo("player1");
		assertThat(match.getPlayer2().nickname()).isEqualTo("player2");
		assertThat(match.getSpectators()).hasSize(1);
	}
}

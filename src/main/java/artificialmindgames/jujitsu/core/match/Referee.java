package artificialmindgames.jujitsu.core.match;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import artificialmindgames.jujitsu.core.player.Player;
import artificialmindgames.jujitsu.core.spectator.Spectator;
import artificialmindgames.jujitsu.core.state.PlayState;


public class Referee {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final Dealer dealer;
	
	public Referee() {
		super();
		this.dealer = new Dealer(new Random());
	}

	public Referee(Dealer dealer) {
		super();
		this.dealer = dealer;
	}
	
	private void spectate(InternalMatchState internalMatchState) {
		Match match = internalMatchState.getMatch();
		for (Spectator s : match.getSpectators()) {
			s.log(new PlayState(internalMatchState, true),
					match.getPlayer1().nickname(),
					match.getPlayer2().nickname());
		}
	}
	
	public void runMatch(Match match) {
		
		InternalMatchState internalMatchState = new InternalMatchState(match, dealer);
		Player player1 = match.getPlayer1();
		Player player2 = match.getPlayer2();

		spectate(internalMatchState);
		
		while (!internalMatchState.isMatchFinished()) {

			try {
				int bid = player1.move(new PlayState(internalMatchState, true));
				internalMatchState.bidFromPlayer1(bid);
			}
			catch (Exception ex) {
				logger.warn("Player 1 threw exception {}", ex);
				internalMatchState.illegalMovePlayer1();
			}
			
			try {
				int bid = player2.move(new PlayState(internalMatchState, true));
				internalMatchState.bidFromPlayer2(bid);
			}
			catch (Exception ex) {
				logger.warn("Player 2 threw exception {}", ex);
				internalMatchState.illegalMovePlayer2();
			}
			
			spectate(internalMatchState);
		}
	}

}

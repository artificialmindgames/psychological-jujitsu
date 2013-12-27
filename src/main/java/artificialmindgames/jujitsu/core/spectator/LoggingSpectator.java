package artificialmindgames.jujitsu.core.spectator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import artificialmindgames.jujitsu.core.state.PlayState;
import artificialmindgames.jujitsu.core.state.TurnState;

public class LoggingSpectator implements Spectator {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void log(PlayState playState, String myNickname,
			String opponentNickname) {
				
		TurnState turn = playState.getLastTurn();
		if (turn != null) {
			logger.info("VC: {} -- {} bid {} won {} -- {} bid {} won {}",
					turn.getDrawnVictoryCard(),
					myNickname,
					turn.getMyBid(),
					turn.getMyCardsWon(),
					opponentNickname,
					turn.getOpponentBid(),
					turn.getOpponentCardsWon());
		}
	}

}

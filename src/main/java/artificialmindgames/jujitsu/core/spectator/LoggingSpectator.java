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
		
		if (playState.isNewGame()) {
			logger.info("New game between {} and {} with rounds {}", myNickname, opponentNickname, playState.getMatch());
		}
		else {		
			logger.info("VC: {} -- {} bid {} won {} -- {} bid {} won {}",
					turn.getDrawnVictoryCard(),
					myNickname,
					turn.getMyBid(),
					turn.getMyCardsWon(),
					opponentNickname,
					turn.getOpponentBid(),
					turn.getOpponentCardsWon());
		}
		
		if (playState.isGameEnded()) {
			logger.info("Game ended. {} got {} points and {} got {} points",
					myNickname,
					playState.getMyPointsTotal(),
					opponentNickname,
					playState.getOpponentPointsTotal());
					
		}
	}

}

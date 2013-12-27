package artificialmindgames.jujitsu.core.spectator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import artificialmindgames.jujitsu.core.state.PlayState;
import artificialmindgames.jujitsu.core.state.RoundState;
import artificialmindgames.jujitsu.core.state.TurnState;

public class LoggingSpectator implements Spectator {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void log(PlayState playState, String myNickname,
			String opponentNickname) {
		
		RoundState round = playState.getCurrentRound();
		TurnState turn = round.getLastTurn();
		if (turn != null) {
			logger.info("VC: {} -- {} bid {} won {} -- {} bid {} won {}",
					turn.getDrawnVictoryCard(),
					myNickname,
					turn.getMyBid(),
					turn.getMyPointsGained(),
					opponentNickname,
					turn.getOpponentBid(),
					turn.getOpponentPointsGained());
		}
	}

}
package artificialmindgames.jujitsu.core.referee;

import artificialmindgames.jujitsu.core.match.Dealer;
import artificialmindgames.jujitsu.core.match.InternalMatchState;
import artificialmindgames.jujitsu.core.match.Match;

public class Referee {
	
	private final Dealer dealer;

	public Referee(Dealer dealer) {
		super();
		this.dealer = dealer;
	}

	protected void runRound(InternalMatchState internalMatchState) {
		
	}
	
	public void runMatch(Match match) {
		
		InternalMatchState internalMatchState = new InternalMatchState(match, dealer);
		
		for (int i = 1; i <= match.getNumberOfRounds(); i++) {
			internalMatchState.startNextRound();
			runRound(internalMatchState);
		}
	}

}

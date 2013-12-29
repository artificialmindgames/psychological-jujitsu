package artificialmindgames.jujitsu.core.player;

import artificialmindgames.jujitsu.core.state.PlayState;

/**
 * 
 * Wraps a PlayerStrategy up for local play, e.g. using the Quickplay class.
 * 
 * @author aigames
 *
 */
public class LocalPlayer implements Player {

	private String nickname;
	
	private PlayerStrategy playerStrategy;

	public LocalPlayer(String nickname, PlayerStrategy playerStrategy) {
		super();
		this.nickname = nickname;
		this.playerStrategy = playerStrategy;
	}

	@Override
	public int move(PlayState playState) {
		return playerStrategy.move(playState);
	}

	@Override
	public String nickname() {
		return nickname;
	}
	
	
}

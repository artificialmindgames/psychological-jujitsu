package artificialmindgames.jujitsu.core.player;

import artificialmindgames.jujitsu.core.state.PlayState;
import artificialmindgames.jujitsu.rest.PlayerResource;

public class LocalPlayer implements Player {

	private String nickname;
	
	private PlayerResource playerResource;

	public LocalPlayer(String nickname, PlayerResource playerResource) {
		super();
		this.nickname = nickname;
		this.playerResource = playerResource;
	}

	@Override
	public int move(PlayState playState) {
		return Integer.parseInt(playerResource.play(playState));
	}

	@Override
	public String nickname() {
		return nickname;
	}
	
	
}

package artificialmindgames.jujitsu.rest;

public class ApiVersion {

	private final String game;
	
	private final String version;

	public ApiVersion(String game, String version) {
		super();
		this.game = game;
		this.version = version;
	}

	public String getGame() {
		return game;
	}

	public String getVersion() {
		return version;
	}
	
}

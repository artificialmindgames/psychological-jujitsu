package artificialmindgames.jujitsu;

import org.junit.Test;

import artificialmindgames.jujitsu.core.player.starterbots.DrawCardStarterBot;
import artificialmindgames.jujitsu.core.player.starterbots.RandomStarterBot;
import artificialmindgames.jujitsu.quickplay.Quickplay;

public class QuickplayTest {

	@Test
	public void quickplay() {
		Quickplay.play(new DrawCardStarterBot(), new RandomStarterBot());
	}
	
	@Test
	public void quickplayWithCustomRounds() {
		Quickplay.play(new DrawCardStarterBot(), new RandomStarterBot(), 5, 6, 7);
	}
}

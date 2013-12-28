package artificialmindgames.jujitsu;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import artificialmindgames.jujitsu.core.match.Dealer;
import artificialmindgames.jujitsu.core.match.InternalMatchState;
import artificialmindgames.jujitsu.core.match.Match;
import artificialmindgames.jujitsu.core.match.MatchMaker;
import artificialmindgames.jujitsu.core.state.PlayState;

public class InternalMatchStateTest {

	private Dealer mockDealer;
	
	private Validator validator;
	
	@Before
	public void before() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    validator = factory.getValidator();
	    
	    mockDealer = Mockito.mock(Dealer.class);
	    List<Integer> shuffledDeck = Arrays.asList(6,3,11,2,4,7,13,8,10,9,1,5,12);
	    Mockito.when(mockDealer.shuffle(Mockito.anyListOf(Integer.class))).thenReturn(shuffledDeck);
	}
	
	@Test
	public void newMatchTest() {

		Match match = MatchMaker.makeMeAMatch().done();
		InternalMatchState internalMatchState = new InternalMatchState(match, mockDealer);
		
		PlayState playState1 = new PlayState(internalMatchState, true);
	    assertThat(validator.validate(playState1)).isEmpty();
	    assertThat(playState1.getCurrentRound()).isEqualTo(1);
	    assertThat(playState1.getLot()).isEqualTo(new Integer[]{6});
	    assertThat(playState1.getMyPointsTotal()).isEqualTo(0);
	    assertThat(playState1.getOpponentPointsTotal()).isEqualTo(0);
	    assertThat(playState1.getHighestCardValueThisRound()).isEqualTo(13);
	    assertThat(playState1.getDrawPile()).isEqualTo(new Integer[]{1,2,3,4,5,7,8,9,10,11,12,13});
	    assertThat(playState1.getLastTurn()).isNull();
	    assertThat(playState1.getMatch()).isEqualTo(new Integer[]{13,13,13});
	    assertThat(playState1.getPreviousTurns()).isEmpty();
	    assertThat(playState1.getMyCardsRemaining()).isEqualTo(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13});
	    assertThat(playState1.getOpponentCardsRemaining()).isEqualTo(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13});
	    
		PlayState playState2 = new PlayState(internalMatchState, false);
	    assertThat(validator.validate(playState2)).isEmpty();
	    assertThat(playState2.getCurrentRound()).isEqualTo(1);
	    assertThat(playState2.getLot()).hasSize(1);
	    assertThat(playState2.getMyPointsTotal()).isEqualTo(0);
	    assertThat(playState2.getOpponentPointsTotal()).isEqualTo(0);
	    assertThat(playState2.getDrawPile()).isEqualTo(new Integer[]{1,2,3,4,5,7,8,9,10,11,12,13});
	    assertThat(playState2.getLastTurn()).isNull();
	    assertThat(playState2.getMatch()).isEqualTo(new Integer[]{13,13,13});
	    assertThat(playState2.getPreviousTurns()).isEmpty();
	    assertThat(playState2.getMyCardsRemaining()).isEqualTo(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13});
	    assertThat(playState2.getOpponentCardsRemaining()).isEqualTo(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13});
	}
}

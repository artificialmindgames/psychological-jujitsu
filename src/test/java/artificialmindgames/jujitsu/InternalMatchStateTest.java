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
	    Mockito.when(mockDealer.createShuffledDeck(13)).thenReturn(shuffledDeck);
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
	
	@Test
	public void timeRemaining() {
		Match match = MatchMaker.makeMeAMatch().done();
		InternalMatchState internalMatchState = new InternalMatchState(match, mockDealer);
			
		internalMatchState.setTimeRemainingForPlayer1(500);
		internalMatchState.setTimeRemainingForPlayer2(999);
		
		PlayState playState1 = new PlayState(internalMatchState, true);
		assertThat(playState1.getMyTimeRemainingMs()).isEqualTo(500);
		assertThat(playState1.getOpponentTimeRemainingMs()).isEqualTo(999);
		
		PlayState playState2 = new PlayState(internalMatchState, false);
		assertThat(playState2.getMyTimeRemainingMs()).isEqualTo(999);
		assertThat(playState2.getOpponentTimeRemainingMs()).isEqualTo(500);		
	}
	
	@Test
	public void illegalMovePlayer1() {
		// mock dealer to 'randomly' pick 9 in place of illegal move
		Mockito.when(mockDealer.pickRandom(Mockito.anyListOf(Integer.class))).thenReturn(9);
		
		Match match = MatchMaker.makeMeAMatch().done();
		InternalMatchState internalMatchState = new InternalMatchState(match, mockDealer);
		
		internalMatchState.bidFromPlayer1(14);
		
		Mockito.verify(mockDealer, Mockito.atLeastOnce()).pickRandom(Mockito.anyListOf(Integer.class));
		// check that card 9 has been removed:
		assertThat(internalMatchState.getHandPlayer1()).containsOnly(1,2,3,4,5,6,7,8, 10,11,12,13);
	}
	
	@Test
	public void illegalMovePlayer2() {
		// mock dealer to 'randomly' pick 9 in place of illegal move
		Mockito.when(mockDealer.pickRandom(Mockito.anyListOf(Integer.class))).thenReturn(9);
		
		Match match = MatchMaker.makeMeAMatch().done();
		InternalMatchState internalMatchState = new InternalMatchState(match, mockDealer);
		
		internalMatchState.bidFromPlayer2(14);
		
		Mockito.verify(mockDealer, Mockito.atLeastOnce()).pickRandom(Mockito.anyListOf(Integer.class));
		// check that card 9 has been removed:
		assertThat(internalMatchState.getHandPlayer2()).containsOnly(1,2,3,4,5,6,7,8, 10,11,12,13);
	}
	
	@Test
	public void turnSequence() {
		Match match = MatchMaker.makeMeAMatch().done();
		InternalMatchState internalMatchState = new InternalMatchState(match, mockDealer);
		
		//PlayState playState1 = new PlayState(internalMatchState, true);
		
		assertThat(internalMatchState.getCurrentRound()).isEqualTo(1);
		assertThat(internalMatchState.getCurrentTurn()).isEqualTo(1);
		assertThat(internalMatchState.getPreviousTurns()).isEmpty();
		assertThat(internalMatchState.getScoreForPlayer1()).isEqualTo(0);
		assertThat(internalMatchState.getScoreForPlayer2()).isEqualTo(0);
		assertThat(internalMatchState.getLot()).containsOnly(6);
		internalMatchState.bidFromPlayer1(6);
		assertThat(internalMatchState.getCurrentRound()).isEqualTo(1);
		assertThat(internalMatchState.getCurrentTurn()).isEqualTo(1);
		assertThat(internalMatchState.getPreviousTurns()).isEmpty();
		assertThat(internalMatchState.getScoreForPlayer1()).isEqualTo(0);
		assertThat(internalMatchState.getScoreForPlayer2()).isEqualTo(0);
		assertThat(internalMatchState.getLot()).containsOnly(6);
		internalMatchState.bidFromPlayer2(7);
		assertThat(internalMatchState.getCurrentRound()).isEqualTo(1);
		assertThat(internalMatchState.getCurrentTurn()).isEqualTo(2);
		assertThat(internalMatchState.getPreviousTurns()).hasSize(1);
		assertThat(internalMatchState.getScoreForPlayer1()).isEqualTo(0);
		assertThat(internalMatchState.getScoreForPlayer2()).isEqualTo(6);
		assertThat(internalMatchState.getLot()).containsOnly(3);
		
		internalMatchState.bidFromPlayer1(3);
		internalMatchState.bidFromPlayer2(3);
		assertThat(internalMatchState.getCurrentRound()).isEqualTo(1);
		assertThat(internalMatchState.getCurrentTurn()).isEqualTo(3);
		assertThat(internalMatchState.getPreviousTurns()).hasSize(2);
		assertThat(internalMatchState.getScoreForPlayer1()).isEqualTo(0);
		assertThat(internalMatchState.getScoreForPlayer2()).isEqualTo(6);
		assertThat(internalMatchState.getLot()).containsOnly(3, 11);

		// 6,3,11,2,4,7,13,8,10,9,1,5,12
		internalMatchState.bidFromPlayer1(11);
		internalMatchState.bidFromPlayer2(11);
		internalMatchState.bidFromPlayer1(2);
		internalMatchState.bidFromPlayer2(2);
		internalMatchState.bidFromPlayer1(4);
		internalMatchState.bidFromPlayer2(4);
		internalMatchState.bidFromPlayer1(7);
		internalMatchState.bidFromPlayer2(6);
		assertThat(internalMatchState.getLot()).containsOnly(13);
		internalMatchState.bidFromPlayer1(13);
		internalMatchState.bidFromPlayer2(13);
		internalMatchState.bidFromPlayer1(8);
		internalMatchState.bidFromPlayer2(8);
		internalMatchState.bidFromPlayer1(10);
		internalMatchState.bidFromPlayer2(10);
		internalMatchState.bidFromPlayer1(9);
		internalMatchState.bidFromPlayer2(9);
		internalMatchState.bidFromPlayer1(1);
		internalMatchState.bidFromPlayer2(1);
		internalMatchState.bidFromPlayer1(5);
		internalMatchState.bidFromPlayer2(5);
		assertThat(internalMatchState.getCurrentRound()).isEqualTo(1);
		assertThat(internalMatchState.getCurrentTurn()).isEqualTo(13);
		assertThat(internalMatchState.getPreviousTurns()).hasSize(12);
		assertThat(internalMatchState.getLot()).containsOnly(1,5,8,9,10,12,13);
		assertThat(internalMatchState.getHandPlayer1()).containsOnly(12);
		assertThat(internalMatchState.getHandPlayer2()).containsOnly(12);
		internalMatchState.bidFromPlayer1(12);
		internalMatchState.bidFromPlayer2(12);
		assertThat(internalMatchState.getCurrentRound()).isEqualTo(2);
		assertThat(internalMatchState.getCurrentTurn()).isEqualTo(1);
		assertThat(internalMatchState.getPreviousTurns()).hasSize(13);
		assertThat(internalMatchState.getLot()).containsOnly(6);
		assertThat(internalMatchState.getHandPlayer1()).containsOnly(1,2,3,4,5,6,7,8,9,10,11,12,13);
		assertThat(internalMatchState.getHandPlayer2()).containsOnly(1,2,3,4,5,6,7,8,9,10,11,12,13);
		internalMatchState.bidFromPlayer1(7);
		internalMatchState.bidFromPlayer2(7);
		assertThat(internalMatchState.getCurrentRound()).isEqualTo(2);
		assertThat(internalMatchState.getCurrentTurn()).isEqualTo(2);
		assertThat(internalMatchState.getPreviousTurns()).hasSize(1);
		assertThat(internalMatchState.getLot()).containsOnly(6,3);

		// check that we only used legal moves and therefore never picked a random card from a
		// player's hand
		Mockito.verify(mockDealer, Mockito.never()).pickRandom(Mockito.anyListOf(Integer.class));
	}
}

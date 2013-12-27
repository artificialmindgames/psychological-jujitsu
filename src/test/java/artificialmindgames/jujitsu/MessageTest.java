package artificialmindgames.jujitsu;

import static org.fest.assertions.api.Assertions.*;

import java.io.IOException;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

import artificialmindgames.jujitsu.core.state.PlayState;
import artificialmindgames.jujitsu.core.state.TurnState;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageTest {

	@Test
	public void parseReadmeExample() throws JsonParseException, JsonMappingException, IOException {
		String message = 
			"{" +
			  "\"match\": [5,6,7,8]," +
			  "\"myTimeRemainingMs\": null," +
			  "\"opponentTimeRemainingMs\": null," +
			  "\"myPointsTotal\": 0," +
			  "\"opponentPointsTotal\": 2," +
			  "\"currentRound\": 1," +
			  "\"gameEnded\": false," +
			  
			  "\"lot\": [4]," +
			  "\"nextVictoryCard\": 1," +
			  "\"myCardsRemaining\": [1,3,4,5]," +     
			  "\"opponentCardsRemaining\": [1,2,4,5]," + 
			  "\"drawPile\": [1,3,5]," +
			  
			  "\"previousTurns\": [{" +
			  "   \"myBid\": 2," +
			  "   \"opponentBid\": 3," +
			  "   \"drawnVictoryCard\": 2," +
			  "   \"myCardsWon\": []," +
			  "   \"opponentCardsWon\": [2]," +              
			  "   \"myResponseLegal\": true," +          
			  "   \"opponentResponseLegal\": true" +
			  "}]" +
			"}";
		
		ObjectMapper objectMapper = new ObjectMapper();
		PlayState playState = objectMapper.readValue(message, PlayState.class);
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Validator validator = factory.getValidator();
	    assertThat(validator.validate(playState)).isEmpty();
		
		assertThat(playState.getMatch()).containsOnly(5,6,7,8);
		assertThat(playState.getMyTimeRemainingMs()).isNull();
		assertThat(playState.getOpponentTimeRemainingMs()).isNull();		
		assertThat(playState.getMyPointsTotal()).isEqualTo(0);
		assertThat(playState.getOpponentPointsTotal()).isEqualTo(2);
		assertThat(playState.getCurrentRound()).isEqualTo(1);
		assertThat(playState.isGameEnded()).isFalse();
		
		assertThat(playState.getLot()).containsOnly(4);
		assertThat(playState.getNextVictoryCard()).isEqualTo(1);
		assertThat(playState.getMyCardsRemaining()).containsOnly(1,3,4,5);
		assertThat(playState.getOpponentCardsRemaining()).containsOnly(1,2,4,5);
		assertThat(playState.getDrawPile()).containsOnly(1,3,5);
		
		TurnState turnState = playState.getLastTurn();		
		assertThat(playState.getPreviousTurns()).containsOnly(turnState);
		
		assertThat(turnState.getMyBid()).isEqualTo(2);
		assertThat(turnState.getOpponentBid()).isEqualTo(3);
		assertThat(turnState.getDrawnVictoryCard()).isEqualTo(2);
		assertThat(turnState.getMyCardsWon()).isEmpty();
		assertThat(turnState.getOpponentCardsWon()).containsOnly(2);
		assertThat(turnState.isMyResponseLegal()).isTrue();
		assertThat(turnState.isOpponentResponseLegal()).isTrue();
	}
}

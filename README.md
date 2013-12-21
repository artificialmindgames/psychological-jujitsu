psychological-jujitsu
=====================

A 'psychological' AI challenge based on the card game of the same name.

There are several variants of the card game. To keep the game rules simple (so that the focus is strategy), just one variant is used (the one we think is most interesting), and the game is always two-player.

The game is described using some card game terms, for ease of explanation, although the game is abstract and doesn't really require playing cards. Suits can be ignored. Also, Aces, Jacks, Queens and Kings, can be replaced simply with the numbers 1..N.

match description
-----------------

A match between two players consists of one or more rounds; these are played independently, but points earnt are added over each round to decide the overall outcome of the match. It is possible to win, lose or draw a match.

The number of rounds, and the number of cards for each round, is specified before play begins. This is simply an array, so [4,5,10,10] would specify a match of four rounds. In the first round, each player would receive 4 cards. In the second round, 5 cards, and so on. The minimum number of cards in a round is 3 for a practice match, and 5 for a 'competitive' match. It is worth noting that the 3-card game is trivial (see http://www.icynic.com/~don/psych.html). A competitive match will always have at least 2 rounds.

round description
-----------------

Each player receives a hand of bidding cards [1,2,3...N] at the beginning of the round. Another set of [1,2,3...N] victory cards are shuffled and placed in a draw pile. Each victory card is worth as many points as its value. So there are N(N+1)/2 victory points to win in a round (91 for N=13, for a traditional suit of playing cards). It happens that there are always N turns in a round, since each player always plays exactly one card on each turn.

turn description
----------------

The top victory card is drawn from the draw pile and placed face-up between the players, in the 'lot'. As long as the draw pile has not been exhausted, the next victory card on top of the draw pile is turned over so that the players can see what is coming in the next turn. However, it remains on the draw pile and is not used in the current turn.

Players choose a bidding card from their hand to bid for the victory card in the lot. When both players have chosen, their bids are revealed. The player who played who played the highest value bidding card wins any victory cards in the lot. In the event of a tie, nobody wins the card. It remains in the lot for the next turn; in this way victory cards can accumulate until a winning bid is played.

Played bidding cards are left in front of the players and take no further part in the round.

The value of the victory cards won by each player are added up to determine the points earnt in the round.

Player bot API
--------------

A game server is responsible for running matches between players, as well as judging and recording match outcomes.

The game server communicates with the player bots via a simple REST api, using JSON over HTTP. In terms of HTTP, it is important to be clear that the player bots are in fact acting as servers; the game server calls the player bots rather than the other way around.

The game server uses basic HTTP authentication and always issues HTTP POST requests to a specified url in the following form:

```javascript
{
  "match": [5,6,7,8],       // an array of integers describing the rounds planned for the match
  "myTimeRemainingMs": null, // thinking time in milliseconds player has left this match; null if unlimited.
  "opponentTimeRemainingMs": null, // as above, for your opponent
  "rounds": [{             // an array describing each round so far. The last array element is the current round.
    "lot": [4],             // an array of integers, the victory cards currently in the lot
    "nextVictoryCard": 1,   // an integer; value of the next victory card on the draw pile, null if all cards drawn 
    "turns": [{            // an array describing previous turns.
      "drawnVictoryCard": 1,      // the victory card drawn into the lot
      "myBid": 1,                 // the bid card played by the player
      "opponentBid": 1,           // as above, for your opponent
      "myPointsGained": 1,         // the value of victory cards won by your bot (0 if none) on this turn
      "opponentPointsGained": 1,  // as above, for your opponent
      "myResponseLegal": 1,       // true if the server accepted the response from your player bot that turn
      "opponentResponseLegal": 1 // as above, for your opponenet
    }]
  }],
  "myPointsTotal": 0,            // total points for the match so far
  "opponentPointsTotal": 0,      // as above, for your opponent
  "gameEnded": false            // true if the game has ended (server reserves right to end match early)
}
```

The first request will have a single element in the rounds array -- this is the first and current round. The turns array will be empty, since no turns have been played yet.

The HTTP response body is simply the number of the bid card to play, with mime type 'text/plain'. If a valid response is not received, or the response is not a legal move, then a card is played randomly. The server will report this in the next request using myResponseLegal and opponentResponseLegal. If a player runs out of thinking time for the match, all their remaining bids will be random, and they will only receive the game end request. Both players are made aware of this happening, because the time remaining will be zero. By disclosing this information, a player is not at a disadvantage for having an opponent run out of time, since they can adjust their strategy. The response value is ignored if gameEnded is true.

A single game server will only ever expect a player bot to play one game at a time.

Note that the history of the match is provided in the request so that bots can be stateless and still use previous information to influence strategy. The points information is redundant, but is provided for convenience to the player bot.

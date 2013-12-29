psychological-jujitsu
=====================

A 'psychological' AI challenge based on the card game of the same name.

There are several variants of the card game. To keep the game rules simple (so that the focus is strategy), just one variant is used (the one we think is most interesting),
and the game is always two-player.

The game is described using some card game terms, for ease of explanation, although the game is abstract and doesn't really require playing cards. Suits can be ignored.
Also, Aces, Jacks, Queens and Kings, can be replaced simply with the numbers 1..N.

match description
-----------------

A match between two players consists of one or more rounds; these are played independently, but points earnt are added over each round to decide the overall outcome of the
match. It is possible to win, lose or draw a match.

The number of rounds, and the number of cards for each round, is specified before play begins. This is simply an array, so [4,5,10,10] would specify a match of four rounds.
In the first round, each player would receive 4 cards. In the second round, 5 cards, and so on. The minimum number of cards in a round is 3 for a practice match, and 5 for
a 'competitive' match. It is worth noting that the 3-card game is trivial (see http://www.icynic.com/~don/psych.html). A competitive match will always have at least 2 rounds.

round description
-----------------

Each player receives a hand of bidding cards [1,2,3...N] at the beginning of the round. Another set of [1,2,3...N] victory cards are shuffled and placed in a draw pile.
Each victory card is worth as many points as its value. So there are N(N+1)/2 victory points to win in a round (91 for N=13, for a traditional suit of playing cards).
It happens that there are always N turns in a round, since each player always plays exactly one card on each turn.

turn description
----------------

The top victory card is drawn from the draw pile and placed face-up between the players, in the 'lot'. As long as the draw pile has not been exhausted, the next victory
card on top of the draw pile is turned over so that the players can see what is coming in the next turn. However, it remains on the draw pile and is not used in the
current turn.

Players choose a bidding card from their hand to bid for the victory card in the lot. When both players have chosen, their bids are revealed. The player who played who
played the highest value bidding card wins any victory cards in the lot. In the event of a tie, nobody wins the card. It remains in the lot for the next turn; in this
way victory cards can accumulate until a winning bid is played.

Played bidding cards are left in front of the players and take no further part in the round.

The value of the victory cards won by each player are added up to determine the points earnt in the round.

Player bot API
--------------

A game broker is responsible for running matches between players, as well as judging and recording match outcomes.

The game broker communicates with the player bots via a simple REST api, using JSON over HTTP. In terms of HTTP, it is important to be clear that the player bots are in
fact acting as servers; the game broker calls the player bots rather than the other way around.

The game broker always issues HTTP POST requests to a specified url in the following form:

```javascript
{
  "match": [5,6,7,8],              // an array of integers describing the number of rounds, and highest card value each round
  "myTimeRemainingMs": null,       // thinking time in milliseconds player has left this match; null if unlimited.
  "opponentTimeRemainingMs": null, // as above, for your opponent
  "myPointsTotal": 0,              // total victory points you have earnt in the match so far
  "opponentPointsTotal": 2,        // as above, for your opponent
  "currentRound": 1,               // the number of the round in the match, beginning with 1
  "gameEnded": false,              // true if the game has ended (server reserves right to end match early)
  
  "lot": [4],                          // an array of the victory card values currently being bid for
  "nextVictoryCard": 1,                // an integer; value of the next victory card on the draw pile; null on last turn
  "myCardsRemaining": [1,3,4,5],       // bidding cards still available for play this round
  "opponentCardsRemaining": [1,2,4,5], // as above, for your opponent
  "drawPile": [1,3,5],                 // victory cards remaining in the draw pile, in value order (NOT play order) 
  
  "previousTurns": [{                     // provides information on previous turns (see below for details) 
     "myBid": 2,                          // bidding card played that turn
     "opponentBid": 3,                    // as above, for your opponent
     "drawnVictoryCard": 2,               // the victory card drawn into the lot that turn
     "myCardsWon": [],                    // an array of all victory cards won by you from the lot on that turn
     "opponentCardsWon": [2],             // as above, for your opponent
     "myResponseLegal": true,             // true if the server accepted the response from your player bot
     "opponentResponseLegal": true        // as above, for your opponent
  }]
}
```

The first request will begin with currentRound 1 and an empty previousTurns array. This is the only time that the previousTurns array
will be empty. On the first turn of each subsequent round, previousTurns will show the turn information for the previous round rather
than be empty, so that the player knows how the last round ended. Otherwise, previousTurns shows information for the current round only.

Note that all the information on the current round is provided. This is intended to represent what the player would be able to see if
cards were arranged on the table face upwards in order of play, a game clock and scoreboard was provided, etc. However, it is up to the bot
to have a 'memory' or 'learn' from previous rounds.

Some information in the game request message is redundant, but is provided for convenience so that a valid starter bot can be extremely simple.

The HTTP response body is simply the number of the bid card to play, with mime type 'text/plain'. If a valid response is not received, or the response
is not a legal move, then a card is played randomly. The server will report this in the next request using myResponseLegal and opponentResponseLegal.
If a player runs out of thinking time for the match, all their remaining bids will be random, and they will only receive the game end request. Both players
are made aware of this happening, because the time remaining will be zero. By disclosing this information, a player is not at a disadvantage for having an
opponent run out of time, since they can adjust their strategy. The response value is ignored if gameEnded is true; the game broker always send one last
request to the players to show them how the game ended, although no move is necessary.

A single game broker will only ever expect a player bot to play one game at a time.

Starter Bots (Java)
-------------------

The reference implementation of 'Pyschological Jujitsu' is written in Java and provides some starter AIs, used to test the game logic internally,
but also to establish a baseline for your own AIs.

You can find the starter bots in the package "artificialmindgames.jujitsu.core.player.starterbots"

Two starter bots are provided. One picks a random card every turn. The other simply matches the victory card draw each turn.

To create your own bots, all you have to do is implement the PlayerStrategy interface (in package artificialmindgames.jujitsu.core.player).
You can run play strategies against each other using the Quickplay class (in package artificialmindgames.jujitsu.quickplay). You can
do this with a main class, an example of which is included in the Quickplay class:

```java
	public static void main(String[] args) throws Exception {
		Quickplay.play(new DrawCardStarterBot(), new RandomStarterBot());
	}
```

Or if you want to customize the number of cards and rounds:

```java
	public static void main(String[] args) throws Exception {
		Quickplay.play(new DrawCardStarterBot(), new RandomStarterBot(), 5, 6, 7);
	}
```

This would pit these two bots against each other with a match of three rounds of 5 then 6 then 7 cards, i.e. [5,6,7].

Security
--------

Security is not yet implemented, but will use one of the following: HTTP Basic Authentication over SSH, HTTP Digest, or OAuth 1.
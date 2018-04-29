import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * This class provides the entry-point to the Chancho game. The class is
 * responsible for:
 *  
 *  -- creating players, organising them in a circle and allocating card-piles
 *     between each player.
 * 
 *  -- creating a Chancho game-deck of the appropriate size.
 * 
 *  -- shuffling the game-deck.
 * 
 *  -- creating a game dealer.
 *  
 *  -- instructing the game dealer to begin the game.
 * 
 */
public final class Chancho {

	public static void main(String[] args) {

		// Set up players in a circle with their buffers
		Set<Player> players = setupPlayers();

		// Create appropriate sized game-deck for number of players
		Deck gameDeck = createGameDeck(players.size() * 4);

		// Shuffle deck a random number of times
		// (but avoiding 2 mod 4, which makes everyone win right away)
		int timesToShuffle = 7 + new Random().nextInt(3);
		for (int i = 0; i < timesToShuffle; i++) {
			gameDeck = gameDeck.riffleShuffle(gameDeck.cut());
		}

		// Start game
		new Dealer(players, gameDeck).playGame();
	}

	private static Set<Player> setupPlayers() {
		Set<Player> players = new HashSet<>();
		CardPile[] piles = new CardPile[4];
    for (int i = 0; i < 4; i++) {
      piles[i] = new CardPile(4);
    }
		for (int i = 0; i < 3; i++) {
		  players.add(new AutoPlayer(piles[i], piles[i + 1], "P" + (i + 1)));
    }
    players.add(new ManualPlayer(piles[3], piles[0], "P4"));
    return players;
	}

	/**
	 * Create a game-deck with 'size' cards, by adding complete sets of
	 * suit-values for each rank-value in ascending order of rank.
	 */
	private static Deck createGameDeck(int size) {
		assert size % 4 == 0 : "Attempting to create a game-deck whose size is not a multiple of 4.";

		Deck deck = new MinHeapDeck();

		addCards: 
		for (Rank rank : Rank.values()) {
			for (Suit suit : Suit.values()) {
				if (size-- > 0) {
					deck.addToBottom(new Card(rank, suit));
				} else {
					break addCards;
				}
			}
		}

		return deck;
	}
}

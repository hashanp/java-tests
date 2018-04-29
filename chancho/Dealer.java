import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * The class Dealer encapsulates the actions of a Chancho game-dealer. The game
 * dealer is responsible for dealing cards from the provided game-deck to each
 * player, and scheduling rounds of the game until a player has won the game.
 * All players who have declared themselves as a winner should be congratulated.
 * 
 * Developers should provide the constructor,
 * 
 * public Dealer(Set<Player> players, Deck gameDeck);
 * 
 */
public final class Dealer {

  private final List<Player> players;

  public Dealer(Set<Player> players, Deck gameDeck) {
    this.players = new LinkedList<>(players);
    for (Player player: players) {
      for (int i = 0; i < 4; i++) {
        player.addToHand(gameDeck.removeFromTop());
      }
    }
  }

  public void playGame() {
    while (!players.stream().anyMatch(t -> t.hasWon())) {
      players.stream().forEach(t -> t.discard());
      players.stream().forEach(t -> t.pickup());
    }
    System.out.println("Congratulations to: ");
    players.stream().filter(t -> t.hasWon()).forEach(System.out::println);
  }

}
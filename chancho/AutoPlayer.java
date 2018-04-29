import java.util.Arrays;
import java.util.Random;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoPlayer extends AbstractPlayer {

  private static final Random rand = new Random();

  AutoPlayer(CardPile left, CardPile right, String name) {
    super(left, right, name);
  }

  @Override
  protected int selectCard() {
    Map<Rank, Long> counts = Arrays.stream(cards)
        .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
    long max = counts.entrySet().stream()
        .mapToLong(Map.Entry::getValue)
        .max()
        .getAsLong();
    Rank[] ranks = counts.entrySet().stream()
        .filter(t -> t.getValue() == max)
        .map(Map.Entry::getKey)
        .toArray(Rank[]::new);
    if (ranks.length == 1) {
      int[] indexes = IntStream.range(0, cards.length)
          .filter(t -> cards[t].getRank() != ranks[0])
          .toArray();
      return indexes[rand.nextInt(indexes.length)];
    } else {
      return rand.nextInt(cards.length);
    }
  }
}
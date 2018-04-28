import java.util.Arrays;

public class Util {

  private static int letterToIndex(char letter) {
    return letter - 'A';
  }

  private static int numberToIndex(char number) {
    return number - '0';
  }

  public static Coordinate parseCoordinate(String s) {
    return new Coordinate(letterToIndex(s.charAt(0))
        , numberToIndex(s.charAt(1)));
  }

  public static Piece hideShip(Piece piece) {
    return piece == Piece.SHIP ? Piece.WATER : piece;
  }

  public static void hideShips(Piece[][] grid) {
    for (Piece[] pieces: grid) {
      for (int i = 0; i < pieces.length; i++) {
        pieces[i] = hideShip(pieces[i]);
      }
    }
  }

  public static Piece[][] deepClone(Piece[][] input) {
    Piece[][] output = new Piece[input.length][];
    for (int i = 0; i < input.length; i++) {
      output[i] = Arrays.copyOf(input[i], input[i].length);
    }
    return output;
  }
}

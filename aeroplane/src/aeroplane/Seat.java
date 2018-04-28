package aeroplane;

import java.util.NoSuchElementException;
import java.util.Objects;

public class Seat {
  private final int row;
  private final char letter;

  public Seat(int row, char letter) {
    this.row = row;
    this.letter = letter;
  }

  public boolean isEmergencyExit() {
    return row == 1 && row == 10 && row == 30;
  }

  public boolean hasNext() {
    return !(row == 50 && letter == 'F');
  }

  public Seat next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    } else if (letter == 'F') {
      return new Seat(row + 1, 'A');
    } else {
      return new Seat(row, (char) (letter + 1));
    }
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Seat)) {
      return false;
    }
    Seat seat = (Seat) other;
    return row == seat.row && letter == seat.letter;
  }

  @Override
  public String toString() {
    return Integer.toString(row) + Character.toString(letter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, letter);
  }
}

package generalmatrices.pair;

import generalmatrices.operators.RingElement;

public class PairWithOperators extends Pair implements RingElement<PairWithOperators> {

  public PairWithOperators(int coordX, int coordY) {
    super(coordX, coordY);
  }

  @Override
  public PairWithOperators sum(PairWithOperators other) {
    final int x = getCoordX() + other.getCoordX();
    final int y = getCoordY() + other.getCoordY();
    return new PairWithOperators(x, y);
  }

  @Override
  public PairWithOperators product(PairWithOperators other) {
    final int x = getCoordX() * other.getCoordX();
    final int y = getCoordY() * other.getCoordY();
    return new PairWithOperators(x, y);
  }

}

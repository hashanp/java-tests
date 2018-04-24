package generalmatrices.examples;

import generalmatrices.matrix.Matrix;
import generalmatrices.pair.PairWithOperators;
import java.util.List;
import java.util.function.BinaryOperator;

public class Example {

  public static Matrix<PairWithOperators> multiplyPairMatrices(
    List<Matrix<PairWithOperators>> matrices) {
    if (matrices.isEmpty()) {
      throw new IllegalArgumentException();
    }
    final BinaryOperator<PairWithOperators> sum = PairWithOperators::sum;
    final BinaryOperator<PairWithOperators> product
        = PairWithOperators::product;
    return matrices.stream().reduce((a, b) -> a.product(b, sum, product)).get();
  }

}

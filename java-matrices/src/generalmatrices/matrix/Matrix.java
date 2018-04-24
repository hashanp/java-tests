package generalmatrices.matrix;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class Matrix<T> {

  private final int order;
  private final T[][] data;

  @SuppressWarnings("unchecked")
  public Matrix(List<T> data) {
    if (data.size() == 0) {
      throw new IllegalArgumentException();
    }
    order = (int) Math.sqrt(data.size());
    this.data = (T[][]) new Object[order][order];
    for (int i = 0; i < order; i++) {
      for (int j = 0; j < order; j++) {
        this.data[i][j] = data.get(i * order + j);
      }
    }
  }

  public T get(int row, int col) {
    return data[row][col];
  }

  public int getOrder() {
    return order;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append('[');
    for (int i = 0; i < order; i++) {
      builder.append('[');
      builder.append(Arrays.stream(data[i]).map(T::toString)
         .collect(Collectors.joining(" ")));
      builder.append(']');
    }
    builder.append(']');
    return builder.toString();
  }

  @SuppressWarnings("unchecked")
  public Matrix<T> sum(Matrix<T> other, BinaryOperator<T> elementSum) {
    final T[] newData = (T[]) new Object[order * order];
    for (int i = 0; i < order; i++) {
      for (int j = 0; j < order; j++) {
        newData[i * order + j] =
            elementSum.apply(get(i, j), other.get(i, j));
      }
    }
    return new Matrix(Arrays.asList(newData));
  }

  @SuppressWarnings("unchecked")
  public Matrix<T> product(Matrix<T> other, BinaryOperator<T> elementSum
      , BinaryOperator<T> elementProduct) {
    final T[] newData = (T[]) new Object[order * order];
    for (int i = 0; i < order; i++) {
      for (int j = 0; j < order; j++) {
        final int index = i * order + j;
        newData[index]
            = elementProduct.apply(get(i, 0), other.get(0, j));
        for (int k = 1; k < order; k++) {
          T product = elementProduct.apply(get(i, k), other.get(k, j));
          newData[index] = elementSum.apply(newData[index], product);
        }
      }
    }
    return new Matrix(Arrays.asList(newData));
  }

}

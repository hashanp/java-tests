package cells;

public class ImmutableCell<T> implements Cell<T> {
  private T value;

  public ImmutableCell(T value) {
    if (value == null) {
      throw new IllegalArgumentException();
    }
    this.value = value;
  }

  @Override
  public void set(T value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public T get() {
    return value;
  }

  @Override
  public boolean isSet() {
    return true;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof ImmutableCell)) {
      return false;
    }
    ImmutableCell<T> other = (ImmutableCell<T>) object;
    return value.equals(other.value);
  }
}

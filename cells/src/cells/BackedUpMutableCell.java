package cells;

import java.util.Deque;
import java.util.ArrayDeque;

public class BackedUpMutableCell<T> implements BackedUpCell<T> {

  private Deque<T> backingStore = new ArrayDeque<>();
  private T value = null;
  private final int limit;

  public BackedUpMutableCell() {
    limit = Integer.MAX_VALUE;
  }

  public BackedUpMutableCell(int limit) {
    if (limit < 0) {
      throw new IllegalArgumentException();
    }
    this.limit = limit;
  }

  @Override
  public boolean hasBackup() {
    return !backingStore.isEmpty();
  }

  @Override
  public void revertToPrevious() {
    if (!hasBackup()) {
      throw new UnsupportedOperationException();
    } else {
      value = backingStore.pop();
    }
  }

  @Override
  public void set(T value) {
    if (this.value != null) {
      backingStore.push(this.value);
      if (backingStore.size() > limit) {
        backingStore.removeFirst();
      }
    }
    this.value = value;
  }

  @Override
  public T get() {
    return value;
  }

  @Override
  public boolean isSet() {
    return value != null;
  }

}

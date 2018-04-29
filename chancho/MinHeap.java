/**
 * This class implements a min-heap abstract data type (as described by the
 * generic interface IMinHeap<T extends Comparable<T>>) using a fixed array of
 * size MinHeap.MAXIMUM_HEAP_SIZE.
 */
public class MinHeap<T extends Comparable<T>> implements IMinHeap<T> {

  private static final int MAXIMUM_HEAP_SIZE = 52;

  @SuppressWarnings("unchecked")
  private final T[] data = (T[]) new Comparable[MAXIMUM_HEAP_SIZE];

  private int size = 0;

  @Override
  public void add(T element) throws HeapException {
    if (size >= MAXIMUM_HEAP_SIZE) {
      throw new HeapException("Heap full");
    }
    data[size] = element;
    int current = size++;
    while (current != 0) {
      final int parent = current / 2;
      if (data[parent].compareTo(data[current]) > 0) {
        T item = data[parent];
        data[parent] = data[current];
        data[current] = item;
      } else {
        return;
      }
      current = parent;
    }
  }

  @Override
  public T removeMin() {
    T min = data[0];
    data[0] = data[--size];
    int root = 0;
    while (2 * root + 1 < size) {
      int indexToCompareTo = 2 * root + 1;
      if (indexToCompareTo + 1 < size
          && data[indexToCompareTo].compareTo(data[indexToCompareTo + 1]) > 0) {
        indexToCompareTo++;
      }

      if (data[root].compareTo(data[indexToCompareTo]) > 0) {
        T item = data[root];
        data[root] = data[indexToCompareTo];
        data[indexToCompareTo] = item;
        root = indexToCompareTo;
      } else {
        break;
      }
    }
    return min;
  }

  @Override
  public T getMin() {
    return data[0];
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

}
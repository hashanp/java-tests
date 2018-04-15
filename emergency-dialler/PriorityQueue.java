public interface PriorityQueue<T> extends Iterable<T> {
  void add(double priority, T element);
  T dequeue();
  boolean isEmpty();
}

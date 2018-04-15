import java.util.Iterator;

public class LinkedListPriorityQueue<T> implements PriorityQueue<T> {

  private Node<T> head = new Node<>(Double.NEGATIVE_INFINITY, null);

  @Override
  public void add(double priority, T element) {
    Node<T> prev = head;
    Node<T> current = head.getNext();
    while (current != null && current.getPriority() < priority) {
      prev = current;
      current = current.getNext();
    }
    Node<T> nova = new Node<>(priority, element);
    nova.setNext(current);
    prev.setNext(nova);
  }

  @Override
  public T dequeue() {
    final Node<T> toBeRemoved = head.getNext();
    if (toBeRemoved == null) {
      throw new RuntimeException();
    }
    head.setNext(toBeRemoved.getNext());
    return toBeRemoved.getValue();
  }

  @Override
  public boolean isEmpty() {
    return head.getNext() == null;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private Node<T> current = head;

      @Override
      public boolean hasNext() {
        return current.getNext() != null;
      }

      @Override
      public T next() {
        current = current.getNext();
        return current.getValue();
      }
    };
  }
}

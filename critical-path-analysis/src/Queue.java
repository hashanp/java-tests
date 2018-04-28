public class Queue<T> implements QueueInterface<T>{
	
	private Node<T> first = new Node<>(null);
	private Node<T> last = first;

	public boolean isEmpty() {
		return first.getNext() == null;
	}
	
	//post: Adds the given item to the queue
	public void enqueue(T item) {
    final Node<T> nova = new Node<>(item);
    last.setNext(nova);
    last = nova;
	}
	
	//post: Removes and returns the head of the queue. It throws an 
	//      exception if the queue is empty.
	public T dequeue() throws QueueException {
    if (first.getNext() == null) {
      throw new QueueException("Queue is empty.");
    }
    first = first.getNext();
    return first.getItem();
	}
	
}

import java.util.Iterator;

/**
  * You must implement the <code>add</code> and <code>PQRebuild</code> methods.
  *
  */

public class PriorityQueue<T extends Comparable<T>> implements
		PriorityQueueInterface<T> {

	private T[] items;             //a minHeap of elements T
	private final static int max_size = 512;
	private int size;              // number of elements in the minHeap.


	public PriorityQueue() {
		items = (T[]) new Comparable[max_size];
		size = 0;
	}

	/**
	 * Returns true if the priority queue is empty. False otherwise.
	*/
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the size of the priority queue.
	*/
	public int getSize() {
		return size;
	}

	/**
	 * Returns the element with highest priority or null if the priority.
	 * queue is empty. The priority queue is left unchanged
	 */
	public T peek() {
		T root = null;
		if (!isEmpty())
			root = items[0];
		return root;
	}

	/**
	 * <strong>Implement this method for Question 2</strong>
	 * Adds a new entry to the priority queue according to the priority value. 
	 * 
	 * @param newEntry
	 *		the new element to add to the priority queue 
	 * @throws PQException if the priority queue is full
	 */
	public void add(T newEntry) throws PQException {
	  if (size == max_size) {
	  	throw new PQException("Priority queue is full");
		}
		items[size] = newEntry;
	  int current = size++;
	  while (current != 0) {
      int parent = current / 2;
      if (items[parent].compareTo(items[current]) > 0) {
        T temp = items[parent];
        items[parent] = items[current];
        items[current] = temp;
        current = parent;
      } else {
        break;
      }
    }
	}

	/**
	 * Removes the element with highest priority.
	 */
	public void remove() {
		if (!isEmpty()) {
			items[0] = items[size - 1];
			size--;
			PQRebuild(0);
		}
	}

	/**
	 *
	 * <strong>Implement this method for Question 2</strong>
	 */
	private void PQRebuild(int root) {
	  int leftChild = 2 * root;
	  int rightChild = 2 * root + 1;
	  if (leftChild >= size) {
	    return;
    }
    int toCompareTo = leftChild;
	  if (rightChild < size && items[leftChild].compareTo(items[rightChild]) > 0) {
	    toCompareTo = rightChild;
    }
    if (items[root].compareTo(items[toCompareTo]) > 0) {
	    T temp = items[root];
	    items[root] = items[toCompareTo];
	    items[toCompareTo] = temp;
	    PQRebuild(toCompareTo);
    }
	}

	public Iterator<Object> iterator() {
		return new PQIterator<Object>();
	}

	private class PQIterator<T> implements Iterator<Object> {

		private int position = 0;

		public boolean hasNext() {
			return position < size;
		}

		public Object next() {
			Object temp = items[position];
			position++;
			return temp;
		}

		public void remove() {
			throw new IllegalStateException();
		}

	}

	/**
	 * Returns a priority queue that is a clone of the current priority queue.
	*/
	public PriorityQueue<T> clone() {
		PriorityQueue<T> clone = new PriorityQueue<T>();
		clone.size = this.size;
		clone.items = (T[]) new Comparable[max_size];
		System.arraycopy(this.items, 0, clone.items, 0, size);
		return clone;
	}
	
}

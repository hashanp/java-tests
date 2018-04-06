public class PriorityQueue<E extends Comparable<E>> implements PriorityQueueInterface<E>{

  private E[] items;    //a heap of HuffmanTrees
  private final static int max_size = 256;
  private int size;    //number of HuffManTrees in the heap.

  public PriorityQueue() {
        // constructor which creates an empty heap
    items = (E[]) new Comparable[max_size];
    size = 0;
  }

  public boolean isEmpty(){
    return size == 0;
  }

  public int getSize(){
    return size;
  }

  public E getMin() {
    E root = null;
    if (!isEmpty()) root = items[0];
    return root;
  }

  public void add(E newEntry) throws PriorityQueueException {
    int curr = size;
    items[size++] = newEntry;
    while (curr != 0) {
      final int parent = curr / 2;
      if (items[parent].compareTo(items[curr]) > 0) {
        E temp = items[parent];
        items[parent] = items[curr];
        items[curr] = temp;
        curr = parent;
      } else {
        break;
      }
    }
  }

  public E removeMin() {
  // post: Removes the minimum valued item from the PriorityQueue
    E root = null;
    if (!isEmpty()){
      root = items[0];
      items[0] = items[size-1];
      size--;
      heapRebuild(0);
    }
    return root;
  }

  private void heapRebuild(int root) {
    int leftChild = 2 * root;
    int rightChild = 2 * root + 1;
    if (leftChild >= size) {
      return;
    }
    int smallest = leftChild;
    if (rightChild < size &&
        items[leftChild].compareTo(items[rightChild]) > 0) {
      smallest = rightChild;
    }
    if (items[smallest].compareTo(items[root]) < 0) {
      E temp = items[root];
      items[root] = items[smallest];
      items[smallest] = temp;
      heapRebuild(smallest);
    }
  }
}

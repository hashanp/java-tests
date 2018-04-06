public class Huffman {

  private HuffmanData[] leafEntries;
  private final static int SIZE = 50;
  private PriorityQueueInterface<BinaryTreeInterface<HuffmanData>> pq;
  private BinaryTreeInterface<HuffmanData> huffmanTree;

  public Huffman() {
    leafEntries = new HuffmanData[SIZE];
    pq = new PriorityQueue<>();
    huffmanTree = new BinaryTree<>();
  }

  public void setFrequencies() {
  // Create test data and store as an array of HuffData
    leafEntries[0] = new HuffmanData(5000, 'a');
    leafEntries[1] = new HuffmanData(2000, 'b');
    leafEntries[2] = new HuffmanData(10000, 'c');
    leafEntries[3] = new HuffmanData(8000, 'd');
    leafEntries[4] = new HuffmanData(22000, 'e');
    leafEntries[5] = new HuffmanData(49000, 'f');
    leafEntries[6] = new HuffmanData(4000, 'g');
  }

  public void setPriorityQueue() {
  // Copy test data from array LeafEntries of HuffData
  // into a PriorityQueue of HuffmanTree
    for (int i = 0; i < 7; i++) {
      if (leafEntries[i].getFrequency() > 0)
        pq.add(new BinaryTree<>(leafEntries[i]));
    }
  }

  public void createHuffmanTree() {
    while (pq.getSize() != 1) {
      final BinaryTree<HuffmanData> left
          = (BinaryTree<HuffmanData>) pq.removeMin();
      final BinaryTree<HuffmanData> right
          = (BinaryTree<HuffmanData>) pq.removeMin();
      final HuffmanData leftData = left.getRootData();
      final HuffmanData rightData = right.getRootData();
      final int freq = leftData.getFrequency() + rightData.getFrequency();
      HuffmanData huffmanData = new HuffmanData(freq, ' ');
      pq.add(new BinaryTree<>(huffmanData, left, right));
    }
    huffmanTree = pq.removeMin();
  }

  public void printCode() {
    printCodeProcedure("", huffmanTree);
  }

  private void printCodeProcedure(String code, BinaryTreeInterface<HuffmanData> tree) {
    if (tree.getLeftSubtree() == null) {
      System.out.println(tree.getRootData().getSymbol() + ": " + code);
    } else {
      printCodeProcedure(code + "0", tree.getLeftSubtree());
      printCodeProcedure(code + "1", tree.getRightSubtree());
    }
  }
}

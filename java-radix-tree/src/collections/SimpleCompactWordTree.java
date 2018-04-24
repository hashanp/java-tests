package collections;

import collections.exceptions.InvalidWordException;
import java.util.concurrent.atomic.AtomicInteger;

import java.util.LinkedList;
import java.util.List;

public class SimpleCompactWordTree implements CompactWordsSet {

  private final SimpleCompactNode root = new SimpleCompactNode();
  private final AtomicInteger size = new AtomicInteger(0);

  private static int convertToInt(char a) {
    return (int) a - 97;
  }

  @Override
  public boolean add(String word) throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid(word);
    SimpleCompactNode curr = root;
    for (int i = 0; i < word.length(); i++) {
      final int v = convertToInt(word.charAt(i));
      curr.createSubnodeIfNeeded(v);
      curr = curr.getSubnode(v);
    }
    if (curr.setWord(false, true)) {
      size.incrementAndGet();
      return true;
    }
    return false;
  }

  @Override
  public boolean remove(String word) throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid(word);
    SimpleCompactNode curr = root;
    for (int i = 0; i < word.length(); i++) {
      final int v = convertToInt(word.charAt(i));
      if (!curr.existsSubnode(v)) {
        return false;
      }
      curr = curr.getSubnode(v);
    }
    if (curr.setWord(true, false)) {
      size.decrementAndGet();
      return true;
    }
    return false;
  }

  @Override
  public boolean contains(String word) throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid(word);
    SimpleCompactNode curr = root;
    for (int i = 0; i < word.length(); i++) {
      final int v = convertToInt(word.charAt(i));
      if (!curr.existsSubnode(v)) {
        return false;
      }
      curr = curr.getSubnode(v);
    }
    return curr.isWord();
  }

  @Override
  public int size() {
    return size.get();
  }

  @Override
  public List<String> uniqueWordsInAlphabeticOrder() {
   final List<String> result = new LinkedList<>();
    root.createList("", result);
    return result;
  }

}

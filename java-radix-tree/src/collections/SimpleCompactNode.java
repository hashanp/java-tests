package collections;

import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleCompactNode {

  private final AtomicReferenceArray<SimpleCompactNode> data =
      new AtomicReferenceArray<>(26);
  private final AtomicBoolean isWord = new AtomicBoolean(false);

  public boolean isWord() {
    return isWord.get();
  }

  public boolean setWord(boolean expected, boolean value) {
    return isWord.compareAndSet(expected, value);
  }

  public boolean existsSubnode(int i) {
    return data.get(i) != null;
  }

  public void createSubnodeIfNeeded(int i) {
    if (data.get(i) == null) {
      data.compareAndSet(i, null, new SimpleCompactNode());
    }
  }

  public SimpleCompactNode getSubnode(int i) {
    return data.get(i);
  }

  public void createList(String prefix, List<String> result) {
    if (isWord.get()) {
      result.add(prefix);
    }
    for (int i = 0; i < 26; i++) {
      if (data.get(i) != null) {
        final String prefixSub = prefix + Character.toString(convertToChar(i));
        data.get(i).createList(prefixSub, result);
      }
    }
  }

  private static char convertToChar(int a) {
    return (char) (a + 97);
  }

}

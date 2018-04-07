package cells;

import java.util.Comparator;
import java.util.Stack;

public class BackedUpCellComparator<T> implements Comparator<BackedUpCell<T>> {

  private final Comparator<T> valueComparator;

  public BackedUpCellComparator(Comparator<T> valueComparator) {
    this.valueComparator = valueComparator;
  }

  @Override
  public int compare(BackedUpCell<T> o1, BackedUpCell<T> o2) {
    Stack<T> s1 = new Stack<>();
    Stack<T> s2 = new Stack<>();

    try {
      while (true) {
        if (!o1.isSet() && !o2.isSet()) {
          return 0;
        } else if (!o1.isSet()) {
          return -1;
        } else if (!o2.isSet()) {
          return 1;
        }

        int a = valueComparator.compare(o1.get(), o2.get());
        if (a == 0) {
          if (!o1.hasBackup() && !o2.hasBackup()) {
            return 0;
          } else if (!o1.hasBackup()) {
            return -1;
          } else if (!o2.hasBackup()) {
            return 1;
          }
          s1.push(o1.get());
          s2.push(o2.get());
          o1.revertToPrevious();
          o2.revertToPrevious();
        } else {
          return a;
        }
      }
    } finally {
      while (!s1.isEmpty()) {
        o1.set(s1.pop());
      }

      while (!s2.isEmpty()) {
        o2.set(s2.pop());
      }
    }
  }
}

import java.util.concurrent.Callable;

public class Worker implements Callable<Boolean> {
  private final int start;
  private final int end;
  private final ListInterface<Object2D> objs;
  private final QuadTree quadTree;

  public Worker(int start, int end, ListInterface<Object2D> objs, QuadTree quadTree) {
    this.start = start;
    this.end = end;
    this.objs = objs;
    this.quadTree = quadTree;
  }

  @Override
  public Boolean call() throws Exception {
    for (int i = start + 1; i <= end; i++) {
      if (quadTree.queryRegion(objs.get(i).getSafetyRegion()).size() != 1) {
        return false;
      }
    }
    return true;
  }
}

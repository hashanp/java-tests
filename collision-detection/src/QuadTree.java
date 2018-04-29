/**
 * You must implement the <code>add</code> and <code>queryRegion</code> methods in the
 * region-based QuadTree class given below.
 */


import java.awt.Point;
import java.rmi.UnexpectedException;

/**
 * A region-based quadtree implementation.
 */
public class QuadTree implements QuadTreeInterface {

  private QuadTreeNode root;
  private int nodeCapacity;

  /**
   * Default constructor.
   *
   * @param region The axis-aligned bounding region representing the given area that the current
   * quadtree covers
   * @param capacity The maximum number of objects each quadtree node can store. If a quadtree
   * node's number of stored objects exceeds its capacity, the node should be subdivided.
   */
  public QuadTree(AABB region, int capacity) {
    root = new QuadTreeNode(region);
    nodeCapacity = capacity;
  }

  /**
   * <p> Implement this method for Question 2 </p>
   *
   * Adds a 2D-object with Cartesian coordinates to the tree.
   *
   * @param elem the 2D-object to add to the tree.
   */
  public void add(Object2D elem) {
    addAux(root, elem);
  }

  public void addAux(QuadTreeNode start, Object2D elem) {
    QuadTreeNode node = addHelper(start, elem);
    node.values.add(node.values.size() + 1, elem);
  }

  /**
   * <p> Implement this method for Question 2 </p>
   *
   * @param elem the 2D-object to add to the tree.
   * @param node the root of the current subtree to visit
   */
  private QuadTreeNode addHelper(QuadTreeNode node, Object2D elem) {
    final Point2D point = elem.getCenter();

    subDivideIfNeeded(node);

    if (node.isLeaf()) {
      return node;
    }

    QuadTreeNode[] subnodes = {node.NE, node.NW, node.SE, node.SW};

    for (QuadTreeNode subnode: subnodes) {
      if (subnode.region.covers(point)) {
        return addHelper(subnode, elem);
      }
    }

    throw new RuntimeException();
  }

  private void subDivideIfNeeded(QuadTreeNode node) {
    if (node.isLeaf() && node.values.size() >= nodeCapacity) {
      node.subdivide();
      for (int i = node.values.size(); i > 0; i--) {
        addAux(node, node.values.get(i));
        node.values.remove(i);
      }
    }
  }

  /**
   * <p> Implement this method for Question 3 </p>
   *
   * Given an axis-aligned bounding box region, it returns all the 2D-objects
   * in the quadtree that are within the region.
   *
   * @param region axies-aligned bounding box region
   * @return a list of 2D-objects
   */
  public ListInterface<Object2D> queryRegion(AABB region) {
    // TODO: Implement this method for Question 3
    ListInterface<Object2D> objs = new ListArrayBased<>();
    queryRegionHelper(root, region, objs);
    return objs;
  }

  /**
   * <p> Implement this method for Question 3 </p>
   *
   * Auxiliary method that recursively goes down from the root of the tree through all * the
   * children whose regions overlap with the given region. When a leaf node is reached, then only
   * the 2D-objects stored at this leaf node that are covered by the given region are collected.
   *
   * @param region axies-aligned bounding box region
   * @param node the root of the current subtree to visit
   */
  private void queryRegionHelper(QuadTreeNode node, AABB region,
      ListInterface<Object2D> bucket) {

    if (node.isLeaf()) {
      for (int i = 1; i <= node.values.size(); i++) {
        if (region.covers(node.values.get(i).getCenter())) {
          bucket.add(bucket.size() + 1, node.values.get(i));
        }
      }
      return;
    }

    QuadTreeNode[] subnodes = {node.NE, node.NW, node.SE, node.SW};
    for (QuadTreeNode subnode: subnodes) {
      if (region.overlaps(subnode.region)) {
        queryRegionHelper(subnode, region, bucket);
      }
    }
  }

  /**
   * Returns true if a 2D-object is in the tree.
   *
   * @param elem the 2D-object to search for in the tree.
   */
  public boolean contains(Object2D elem) {
    return containsHelper(root, elem);
  }


  /**
   * @param elem the 2D-object to search for in the tree.
   */
  private boolean containsHelper(QuadTreeNode node, Object2D elem) {
    if (node.isLeaf()) {
      return node.values.contains(elem);
    } else {
      if (node.NE.region.covers(elem.getCenter())) {
        return containsHelper(node.NE, elem);
      } else if (node.NW.region.covers(elem.getCenter())) {
        return containsHelper(node.NW, elem);
      } else if (node.SE.region.covers(elem.getCenter())) {
        return containsHelper(node.SE, elem);
      } else {
        return containsHelper(node.SW, elem);
      }
    }
  }
}

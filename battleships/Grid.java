public class Grid {

  private static final int WIDTH = 10;
  private static final int HEIGHT = 10;

  private final Piece[][] grid = new Piece[HEIGHT][WIDTH];

  public Grid() {
    for (Piece[] pieces: grid) {
      for (int i = 0; i < pieces.length; i++) {
        pieces[i] = Piece.WATER;
      }
    }
  }

  public boolean canPlace(Coordinate c, int size, boolean isDown) {
    int x = isDown ? 0 : 1;
    int y = isDown ? 1 : 0;
    if (c.getRow() + size * y >= HEIGHT || c.getColumn() + size * x >= WIDTH) {
      return false;
    }
    for (int i = 0; i < size; i++) {
      if (grid[c.getRow() + i * y][c.getColumn() + i * x] != Piece.WATER) {
        return false;
      }
    }
    return true;
  }

  public void placeShip(Coordinate c, int size, boolean isDown) {
    int x = isDown ? 0 : 1;
    int y = isDown ? 1 : 0;
    for (int i = 0; i < size; i++) {
      grid[c.getRow() + i * y][c.getColumn() + i * x] = Piece.SHIP;
    }
  }

  public boolean wouldAttackSucceed(Coordinate c) {
    return grid[c.getRow()][c.getColumn()] == Piece.SHIP;
  }

  public void attackCell(Coordinate c) {
    final int x = c.getRow();
    final int y = c.getColumn();
    grid[x][y] = grid[x][y] == Piece.WATER ? Piece.MISS : Piece.DAMAGED_SHIP;
  }

  public boolean areAllSunk() {
    for (Piece[] pieces: grid) {
      for (int i = 0; i < pieces.length; i++) {
        if (pieces[i] == Piece.SHIP) {
          return false;
        }
      }
    }
    return true;
  }

  public String toPlayerString() {
    Piece[][] gridCopy = Util.deepClone(grid);
    Util.hideShips(gridCopy);
    return renderGrid(gridCopy);
  }

  @Override
  public String toString() {
    return renderGrid(grid);
  }

  private static String renderGrid(Piece[][] grid) {
    StringBuilder sb = new StringBuilder();
    sb.append(" 0123456789\n");
    for (int i = 0; i < grid.length; i++) {
      sb.append((char) ('A' + i));
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == null) {
          return "!";
        }
        switch (grid[i][j]) {
          case SHIP:
            sb.append('#');
            break;
          case DAMAGED_SHIP:
            sb.append('*');
            break;
          case MISS:
            sb.append('o');
            break;
          case WATER:
            sb.append('.');
            break;
        }

      }
      sb.append('\n');
    }

    return sb.toString();
  }
}

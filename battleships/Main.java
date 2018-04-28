import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Grid grid = makeInitialGrid();

    int n = 0;

    while (!grid.areAllSunk()) {
      System.out.println(grid.toPlayerString());
      System.out.println();

      System.out.print("Enter co-ordinate: ");
      final Coordinate coord = Util.parseCoordinate(input.nextLine());

      if (grid.wouldAttackSucceed(coord)) {
        System.out.println("Direct Hit!");
      }

      grid.attackCell(coord);
      n++;
    }

    System.out.println("Attempts required to win: " + n);
    System.out.println();
    System.out.println("Final state of the board:");
    System.out.println(grid);
  }

  private static Grid makeInitialGrid() {
    Grid grid = new Grid();

    String[] coords = { "A7", "B1", "B4", "D3", "F7", "H1", "H4" };
    int[] sizes = { 2, 4, 1, 3, 1, 2, 5 };
    boolean[] isDowns = { false, true, true, false, false, true, false };

    for (int i = 0; i < coords.length; i++) {
      Coordinate c = Util.parseCoordinate(coords[i]);
      grid.placeShip(c, sizes[i], isDowns[i]);
    }

    return grid;
  }
}

package videogame;

public class Magician extends Entity implements SpellCaster {

  public Magician(String name, int lifePoints) {
    super(name, lifePoints);
  }

  @Override
  protected int propagateDamage(int damageAmount) {
    lifePoints -= damageAmount;
    if (lifePoints < 0) {
      lifePoints = 0;
    }
    return damageAmount;
  }

  @Override
  public int minimumStrikeToDestroy() {
    return lifePoints;
  }

  @Override
  public int getStrength() {
    return 2 * lifePoints;
  }

  @Override
  public String toString() {
    return name + "(" + lifePoints + ")";
  }
}

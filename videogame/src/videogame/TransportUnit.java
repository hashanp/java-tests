package videogame;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TransportUnit extends Entity {
  private final Set<Entity> entries = new HashSet<>();

  public TransportUnit(String name, int lifePoints) {
    super(name, lifePoints);
  }

  public void add(Entity entity) {
    entries.add(entity);
  }

  @Override
  protected int propagateDamage(int damageAmount) {
    lifePoints -= damageAmount;
    if (lifePoints < 0) {
      lifePoints = 0;
    }
    entries.stream().forEach(t -> t.propagateDamage(damageAmount / 2));
    return damageAmount * (entries.size() + 1);
  }

  @Override
  public int minimumStrikeToDestroy() {
    int m = entries.stream()
        .mapToInt(Entity::minimumStrikeToDestroy)
        .max()
        .orElse(Integer.MIN_VALUE);

    return Math.max(lifePoints, 2 * m);
  }

  @Override
  public String toString() {
    return name + "(" + lifePoints + ") transporting: [" + entries.stream()
        .map(Object::toString).collect(Collectors.joining(", ")) + "]";
  }
}

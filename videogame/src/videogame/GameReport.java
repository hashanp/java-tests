package videogame;

import java.util.Arrays;

public class GameReport {

	public static void main(String[] args) {

	  final TransportUnit t1 = new TransportUnit("Falkor", 2500);
	  final TransportUnit t2 = new TransportUnit("Shadowfax", 350);
	  final Magician m1 = new Magician("Ged", 300);
	  final Magician m2 = new Magician("Dumbledore", 200);
	  final Magician m3 = new Magician("Saruman", 600);
    final Magician m4 = new Magician("Harry", 18);
    final Magician m5 = new Magician("Gandalf", 950);
    final Magician m6 = new Magician("Gargamel", 200);
    t2.add(m4);
    t2.add(m5);
    t1.add(m1);
    t1.add(m2);
    t1.add(m3);
    t1.add(t2);
    System.out.println(t1);
    t1.applySpell(m6);
    System.out.println(t1);
    final Magician m7 = new Magician("Hashan", t1.minimumStrikeToDestroy() / 2);
    t1.applySpell(m7);
    System.out.println(t1);
    Entity[] entities = {t1, t2, m1, m2, m3, m4, m5, m6, m7};
    Arrays.stream(entities).map(Entity::isAlive).forEach(System.out::println);
	}

}

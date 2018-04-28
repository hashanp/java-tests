package aeroplane;

public class NonCrewPassenger extends Passenger {

  public NonCrewPassenger(String firstName, String lastName, int age) {
    super(firstName, lastName);
    this.age = age;
  }

  private final int age;

  public boolean isAdult() {
    return age >= 18;
  }

  @Override
  public String toString() {
    return super.toString() + ", Age: " + age;
  }

}

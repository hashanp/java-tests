package aeroplane;

public class CrewPassenger extends Passenger {

  public CrewPassenger(String firstName, String lastName) {
    super(firstName, lastName);
  }

  @Override
  public boolean isAdult() {
    return true;
  }

  @Override
  public String toString() {
    return "Type: Crew, " + super.toString();
  }
}

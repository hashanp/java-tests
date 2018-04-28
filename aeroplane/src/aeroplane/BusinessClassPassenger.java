package aeroplane;

import java.util.List;

public class BusinessClassPassenger extends NonCrewPassenger {

  public BusinessClassPassenger(String firstName, String lastName, int age,
      Luxury luxury) {
    super(firstName, lastName, age);
    this.luxury = luxury;
  }

  private final Luxury luxury;

  @Override
  public String toString() {
    return "Type: Business, " + super.toString();
  }
}

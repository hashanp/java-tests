package aeroplane;

public abstract class Passenger {
  private String firstName;
  private String lastName;

  public Passenger(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public abstract boolean isAdult();

  @Override
  public String toString() {
    return "First Name: " + firstName + ", Last Name: " + lastName;
  }
}

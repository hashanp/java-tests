public class MaxEmergencyDialler extends EmergencyDialler {

  public MaxEmergencyDialler(Location location, Dialler dialler) {
    super(location, dialler);
  }

  @Override
  public Contact emergency() {
    return super.emergency();
  }

  @Override
  public void addToEmergencyContactList(Contact contact) {
    double priority = contact.getPeople().stream()
        .map(Person::getAddress)
        .mapToDouble(t -> t.distanceTo(location))
        .max().getAsDouble();
    queue.add(priority, contact);
  }
}

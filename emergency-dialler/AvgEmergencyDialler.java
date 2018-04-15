import java.util.Set;

public class AvgEmergencyDialler extends EmergencyDialler {

  public AvgEmergencyDialler(Location location, Dialler dialler) {
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
        .average().getAsDouble();
    queue.add(priority, contact);
  }
}

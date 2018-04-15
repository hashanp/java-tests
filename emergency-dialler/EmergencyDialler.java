public abstract class EmergencyDialler {
  protected PriorityQueue<Contact> queue = new LinkedListPriorityQueue<>();
  protected Location location;
  private Dialler dialler;

  public EmergencyDialler(Location location, Dialler dialler) {
    this.location = location;
    this.dialler = dialler;
  }

  public Contact emergency() {
    for (Contact contact: queue) {
      final boolean success = contact.getPeople().stream()
          .map(Person::getTelephoneNumber)
          .allMatch(t -> dialler.call(t, "Are you there?"));
      if (success) {
        return contact;
      }
    }
    return null;
  }

  public abstract void addToEmergencyContactList(Contact contact);
}

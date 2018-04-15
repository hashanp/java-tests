public class BuildEmergencyDialler {
  public static void main(String[] args) {
    final Location location = new Location(0, 0);
    final Dialler dialler = new Dialler();
    final EmergencyDialler emergencyDialler
        = new AvgEmergencyDialler(location, dialler);
    final Contact person1 = new Person("Jensen", 4, 2, -9);
    final Contact person2 = new Person("Jamil", 3, 0, 32);
    final Contact person3 = new Person("Ji", 5, -4, -9);
    final Contact person4 = new Person("Jane", 2, -4,1);
    final Contact person5 = new Person("Joe", 1, 2,3);
    final Group group1 = new Group("group1");
    group1.add(person3);
    group1.add(person4);
    final Group group2 = new Group("group2");
    group2.add(group1);
    group2.add(person2);
    final Group group3 = new Group("group3");
    group3.add(person5);
    emergencyDialler.addToEmergencyContactList(person1);
    emergencyDialler.addToEmergencyContactList(group2);
    emergencyDialler.addToEmergencyContactList(group3);
    System.out.println(emergencyDialler.emergency().getName());
  }
}

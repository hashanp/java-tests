import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Group implements Contact {
  private String name;
  private Set<Contact> contacts = new HashSet<>();

  public Group(String name) {
    this.name = name;
  }

  public void add(Contact c) {
    contacts.add(c);
  }

  public void remove(Contact c) {
    contacts.remove(c);
  }

  @Override
  public Set<Person> getPeople() {
    return contacts.stream()
        .map(Contact::getPeople)
        .flatMap(Set::stream)
        .collect(Collectors.toSet());
  }

  @Override
  public String getName() {
    return name;
  }
}

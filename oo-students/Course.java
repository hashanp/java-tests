import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Course {

  private final String name;
  private final Set<Student> students = new HashSet<>();

  public Course(String name) {
    this.name = name;
  }

  public Set<Postgraduate> getPostgraduates(String supervisor) {
    return students.stream()
        .filter(Postgraduate.class::isInstance)
        .map(Postgraduate.class::cast)
        .filter(t -> t.getSupervisor().name.equals(supervisor))
        .collect(Collectors.toSet());
  }

  public Set<Student> getStudents() {
    return students;
  }
}

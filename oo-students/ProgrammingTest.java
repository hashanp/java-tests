import java.util.Set;
import java.util.stream.Stream;

public class ProgrammingTest {

 public static void main(String[] args) {
   final Academic ric = new Academic("Ricardo Rodriguez");
   final Academic ismael = new Academic("Ismael Bento");
   final Undergraduate gg4
       = new Undergraduate("gg4", "George Gilligan", "gg4@ic.ac.uk", ric);
   final Undergraduate pr3
       = new Undergraduate("pr3", "Peter Robinson", "pr34@ic.ac.uk", ismael);
   final Postgraduate te2
       = new Postgraduate("te2", "Teddy Ernestine", "te2@ic.ac.uk", ric);
   final Postgraduate yj34
       = new Postgraduate("yj34", "Yalu Jenny", "yj34@ic.ac.uk", ismael);
   final Postgraduate jj8
       = new Postgraduate("jj8", "Jacob Jenny", "jj8@ic.ac.uk", ismael);
   final Course course = new Course("Programming II");
   Stream.of(gg4, pr3, te2, yj34, jj8).forEach(course.getStudents()::add);
   final Set<Postgraduate> postgraduates =
       course.getPostgraduates("Ismael Bento");
   final Notifier notifier = new Notifier(postgraduates);
   notifier.doNotifyAll("You have been notified!");
 }

}

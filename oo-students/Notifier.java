import java.util.Iterator;
import java.util.Set;

public class Notifier {
  Set<? extends Notifiable> notifiables;

  public Notifier(Set<? extends Notifiable> notifiables) {
    this.notifiables = notifiables;
  }

  public void doNotifyAll(String message) {
    notifiables.forEach(t -> t.notify(message));
  }
}

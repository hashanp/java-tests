package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;

public class RoundRobinScheduler implements Scheduler {

  private int last = -1;

  @Override
  public int chooseThread(ConcurrentProgram program) throws DeadlockException {
    last = program.getEnabledThreadIds().stream()
        .reduce((a, b) -> a > last ^ b > last ? Math.max(a, b) : Math.min(a, b))
        .orElseThrow(() -> new DeadlockException());
    return last;
  }
}

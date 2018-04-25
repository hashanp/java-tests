package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;
import concurrency.statements.WaitStmt;

public class FewestWaitsScheduler implements Scheduler {

  public static long remainingWaitStmts(ConcurrentProgram program, int id) {
    return program.remainingStatements(id).stream()
        .filter(WaitStmt.class::isInstance)
        .count();
  }

  @Override
  public int chooseThread(ConcurrentProgram program) throws DeadlockException {
    return program.getEnabledThreadIds().stream()
        .reduce((a, b) -> {
          final long remainingA = remainingWaitStmts(program, a);
          final long remainingB = remainingWaitStmts(program, b);
          if (remainingA < remainingB) {
            return a;
          } else if (remainingA == remainingB) {
            return Math.min(a, b);
          } else {
            return b;
          }
        })
        .orElseThrow(() -> new DeadlockException());
  }
}

package jobscheduler.job;

public class FailJob extends BaseJob {

    @Override
    public boolean execute() {
        getMillis().ifPresent(millis -> {
            try {
                Thread.sleep(millis);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        throw new RuntimeException();
    }
}

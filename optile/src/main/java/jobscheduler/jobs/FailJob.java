package jobscheduler.jobs;

import jobscheduler.models.BaseJob;

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

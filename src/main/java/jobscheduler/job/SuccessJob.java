package jobscheduler.job;

public class SuccessJob extends BaseJob {

    @Override
    public boolean execute() {
        getMillis().ifPresent(millis -> {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        return true;
    }
}

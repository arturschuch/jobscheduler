package jobs;

import jobscheduler.enums.JobStatus;
import jobscheduler.job.FailJob;
import jobscheduler.job.SuccessJob;
import jobscheduler.job.RunnableJob;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class RunnableJobTest {

    @Test
    public void testRunningASuccessJob() {
        SuccessJob job = new SuccessJob();
        job.setMillis(Optional.empty());

        RunnableJob runnableJob = new RunnableJob(job);

        runnableJob.run();

        Assert.assertEquals(JobStatus.SUCCESS, job.getStatus());
    }

    @Test
    public void testRunningASuccessJobThatWillTakeSomeTimeProcessing() {
        SuccessJob job = new SuccessJob();
        job.setMillis(Optional.of(6000l));

        RunnableJob runnableJob = new RunnableJob(job);

        CompletableFuture.runAsync(() -> runnableJob.run());

        waitAWhileToAsyncCallStart();

        Assert.assertEquals(JobStatus.RUNNING, job.getStatus());
    }

    @Test
    public void testRunningAFailJob() {
        FailJob job = new FailJob();
        job.setMillis(Optional.empty());

        RunnableJob runnableJob = new RunnableJob(job);

        runnableJob.run();

        Assert.assertEquals(JobStatus.FAILED, job.getStatus());
    }

    @Test
    public void testRunningAFailJobThatWillTakeSomeTimeProcessing() {
        FailJob job = new FailJob();
        job.setMillis(Optional.of(6000l));

        RunnableJob runnableJob = new RunnableJob(job);

        CompletableFuture.runAsync(() -> runnableJob.run());

        waitAWhileToAsyncCallStart();

        Assert.assertEquals(JobStatus.RUNNING, job.getStatus());
    }

    public void waitAWhileToAsyncCallStart() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

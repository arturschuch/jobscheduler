package jobscheduler.models;

import jobscheduler.enums.JobStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunnableJob implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(RunnableJob.class);

    private Job job;

    public RunnableJob(Job job){
        this.job = job;
    }

    @Override
    public void run() {
        job.setStatus(JobStatus.RUNNING);
        try {
            LOG.info("Job id="+ job.getId() + " [STARTED]");
            job.execute();
            job.setStatus(JobStatus.SUCCESS);
        } catch (Exception e) {
            job.setStatus(JobStatus.FAILED);
        }
        LOG.info("Job id="+ job.getId() + " [FINISHED] ");
    }
}
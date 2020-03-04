package jobscheduler.services;

import jobscheduler.enums.JobStatus;
import jobscheduler.models.Job;
import jobscheduler.models.RunnableJob;
import jobscheduler.repositories.JobManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

@Service
public class JobManagementServiceImpl implements JobManagementService {

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;
    @Autowired
    private JobManagementRepository jobManagementRepository;

    public JobManagementServiceImpl(ThreadPoolTaskScheduler taskScheduler, JobManagementRepository jobManagementRepository) {
        this.taskScheduler = taskScheduler;
        this.jobManagementRepository = jobManagementRepository;
    }

    @Override
    public Long addJob(Job job) {
        job.setStatus(JobStatus.QUEUED);
        Long id = jobManagementRepository.addJob(job);

        this.getScheduledFutureJob(job);

        return id;
    }

    @Override
    public Optional<JobStatus> getJobStatus(Long id) {
        Optional<Job> scheduledJobOptional = jobManagementRepository.getJob(id);

        return scheduledJobOptional.map(Job::getStatus);
    }

    private ScheduledFuture<?> getScheduledFutureJob(Job job) {
        final RunnableJob runnableJob = new RunnableJob(job);
        final Optional<String> optionalCron = job.getCron();

        if (optionalCron.isPresent()) {
            return taskScheduler.schedule(runnableJob, new CronTrigger(optionalCron.get()));
        } else {
            return taskScheduler.schedule(runnableJob, new Date());
        }
    }
}
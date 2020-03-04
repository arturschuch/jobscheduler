package jobscheduler.service;

import jobscheduler.enums.JobStatus;
import jobscheduler.job.Job;
import jobscheduler.job.RunnableJob;
import jobscheduler.repository.JobManagementRepository;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

@Service
public class JobManagementServiceImpl implements JobManagementService {

    private final ThreadPoolTaskScheduler taskScheduler;
    private final JobManagementRepository jobManagementRepository;

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
        return jobManagementRepository.getJob(id).map(Job::getStatus);
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
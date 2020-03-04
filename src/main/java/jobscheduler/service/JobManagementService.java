package jobscheduler.service;

import jobscheduler.enums.JobStatus;
import jobscheduler.job.Job;

import java.util.Optional;

public interface JobManagementService {

    Long addJob(Job job);

    Optional<JobStatus> getJobStatus(Long id);
}

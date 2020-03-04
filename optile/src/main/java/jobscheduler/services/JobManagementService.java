package jobscheduler.services;

import jobscheduler.enums.JobStatus;
import jobscheduler.models.Job;

import java.util.Optional;

public interface JobManagementService {

    Long addJob(Job job);

    Optional<JobStatus> getJobStatus(Long id);
}

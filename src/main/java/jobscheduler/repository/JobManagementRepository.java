package jobscheduler.repository;

import jobscheduler.job.Job;

import java.util.Optional;

public interface JobManagementRepository {
    Long addJob(Job job);

    Optional<Job> getJob(Long id);
}

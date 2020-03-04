package jobscheduler.repositories;

import jobscheduler.models.Job;

import java.util.Optional;

public interface JobManagementRepository {
    Long addJob(Job job);

    Optional<Job> getJob(Long id);
}

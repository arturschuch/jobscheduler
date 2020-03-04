package jobscheduler.repositories;

import jobscheduler.models.Job;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
public class JobManagementRepositoryImpl implements JobManagementRepository {

    private HashMap<Long, Job> jobs;
    private Long lastId;

    /**
     * MOCK DATABASE
     */
    public JobManagementRepositoryImpl() {
        this.jobs = new HashMap<>();
        this.lastId = 0l;
    }

    @Override
    public Long addJob(Job job) {
        Long id = getNextId();
        job.setId(id);

        jobs.put(id, job);

        return id;
    }

    private Long getNextId() {
        return ++this.lastId;
    }

    @Override
    public Optional<Job> getJob(Long id) {
        return Optional.ofNullable(jobs.get(id));
    }
}

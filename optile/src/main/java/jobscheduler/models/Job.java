package jobscheduler.models;

import jobscheduler.enums.JobStatus;

import java.util.Optional;

public interface Job {

    boolean execute();

    Long getId();
    void setId(Long id);

    JobStatus getStatus();
    void setStatus(JobStatus status);

    Optional<String> getCron();
    void setCron(Optional<String> cron);

    void setMillis(Optional<Long> millis);
    Optional<Long> getMillis();
}

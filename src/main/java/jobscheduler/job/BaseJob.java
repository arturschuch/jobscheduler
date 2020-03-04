package jobscheduler.job;

import jobscheduler.enums.JobStatus;

import java.util.Optional;

public class BaseJob implements Job {

    private Long id;
    private JobStatus status;
    private Optional<Long> millis;
    private Optional<String> cron;

    @Override
    public void setCron(Optional<String> cron) {
        this.cron = cron;
    }

    @Override
    public Optional<String> getCron() {
        return this.cron;
    }

    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public JobStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(JobStatus status) {
        this.status = status;
    }

    @Override
    public Optional<Long> getMillis() {
        return millis;
    }

    @Override
    public void setMillis(Optional<Long> millis) {
        this.millis = millis;
    }
}

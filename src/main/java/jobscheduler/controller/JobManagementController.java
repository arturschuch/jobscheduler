package jobscheduler.controller;

import jobscheduler.job.FailJob;
import jobscheduler.job.Job;
import jobscheduler.service.JobManagementService;
import jobscheduler.job.SuccessJob;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class JobManagementController {

    private final JobManagementService jobManagementService;

    public JobManagementController(JobManagementService jobManagementService) {
        this.jobManagementService = jobManagementService;
    }

    @GetMapping("/add-fail-job")
    public String addFailJob(@RequestParam(value = "cron") Optional<String> cron,
                             @RequestParam(value = "millis") Optional<Long> millis) {
        return addJob(new FailJob(), cron, millis);
    }

    @GetMapping("/add-job")
    public String addJob(@RequestParam(value = "cron") Optional<String> cron,
                         @RequestParam(value = "millis") Optional<Long> millis) {
        return addJob(new SuccessJob(), cron, millis);
    }

    private String addJob(Job job, Optional<String> cron, Optional<Long> millis) {
        job.setCron(cron);
        job.setMillis(millis);

        return "JOB ID: " + jobManagementService.addJob(job);
    }

    @GetMapping("/job-status")
    public String getJobStatus(@RequestParam(value = "id") Long id) {
        return jobManagementService.getJobStatus(id)
                .map(status -> "JOB STATUS: " + status.name())
                .orElse("Error: status not found for the id=" + id);
    }

}

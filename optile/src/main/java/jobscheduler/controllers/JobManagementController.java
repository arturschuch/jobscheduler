package jobscheduler.controllers;

import jobscheduler.enums.JobStatus;
import jobscheduler.jobs.FailJob;
import jobscheduler.models.Job;
import jobscheduler.services.JobManagementService;
import jobscheduler.jobs.SuccessJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class JobManagementController {

    @Autowired
    private JobManagementService jobManagementService;

    @GetMapping("/add-fail-job")
    public String addFailJob(@RequestParam(value = "cron") Optional<String> cron,
                             @RequestParam(value = "millis") Optional<Long> millis) {
        FailJob job = new FailJob();
        return addJob(job, cron, millis);
    }

    @GetMapping("/add-job")
    public String addJob(@RequestParam(value = "cron") Optional<String> cron,
                         @RequestParam(value = "millis") Optional<Long> millis) {
        SuccessJob job = new SuccessJob();
        return addJob(job, cron, millis);
    }

    private String addJob(Job job, Optional<String> cron, Optional<Long> millis) {
        job.setCron(cron);
        job.setMillis(millis);

        Long id = jobManagementService.addJob(job);
        return "JOB ID: " + id;
    }

    @GetMapping("/job-status")
    public String getJobStatus(@RequestParam(value = "id") Long id) {
        Optional<JobStatus> optionalStatus = jobManagementService.getJobStatus(id);
        return optionalStatus
                .map(status -> "JOB STATUS: " + status.name())
                .orElse("Error: status not found for the id=" + id);
    }

}

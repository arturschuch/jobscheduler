package services;

import jobscheduler.enums.JobStatus;
import jobscheduler.jobs.FailJob;
import jobscheduler.jobs.SuccessJob;
import jobscheduler.repositories.JobManagementRepository;
import jobscheduler.services.JobManagementServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

public class JobManagementServiceTest {

    @Mock
    private ThreadPoolTaskScheduler taskScheduler;
    @Mock
    private JobManagementRepository jobManagementRepository;

    private JobManagementServiceImpl jobManagementService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        jobManagementService = new JobManagementServiceImpl(taskScheduler, jobManagementRepository);

        Mockito.when(taskScheduler.schedule(ArgumentMatchers.any(), ArgumentMatchers.any(Trigger.class)))
                .thenReturn(null);
        Mockito.when(taskScheduler.schedule(ArgumentMatchers.any(), ArgumentMatchers.any(Date.class)))
                .thenReturn(null);
        Mockito.when(jobManagementRepository.addJob(ArgumentMatchers.any()))
                .thenReturn(1l);
    }

    @Test
    public void testAddingASuccessJobThatWillRunInTheFuture() {
        String cron = this.getCronForTheNextHour();

        SuccessJob job = new SuccessJob();
        job.setCron(Optional.of(cron));

        jobManagementService.addJob(job);

        Assert.assertEquals(JobStatus.QUEUED, job.getStatus());
    }

    @Test
    public void testAddingAFailJobThatWillRunInTheFuture() {
        String cron = this.getCronForTheNextHour();

        FailJob job = new FailJob();
        job.setCron(Optional.of(cron));

        jobManagementService.addJob(job);

        Assert.assertEquals(JobStatus.QUEUED, job.getStatus());
    }

    private String getCronForTheNextHour() {
        int nextHour = LocalTime.now().plus(2, ChronoUnit.HOURS).getHour();
        return "0 " + nextHour + " * * * * ";
    }

}

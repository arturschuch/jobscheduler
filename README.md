# Jobscheduler

This application was made to solve the study case given by Optile.
The idea was to have a way to schedule jobs with the requirements:
- Flexibility
The types of possible actions performed by the Jobs are not known to the Job
Management System. In the future, new Jobs should be supported without re-developing
the Job Management System (optional).

- Reliability: 
	Each Job should either complete successfully or perform no action at all. (I.e. there should be
        no side-effects created by a Job that fails.)
- Internal Consistency:
        At any one time a Job has one of four states: QUEUED, RUNNING, SUCCESS, FAILED. Following
        the execution of a Job, it should be left in an appropriate state.
- Scheduling:
        A Job can be executed immediately or according to a schedule.

So to fully fill this requirements has been created a rest application to create jobs from an endpoint and verify their status from another endpoit. those jobs are fake jobs, just to mock some time running (using Thread sleep), so it can be set dynamically from the endpoints, where the job will run and how much time it will take the running        

### Configuring

This application have the configuration on the file optile/main/resources/application.conf it is also possible to change some of the spring-boot configs on this application.conf if necessary
    
   * [spring-boot configs](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html) 

### Running
 This application have the Main method on the class src/main/java/jobscheduler/Application.java

### How to use

To facilitate the way to create different types of jobs running in different scheduled times the application has some endpoint to do it:	

- Endpoints:
```
	Description: Endpoint to add a failed job being able to schedule the time that it will take until breaks and what moment to start to run
	URL: [GET] http://localhost:8081/add-job
	Attributes:	
		(optional) cron   = the cron to run this job, if you dont pass it will assume that is to run instantly
		(optional) millis = millis seconds tho mock an job running, if you dont pass it will assume that is to run instantly
```
```
	Description: Endpoint to add a runnable job being able to schedule the time that it will take to runs and what moment to start to run
	URL: [GET] http://localhost:8081/add-fail-job
	Attributes:	
		(optional) cron   = the cron to run this job, if you dont pass it will assume that is to run instantly
		(optional) millis = millis seconds tho mock an job running, if you dont pass it will assume that is to run instantly
```

* OBS those endpoints adding the data should be POST, I've just made then GET to be easier to test.

```
	Description: Endpoint to get the status QUEUED, RUNNING, SUCCESS or FAILED from one specific job at any time 
	URL: [GET] http://localhost:8081/job-status?id=***
	Attributes:
		(required) id	  = id of the Job that you want to know the status
```   

 

package jobscheduler.config;

import jobscheduler.service.JobManagementServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@ComponentScan(
        basePackages= "jobscheduler.service",
        basePackageClasses={JobManagementServiceImpl.class})
public class ThreadPoolTaskSchedulerConfig {

    @Value("${jobscheduler.pool.size}")
    private int poolSize;

    @Value("{jobscheduler.thread.name.prefix}")
    private String threadNamePrefix;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(poolSize);
        threadPoolTaskScheduler.setThreadNamePrefix(threadNamePrefix);
        return threadPoolTaskScheduler;
    }

}

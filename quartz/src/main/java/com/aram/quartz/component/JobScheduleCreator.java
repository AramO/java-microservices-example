package com.aram.quartz.component;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.TriggerBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import static org.quartz.JobBuilder.newJob;

@Slf4j
@Component
public class JobScheduleCreator {

    /**
     * Create Quartz Job.
     *
     * @param jobClass  Class whose executeInternal() method needs to be called.
     * @param isDurable Job needs to be persisted even after completion. if true, job will be persisted, not otherwise.
     * @param jobName   Job name.
     * @param jobGroup  Job group.
     * @return JobDetail object
     */
    public JobDetail createJob(
            Class<? extends QuartzJobBean> jobClass,
            boolean isDurable,
            String jobName,
            String jobGroup
    ) {
//        // set job data map
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(jobName + jobGroup, jobClass.getName());

        return JobBuilder.newJob()
                        .ofType(jobClass)
                                .storeDurably(isDurable)
                                        .withIdentity(jobName, jobGroup)
                                                .setJobData(jobDataMap)
                                                        .build();
    }
}

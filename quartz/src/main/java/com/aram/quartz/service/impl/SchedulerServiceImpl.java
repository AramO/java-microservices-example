package com.aram.quartz.service.impl;

import com.aram.quartz.dto.ScheduleRequestDTO;
import com.aram.quartz.service.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class SchedulerServiceImpl implements SchedulerService {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * Registers a job without a trigger
     * @param jobClass
     * @param jobName
     * @param jobGroup
     * @param replace
     * @throws SchedulerException
     */
    public void register(
            Class<? extends Job> jobClass,
            String jobName,
            String jobGroup,
            boolean replace
    ) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroup).storeDurably().build();
        scheduler.addJob(jobDetail, replace);
    }

    /**
     * Schedules a job using the given cron expression.
     * @param jobInfo
     * @throws SchedulerException
     */
    @Override
    public void scheduleNewJob(ScheduleRequestDTO jobInfo) throws SchedulerException, ClassNotFoundException {
        Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(jobInfo.getJobClass());
        String jobName = jobInfo.getJobName();
        String jobGroup = jobInfo.getJobGroup();
        String cronExpression = jobInfo.getCronExpression();

        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);

        if (!scheduler.checkExists(jobKey)) {
            register(jobClass, jobName, jobGroup, true);
        }

        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobDetail)
                .withSchedule(cronScheduleBuilder).build();

        if (!scheduler.checkExists(triggerKey)) {
            scheduler.scheduleJob(cronTrigger);
        } else {
            scheduler.rescheduleJob(triggerKey, cronTrigger);
        }
    }

    @Override
    public boolean unScheduleJob(String jobName) {
        try {
            return schedulerFactoryBean
                    .getScheduler()
                    .unscheduleJob(new TriggerKey(jobName));
        } catch (SchedulerException e) {
            log.error("Failed to un-schedule job - {}", jobName, e);
            return false;
        }
    }

    @Override
    public boolean deleteJob(ScheduleRequestDTO jobInfo) {
        try {
            return schedulerFactoryBean
                    .getScheduler()
                    .deleteJob(new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
        } catch (SchedulerException e) {
            log.error("Failed to delete job - {}", jobInfo.getJobName(), e);
            return false;
        }
    }

    @Override
    public boolean pauseJob(ScheduleRequestDTO jobInfo) {
        try {
            schedulerFactoryBean
                    .getScheduler()
                    .pauseJob(new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
            return true;
        } catch (SchedulerException e) {
            log.error("Failed to pause job - {}", jobInfo.getJobName(), e);
            return false;
        }
    }

    @Override
    public boolean resumeJob(ScheduleRequestDTO jobInfo) {
        try {
            schedulerFactoryBean
                    .getScheduler()
                    .resumeJob(new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
            return true;
        } catch (SchedulerException e) {
            log.error("Failed to resume job - {}", jobInfo.getJobName(), e);
            return false;
        }
    }

    @Override
    public boolean startJobNow(ScheduleRequestDTO jobInfo) {
        try {
            schedulerFactoryBean
                    .getScheduler()
                    .triggerJob(new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
            return true;
        } catch (SchedulerException e) {
            log.error("Failed to start new job - {}", jobInfo.getJobName(), e);
            return false;
        }
    }
}

package com.aram.quartz.service;

import com.aram.quartz.dto.ScheduleRequestDTO;
import org.quartz.SchedulerException;

public interface SchedulerService {

//    void startAllSchedulers();

    void scheduleNewJob(ScheduleRequestDTO jobInfo) throws ClassNotFoundException, SchedulerException;

//    void updateScheduleJob(ScheduleRequestDTO jobInfo);

    boolean unScheduleJob(String jobName);

    boolean deleteJob(ScheduleRequestDTO jobInfo);

    boolean pauseJob(ScheduleRequestDTO jobInfo);

    boolean resumeJob(ScheduleRequestDTO jobInfo);

    boolean startJobNow(ScheduleRequestDTO jobInfo);
}

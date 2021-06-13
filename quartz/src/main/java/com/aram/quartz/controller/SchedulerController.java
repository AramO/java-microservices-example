package com.aram.quartz.controller;

import com.aram.quartz.dto.ScheduleRequestDTO;
import com.aram.quartz.service.impl.SchedulerServiceImpl;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/schedules")
public class SchedulerController {

    @Autowired
    private SchedulerServiceImpl schedulerServiceImpl;

    @PostMapping(value = "")
    @ResponseBody
    public String schedule(
            @RequestBody ScheduleRequestDTO details
    ) throws Exception {
        schedulerServiceImpl.scheduleNewJob(details);
        return "Successfully scheduled " + details.getJobName();
    }
}

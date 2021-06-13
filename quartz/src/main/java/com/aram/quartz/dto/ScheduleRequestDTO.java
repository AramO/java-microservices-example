package com.aram.quartz.dto;

import lombok.Data;

@Data
public class ScheduleRequestDTO {
    private String jobClass;
    private String jobName;
    private String jobGroup;
    private String cronExpression;
    private Long repeatTime;
    private Boolean cronJob;
}

package com.aram.quartz.job;

import com.aram.quartz.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SimpleJob implements Job {

    @Autowired
    private HelloService helloService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(helloService.sayHello("Teddy"));
    }
}

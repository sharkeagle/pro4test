package com.project4test.project4test.cronjob;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SysJob {
    @XxlJob("sysjob")
    public void sysJob() throws Exception{
        log.info("sysjob,timestamp:{}",System.currentTimeMillis());
    }
}

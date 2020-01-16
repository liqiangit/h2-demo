package com.klovis.demo.h2.support;

import com.klovis.demo.h2.entity.GroupDetail;
import com.klovis.demo.h2.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @program: embedded-in-memory-db
 * @description:
 * @author: zhangke
 * @create: 2020-01-08 19:49
 **/
@Slf4j
//@Component
public class InsertRowsRunner implements ApplicationRunner {
    @Autowired
    private GroupService groupService;
    @Value("${count}")
    private Integer count;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("InsertRowsRunner start");
        GroupDetail groupDetail = GroupDetailFactory.createDemoInstance();

        long start = System.currentTimeMillis();
        for (long i = 1; i <= count; i++) {
            groupDetail.setId(i);
            groupService.add(groupDetail);
            if (i % 10000 == 0) {
                log.info("current rows={}", i);
            }
        }
        long escape = System.currentTimeMillis() - start;
        log.info("escape={}", escape);
        log.info("first groupDetail={}", groupService.selectById(1));

    }
}

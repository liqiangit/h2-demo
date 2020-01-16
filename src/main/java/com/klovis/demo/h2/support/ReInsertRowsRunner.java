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
public class ReInsertRowsRunner implements ApplicationRunner {
    @Autowired
    private GroupService groupService;
    @Value("${count}")
    private Integer count;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("InsertRowsRunner start");
        batchInsert("batchInsert 1:");
        clear("clear 1:");
        batchInsert("batchInsert 2:");
        clear("clear 2:");
        batchInsert("batchInsert 3:");
        clear("clear 3:");
    }

    private void batchInsert(String prefix) {
        GroupDetail groupDetail = GroupDetailFactory.createDemoInstance();

        log.info("{}do insert", prefix);

        long start = System.currentTimeMillis();

        long i = 1;
        try {
            for (; i <= count; i++) {
                groupDetail.setId(i);
                groupService.add(groupDetail);
            }
        } finally {
            long currentRows = i - 1;
            log.info("{}current rows={}",prefix, currentRows);
            log.info("{}count={}",prefix, count);
        }

        long escape = System.currentTimeMillis() - start;
        log.info("escape={}", escape);
        log.info("first groupDetail={}", groupService.selectById(1));
    }

    private void clear(String prefix) {
        log.info("{}do clear", prefix);
        long i = 1;
        try {
            for (; i <= count; i++) {
                groupService.deleteById(i);
            }
        } finally {
            log.info("{} do clear:{}", prefix, i);
            log.info("{}count={}", prefix, count);
        }

    }

}

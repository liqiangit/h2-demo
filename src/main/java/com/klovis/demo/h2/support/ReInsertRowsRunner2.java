package com.klovis.demo.h2.support;

import com.alibaba.fastjson.JSON;
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
@Component
public class ReInsertRowsRunner2 implements ApplicationRunner {
    @Autowired
    private GroupService groupService;
    @Value("${count}")
    private Integer count;
    @Value("${batch1}")
    private Integer batch1;
    @Value("${batch2}")
    private Integer batch2;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("InsertRowsRunner start");
//        batchInsert("batchInsert 1:");
//        clear("clear 1:");
//        batchInsert("batchInsert 2:");
//        clearByLib1("clear 2:");
        batchInsert("batchInsert 3:");
        clearByLib2("clear 3:");
    }

    private void batchInsert(String prefix) {

        log.info("{}do insert", prefix);
        long start = System.currentTimeMillis();

        long i = 1;
        try {
            for (; i <= count; i++) {
                try {
					insert(i);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                GroupDetail detail=groupService.selectById(i);
                System.out.println("detail");
                if(detail!=null) {
                	System.out.println(JSON.toJSONString(detail));
                }
            }
        } finally {
            long currentRows = i - 1;
            log.info("{}current rows={}", prefix, currentRows);
            log.info("{}count={}", prefix, count);
        }

        long escape = System.currentTimeMillis() - start;
        log.info("batchInsert-escape={}", escape);
        int index = (int) (Math.random() * (count));
        log.info("random groupDetail={}", groupService.selectById(index));
    }

    private void insert(long i) {
        GroupDetail groupDetail = GroupDetailFactory.createDemoInstance();
        groupDetail.setId(i);
        groupDetail.setFeatureid("**************Featureid**************:" + i);
        groupDetail.setLibraryname2("**************Libraryname2**************:" + i / batch2);
        groupService.add(groupDetail);
//        printCurrentRowIndex(i);
    }

    private void clear(String prefix) {
        log.info("{}do clear", prefix);
        long start = System.currentTimeMillis();
        long i = 1;
        short step = 1;
        try {
            for (; i <= count; i += step) {
                groupService.deleteById(i);
//                printCurrentRowIndex(i);
            }
        } finally {
            long currentRows = i - step;
            log.info("{}do clear:currentRows={}", prefix, currentRows);
            log.info("{}do clear:count={}", prefix, count);
            log.info("{}clear-escape={}", prefix, System.currentTimeMillis() - start);
        }
    }

    private void clearByLib2(String prefix) {
        log.info("{}do clearByLib2", prefix);
        long start = System.currentTimeMillis();
        long i = 10;
        try {
            GroupDetail groupDetail = GroupDetailFactory.createDemoInstance();
            for (; i <= 20; i++) {
                groupDetail.setLibraryname2("**************Libraryname2**************:" + i);
                groupService.deleteByLibraryName2(groupDetail);
            }
        } finally {
            long currentRows = i - 1;
            log.info("{}do clearByLib2:currentLibraryname2={}", prefix, "**************Libraryname2**************:" + currentRows);
            log.info("{}do clearByLib2:count={}", prefix, count);
            log.info("{}clearByLib2-escape={}", prefix, System.currentTimeMillis() - start);
        }
        log.info("test-save1 select clearByLib2 groupDetail={}", groupService.selectById(4000));
        log.info("test-deleted select clearByLib2 groupDetail={}", groupService.selectById(15000));
        log.info("test-save2 select clearByLib2 groupDetail={}", groupService.selectById(count));
    }

    private void clearByLib1(String prefix) {
        log.info("{}do clearByLib1", prefix);
        long start = System.currentTimeMillis();
        long i = 1;
        try {
            GroupDetail groupDetail = GroupDetailFactory.createDemoInstance();
            for (; i <= 5; i++) {
                groupDetail.setLibraryname1("**************Libraryname1**************:" + i);
                groupService.deleteByLibraryName1(groupDetail);
            }
        } finally {
            log.info("{}do clearByLib1:count={}", prefix, count);
            log.info("{}clearByLib1-escape={}", prefix, System.currentTimeMillis() - start);
        }
        log.info("test-begin select clearByLib1 groupDetail={}", groupService.selectById(1));
        log.info("test-end select clearByLib1 groupDetail={}", groupService.selectById(count));
    }

    private void printCurrentRowIndex(long i) {
        if ((i & (1 << 24 - 1)) == 0L) {
            log.info("current rows={}", i);
        }
    }

}

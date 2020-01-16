package com.klovis.demo.h2.support;

import com.klovis.demo.h2.entity.GroupDetail;

/**
 * @program: embedded-in-memory-db
 * @description:
 * @author: zhangke
 * @create: 2020-01-10 10:15
 **/
public class GroupDetailFactory {
    public static GroupDetail createDemoInstance() {
        GroupDetail groupDetail = new GroupDetail();
        groupDetail.setCategory("11111111111111111111111111111111111111111111111111");
        groupDetail.setChn("Chn1111111");
        groupDetail.setFeatureid("11111111111111111111111111111111111111111111111111");
        groupDetail.setLibraryname1("11111111111111111111111111111111111111111111111111");
        groupDetail.setLibraryname2("11111111111111111111111111111111111111111111111111");
        groupDetail.setPeopleid("11111111111111111111111111111111111111111111111111");
        groupDetail.setX(11111);
        groupDetail.setY(22222);
        groupDetail.setUpdatedon(2222233L);
        groupDetail.setBiosubtype(222226564);
        return groupDetail;
    }
}

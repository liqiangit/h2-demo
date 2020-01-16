package com.klovis.demo.h2.service;

import com.klovis.demo.h2.entity.GroupDetail;
import com.klovis.demo.h2.mapper.GroupDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: embedded-in-memory-db
 * @description:
 * @author: zhangke
 * @create: 2020-01-06 14:38
 **/
@Service
public class GroupService {
    @Autowired
    private GroupDetailMapper groupDetailMapper;

    public boolean add(GroupDetail group) {
        int rows = groupDetailMapper.insert(group);
        return rows > 0;
    }

    public GroupDetail selectById(long id) {
        return groupDetailMapper.selectByPrimaryKey(id);
    }

    public void deleteById(long id) {
        groupDetailMapper.deleteByPrimaryKey(id);
    }

    public void deleteByLibraryName2(GroupDetail groupDetail) {
        groupDetailMapper.deleteByLibraryName2(groupDetail);
    }

    public void deleteByLibraryName1(GroupDetail groupDetail) {
        groupDetailMapper.deleteByLibraryName1(groupDetail);
    }

    public void clear() {
        groupDetailMapper.deleteAll();
    }

}

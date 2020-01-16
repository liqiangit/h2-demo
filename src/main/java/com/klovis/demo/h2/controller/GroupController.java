package com.klovis.demo.h2.controller;

import cn.cloudwalk.mountain.common.bean.dto.ResponseEntity;
import com.klovis.demo.h2.entity.GroupDetail;
import com.klovis.demo.h2.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: embedded-in-memory-db
 * @description:
 * @author: zhangke
 * @create: 2020-01-06 14:37
 **/
@RequestMapping("/group")
@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @RequestMapping
    @PostMapping("")
    public ResponseEntity add(@RequestBody GroupDetail groupDetail) {
        if (groupService.add(groupDetail)) {
            return ResponseEntity.Builder.success();
        }
        return ResponseEntity.Builder.unknownError("添加人脸库异常");
    }

    // TODO 删除库
    // TODO 随机删除

}

package com.klovis.demo.h2.entity;

import lombok.Data;

/**
 * 
 * <p>
 * (chn,libraryname1)两个字段组合唯一确定一个分库，
 * </p>
 * <p>
 * (chn,libraryname1,libraryname2)三个字段组合唯一确定一个二级分库，
 * </p>
 * <p>
 * (peopleid,category)唯一确定一个人,
 * </p>
 * <p>
 * featureid唯一确定一个特征
 * </p>
 * <p>
 * (chn,libraryname1,libraryname2,featureid)唯一
 * </p>
 * @author liqiang
 *
 */
@Data
public class GroupDetail {
	/**
	 * 主键
	 */
    private Long id;

    private String chn;

    private String libraryname1;

    private String libraryname2;

    private String featureid;

    private String peopleid;

    private String category;

    private Integer x;

    private Integer y;

    private Long updatedon;

    private Integer biosubtype;

}
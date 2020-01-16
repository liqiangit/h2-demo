package com.klovis.demo.h2.mapper;

import com.klovis.demo.h2.entity.GroupDetail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface GroupDetailMapper {

	@Delete("delete FROM GROUPDETAIL")
	Integer deleteAll();

	@Delete("delete FROM GROUPDETAIL where CHN=#{chn} AND LIBRARYNAME1=#{libraryname1}")
	Integer deleteByLibraryName1(GroupDetail detail);

	@Delete("delete FROM GROUPDETAIL where CHN=#{chn} AND LIBRARYNAME1=#{libraryname1} AND LIBRARYNAME2=#{libraryname2}")
	Integer deleteByLibraryName2(GroupDetail detail);

	@Delete("delete FROM GROUPDETAIL where ID=#{id}")
	Integer deleteByPrimaryKey(Long id);

	@Insert("INSERT INTO GROUPDETAIL (ID,CHN,LIBRARYNAME1,LIBRARYNAME2,FEATUREID,PEOPLEID,CATEGORY,X,Y,UPDATEDON,BIOSUBTYPE) "
			+ "VALUES (#{id}, #{chn}, #{libraryname1}, #{libraryname2}, #{featureid}, #{peopleid}, #{category}, #{x}, #{y},#{updatedon},#{biosubtype})")
	int insert(GroupDetail record);

	@Select("SELECT * FROM GROUPDETAIL where ID=#{id}")
	GroupDetail selectByPrimaryKey(Long id);

	/**
	 * 查询特征
	 * 
	 * @param detail
	 * @return
	 */
	@Select("SELECT * FROM GROUPDETAIL where CHN=#{chn} AND LIBRARYNAME1=#{libraryname1} AND LIBRARYNAME2=#{libraryname2} AND FEATUREID=#{featureid}")
	GroupDetail selectByUK(GroupDetail detail);

	/**
	 * 查询一级库明细
	 * 
	 * @param detail
	 * @return
	 */
	@Select("SELECT * FROM GROUPDETAIL where CHN=#{chn} AND LIBRARYNAME1=#{libraryname1}")
	GroupDetail selectByLibraryName1(GroupDetail detail);

	/**
	 * 查询二级库明细
	 * 
	 * @param detail
	 * @return
	 */
	@Select("SELECT * FROM GROUPDETAIL where CHN=#{chn} AND LIBRARYNAME1=#{libraryname1} AND LIBRARYNAME2=#{libraryname2}")
	GroupDetail selectByLibraryName2(GroupDetail detail);

	/**
	 * 查询人员，返回多个特征
	 * 
	 * @param detail
	 * @return
	 */
	@Select("SELECT * FROM GROUPDETAIL where CHN=#{chn} AND LIBRARYNAME1=#{libraryname1} AND LIBRARYNAME2=#{libraryname2} AND PEOPLEID=#{peopleid} AND CATEGORY=#{category}")
	GroupDetail selectByPeople(GroupDetail detail);

	@Update("UPDATE GROUPDETAIL SET PEOPLEID=#{peopleid} , CATEGORY=#{category} , X=#{x} , Y=#{y},UPDATEDON=#{updatedon},BIOSUBTYPE=#{biosubtype}  where ID=#{id}")
	int updateByPrimaryKey(GroupDetail record);

}
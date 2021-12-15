package com.make.planner.module.diary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.make.planner.module.diary.model.Diary;

@Mapper
@Repository
public interface DiaryMapper {
	
	List<Diary> selectDiaryList(int userIdx);

}

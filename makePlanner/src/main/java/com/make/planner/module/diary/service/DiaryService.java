package com.make.planner.module.diary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.make.planner.module.diary.dao.DiaryMapper;
import com.make.planner.module.diary.model.Diary;

@Service
public class DiaryService {

	@Autowired
	DiaryMapper diaryMapper;
	
	public int selectTotalCnt() {
		return diaryMapper.selectTotalCnt();
	}
	
	public List<Diary> selectDiaryList(Diary diary){
		return diaryMapper.selectDiaryList(diary);
	};
}

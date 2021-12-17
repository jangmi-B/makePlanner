package com.make.planner.module.diary.model;

import com.make.planner.module.common.paging.Paging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class Diary extends Paging{
	private int diaryIdx;
	
	private String writeDt;
	
	private String updateDt;
	
	private String title;
	
	private String contents;
	
	private int writer;
	
	private String status;
	
	// 추가컬럼
//	private String writerNm;
}

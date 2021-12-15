package com.make.planner.module.diary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diary {
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

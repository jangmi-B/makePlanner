package com.make.planner.module.diary.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.make.planner.module.diary.service.DiaryService;

@Controller
public class DiaryController {
	
	@Autowired
	DiaryService diaryService;
	

}

package com.make.planner.module.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.make.planner.module.common.paging.Criteria;
import com.make.planner.module.common.paging.Paging;
import com.make.planner.module.diary.model.Diary;
import com.make.planner.module.diary.service.DiaryService;
import com.make.planner.module.member.model.Member;
import com.make.planner.module.member.service.MemberService;


@Controller
public class LoginController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	DiaryService diaryService;
	
	// git push -u origin main
	
	@GetMapping("/")
	public String login() {
		return "/login";
	}
	
	@GetMapping("/logout/result")
	public String logout() {
		return "/login";
	}
	
	// https://itstory.tk/entry/Spring-Security-%ED%98%84%EC%9E%AC-%EB%A1%9C%EA%B7%B8%EC%9D%B8%ED%95%9C-%EC%82%AC%EC%9A%A9%EC%9E%90-%EC%A0%95%EB%B3%B4-%EA%B0%80%EC%A0%B8%EC%98%A4%EA%B8%B0
	@GetMapping("/hello")
	public ModelAndView main(Authentication authentication, ModelAndView model, @AuthenticationPrincipal Member member, Diary diary) {
		System.out.println(">>> Authentication >> " + authentication);
//		System.out.println(">>> Principal >> " + principal);
		System.out.println(">>> member >>>" + member);
		
		if(authentication == null) {
			model.setViewName("redirect:/");
			return model;
		} else {
			// 페이징 참고 : https://badstorage.tistory.com/13
			diary.setTotalcount(diaryService.selectTotalCnt());
			diary.setWriter(member.getUserIdx());
			
			diary.setPagenum(0);
			diary.setContentnum(7);
			diary.setCurrentblock(diary.getPagenum());
			diary.setLastblock(diary.getTotalcount());
			diary.setStartPage(1);
			diary.setEndPage(diary.getLastblock(), diary.getCurrentblock());
			
			
			model.addObject("paging", diary);
			model.addObject(diaryService.selectDiaryList(diary));
			model.setViewName("/main");
			
			return model;
		}
	}
}

package com.make.planner.module.member.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.make.planner.module.member.model.Member;
import com.make.planner.module.member.service.MemberService;



@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@GetMapping("/member/findAllMember")
	public String findAllMember(ModelMap map){
		List<Member> member = memberService.findAllMember();
		
		map.addAttribute(member);
		
		return "/test/memberTest";
	}
	
	@GetMapping("/fullcalendar")
	public String calendar() {
		return "/test/fullcalendar";
	}
	
	@PostMapping("/memeber/insertMember")
	public String insertMember(@RequestBody Member member) {
		Member findMember = memberService.findMemberById(member.getUserId());
		
		if(findMember == null) {
			memberService.mergeMember(member);
			return "등록완료";
		} else {
			return "아이디중복";
		}
	}
	
	@PostMapping("/member/updateMember")
	public String updateMember(@PathVariable String userId, @RequestBody Member member) {
		Member findMember = memberService.findMemberById(userId);
		
		if(findMember != null) {
			memberService.mergeMember(member);
			return "수정완료";
		} else {
			return "수정실패";
		}
	}
}

package com.make.planner.module.member.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.make.planner.module.member.model.Member;
import com.make.planner.module.member.service.MemberService;



@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@GetMapping("/joinUs")
	public String joinUs() {
		return "/join";
	}
	
	@ResponseBody
	@PostMapping("/joinProc")
	public Map<String, String> joinProc(Member member) {
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		Map<String, String> map = new HashMap<>();
		
		member.setUserPwd(pwdEncoder.encode(member.getUserPwd()));;
		
		Long mergeMember = memberService.mergeMember(member);
		System.out.println(mergeMember);
		if(mergeMember > 0) {
			map.put("result", "true");
			map.put("msg", member.getName() + "님 반갑습니다 :) <br> 회원가입이 완료되었습니다.");
		} else {
			map.put("result", "false");
			map.put("msg", "오류가 발생했습니다. 확인이 필요합니다.");
		}
		
		return map;
	} 
	
	@ResponseBody
	@PostMapping("/idCheck")
	public Map<String, String> idCheck(Member member) {
		
		Map<String, String> map = new HashMap<>();
		
		Member findMember = memberService.findId(member.getUserId());
		
		if(findMember != null) {
			map.put("result", "false");
			map.put("msg", "** This ID is already occupied :(");
		} else {
			map.put("result", "true");
			map.put("msg", "** You can use the ID :)");
		};
		
		return map;
	} 
	
}

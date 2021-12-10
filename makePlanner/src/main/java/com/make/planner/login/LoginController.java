package com.make.planner.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.make.planner.member.model.Member;
import com.make.planner.member.service.MemberService;

@Controller
public class LoginController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/")
	public String login() {
		return "/login";
	}
	
	@GetMapping("/hello")
	public String main() {
		return "/main";
	}

//	@RequestMapping(value = "/loginProc", method = {RequestMethod.POST})
//	@PostMapping("/loginProc")
//	public String login() {
//		return "/main";
//	}
	
	@GetMapping("/logout")
	public String logout() {
		return "/";
	}
	
	@GetMapping("/denied")
	public String denied() {
		return "/main";
	}
	
}

package com.make.planner.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.make.planner.member.service.MemberService;

@Controller
public class LoginController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/")
	public String login() {
		return "/login";
	}
	
	@GetMapping("/logout/result")
	public String logout() {
		return "/login";
	}
	
	@GetMapping("/hello")
	public String main() {
		return "/main";
	}

	@GetMapping("/denied")
	public String denied() {
		return "/main";
	}
	
}

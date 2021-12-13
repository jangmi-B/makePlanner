package com.make.planner.login;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.make.planner.member.service.MemberService;

@Controller
public class LoginController {
	
	@Autowired
	MemberService memberService;
	
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
	public String main(Principal principal, Authentication authentication) {
		System.out.println(">>> Authentication >> " + authentication);
		if(principal == null) {
			return "redirect:/";
		}
		return "/main";
	}

	@GetMapping("/denied")
	public String denied() {
		return "/main";
	}
	
}

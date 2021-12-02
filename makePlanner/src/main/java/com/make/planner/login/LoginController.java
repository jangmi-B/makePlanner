package com.make.planner.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	@GetMapping("/hello")
	public String main() {
		return "/main";
	}

	@PostMapping("/loginProc")
	public String login(String loginId, String loginPwd) {
		System.out.println(">>>>>");
		System.out.println(loginId);
		System.out.println(loginPwd);
		
		return "redirect:/hello";
	}
}

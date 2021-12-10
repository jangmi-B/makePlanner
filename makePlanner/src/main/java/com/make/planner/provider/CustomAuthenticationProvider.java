package com.make.planner.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.make.planner.member.model.Member;
import com.make.planner.member.service.MemberService;

public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private MemberService memberService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        
        Member member = (Member) memberService.loadUserByUsername(username);
        
        if(!matchPassword(password, member.getPassword())) {
            throw new BadCredentialsException(username);
        }
 
        if(!member.isEnabled()) {
            throw new BadCredentialsException(username);
        }
        System.out.println(">>>Authentication>>>>");
        return new UsernamePasswordAuthenticationToken(username, password, member.getAuthorities());

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
	
	private boolean matchPassword(String loginPwd, String password) {
        return loginPwd.equals(password);
    }

}

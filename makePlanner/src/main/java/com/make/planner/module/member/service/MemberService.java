package com.make.planner.module.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.make.planner.module.member.dao.MemberMapper;
import com.make.planner.module.member.model.Member;

import lombok.extern.slf4j.Slf4j;

/*
 * https://victorydntmd.tistory.com/328
 */
@Slf4j
@Service
public class MemberService implements UserDetailsService {
	
	@Autowired
	MemberMapper memberMapper;
	
	public Member findId(String userId) {
		return memberMapper.findId(userId);
	}
	
	public long mergeMember(Member member) {
		return memberMapper.mergeMember(member);
	}
	
	// https://taesan94.tistory.com/109?category=375242
	// https://to-dy.tistory.com/86?category=720806

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member member = memberMapper.findMemberById(username);

//		List<GrantedAuthority> authorities = new ArrayList<>();
//		
//		if (("admin").equals(username)) {
//			authorities.add(new SimpleGrantedAuthority("admin"));
//		} else {
//		    authorities.add(new SimpleGrantedAuthority("member"));
//		}
//		
//		return new User(member.getUserId(), member.getUserPwd(), authorities);
		
		if( member == null ) {
			log.debug("## 계정정보가 존재하지 않습니다. ##");
			throw new UsernameNotFoundException(username);
		}
		
		return member;
	}

}
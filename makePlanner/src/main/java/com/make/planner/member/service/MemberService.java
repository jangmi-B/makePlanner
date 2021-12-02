package com.make.planner.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.make.planner.member.dao.MemberMapper;
import com.make.planner.member.model.Member;

@Service
public class MemberService{
	
	@Autowired
	MemberMapper memberMapper;
	
	public List<Member> findAllMember(){
		return memberMapper.findAllMember();
	}
	
	public Member findMemberById(String userId) {
		return memberMapper.findMemberById(userId);
	}
	
	public void mergeMember(Member member) {
		memberMapper.mergeMember(member);
	}
	
}
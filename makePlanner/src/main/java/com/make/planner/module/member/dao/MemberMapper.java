package com.make.planner.module.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.make.planner.module.member.model.Member;


@Mapper
@Repository
public interface MemberMapper {
	public Member findId(String userId);
	
	public Member findMemberById(String userId);
	
	public void mergeMember(Member member);

}

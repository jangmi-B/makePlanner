package com.make.planner.module.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.make.planner.module.member.model.Member;


@Mapper
@Repository
public interface MemberMapper {
	List<Member> findAllMember();
	
	public Member findMemberById(String userId);
	
	public void mergeMember(Member member);

}

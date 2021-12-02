package com.make.planner.member.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	private int userIdx;
	
	private String userId;
	
	private String userPwd;
	
	private String userName;
	
	private String email;
	
	private String gender;
	
	private int age;
	
	private String phone;
	
	private String address;
	
	private String status;
}


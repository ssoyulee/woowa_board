package com.woowa.board.user.vo;

import com.woowa.board.user.code.UserRole;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRequest {

	private String userId;
	
	private String password;
	
	private String name;
	
	private String email;
	
	private UserRole role;
	
	private String writeId;
	
}

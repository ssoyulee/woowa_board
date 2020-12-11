package com.woowa.board.user.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
	
	MEMBER("ROLE_MEMBER"),
	ADMIN("ROLE_MEMBER");
	
	private String value;
}

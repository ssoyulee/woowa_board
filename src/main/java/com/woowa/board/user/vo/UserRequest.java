package com.woowa.board.user.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.woowa.board.user.code.UserRole;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRequest {

	@NotBlank(message = "ID는 필수값 입니다.")
	private String userId;
	
	@NotBlank(message = "비밀번호는 필수값 입니다.")
	private String password;
	
	@NotBlank(message = "이름은 필수값 입니다.")
	private String name;
	
	@NotBlank(message = "이메일은 필수값 입니다.")
	@Email(message = "이메일 형식에 맞지 않습니다.")
	private String email;
	
	@NotNull(message = "권한 정보는 필수값 입니다.")
	private UserRole role;
	
	@NotBlank(message = "등록자명은 필수값 입니다.")
	private String writeId;
	
}

package com.woowa.board.board.vo;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardRequest {

	@NotBlank(message = "게시판명은 필수값 입니다.")
	private String boardName;

	@NotBlank(message = "게시판설명은 필수값 입니다.")
	private String explanation;
	
	private String userId;
	
}

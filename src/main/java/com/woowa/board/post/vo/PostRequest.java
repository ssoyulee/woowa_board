package com.woowa.board.post.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostRequest {

	@NotNull(message = "게시판 정보가 존재하지 않습니다.")
	private Long boardId;
	
	@NotBlank(message = "게시물 제목이 존재하지 않습니다.")
	private String postTitle;
	
	@NotBlank(message = "게시물 내용이 존재하지 않습니다.")
	private String postContent;
	
	@NotBlank(message = "등록자 정보가 존재하지 않습니다.")
	private String userId;
	
}

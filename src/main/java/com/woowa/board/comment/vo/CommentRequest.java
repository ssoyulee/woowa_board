package com.woowa.board.comment.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentRequest {

	@NotNull(message = "게시물 정보가 존재하지 않습니다.")
	private Long postId;
	
	@NotBlank(message = "댓글 내용이 존재하지 않습니다.")
	private String commentContent;
	
	@NotBlank(message = "등록자ID가 존재하지 않습니다.")
	private String userId;
}

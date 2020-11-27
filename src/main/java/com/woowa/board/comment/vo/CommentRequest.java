package com.woowa.board.comment.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {

	private Long postId;
	
	private String commentContent;
	
	private String userId;
}

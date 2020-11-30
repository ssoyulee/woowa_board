package com.woowa.board.comment.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class CommentRequest {

	private Long postId;
	
	private String commentContent;
	
	private String userId;
}

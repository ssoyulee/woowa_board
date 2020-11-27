package com.woowa.board.post.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

	private Long boardId;
	
	private String postTitle;
	
	private String postContent;
	
	private String userId;
	
}

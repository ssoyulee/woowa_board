package com.woowa.board.board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardRequest {

	private String boardName;
	
	private String explanation;
	
	private String userId;
	
}

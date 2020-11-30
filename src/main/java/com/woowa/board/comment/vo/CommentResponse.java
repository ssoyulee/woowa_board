package com.woowa.board.comment.vo;

import java.util.List;

import com.woowa.board.comment.dao.Comment;
import com.woowa.board.common.dto.ResponseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse extends ResponseDto {

	private List<Comment> resultList;
	
}

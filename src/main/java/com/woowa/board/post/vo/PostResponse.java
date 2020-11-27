package com.woowa.board.post.vo;

import java.util.List;

import com.woowa.board.common.ResponseDto;
import com.woowa.board.post.dao.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponse extends ResponseDto {

	private List<Post> resultList;
}

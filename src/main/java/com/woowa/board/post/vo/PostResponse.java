package com.woowa.board.post.vo;

import java.util.List;

import com.woowa.board.common.dto.ResponseDto;
import com.woowa.board.post.dao.Post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostResponse extends ResponseDto {

	private List<Post> resultList;
}

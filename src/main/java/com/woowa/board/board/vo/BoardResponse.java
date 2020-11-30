package com.woowa.board.board.vo;

import java.util.List;

import com.woowa.board.board.dao.Board;
import com.woowa.board.common.dto.ResponseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResponse extends ResponseDto {

	private List<Board> resultList;
	
}

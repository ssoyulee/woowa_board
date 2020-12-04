package com.woowa.board.hacker.vo;

import java.util.List;

import com.woowa.board.common.dto.ResponseDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseHacker extends ResponseDto{

	List<Hacker> listHacker;
	
}

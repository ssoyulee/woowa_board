package com.woowa.board.user.vo;

import java.util.List;

import com.woowa.board.common.dto.ResponseDto;
import com.woowa.board.user.dao.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserResponse extends ResponseDto{
	
	private List<User> resultList;

}

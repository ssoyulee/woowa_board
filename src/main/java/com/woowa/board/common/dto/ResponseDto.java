package com.woowa.board.common.dto;

import com.woowa.board.common.code.ResponseCode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResponseDto {

	private String resultCode;
	
	private String resultMessage;
	
	public void setResponseCode(ResponseCode code) {
		this.resultCode = code.getCode();
		this.resultMessage = code.getMsg();
	}
	
}

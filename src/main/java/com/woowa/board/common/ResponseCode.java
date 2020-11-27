package com.woowa.board.common;

public enum ResponseCode {

	SUCCESS("00","성공"),
	FAIL("90","실패하였습니다."),
	IS_EMPTY("91","결과값이 존재하지 않습니다."),
	ERROR("99","오류발생");
	
	private String code;
	private String msg;
	
	private ResponseCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
}

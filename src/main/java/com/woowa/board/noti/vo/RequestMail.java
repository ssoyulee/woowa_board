package com.woowa.board.noti.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RequestMail {

    private String address;
    
    private String title;
    
    private String message;
    
    @Builder
    public RequestMail(String address, String title, String message) {
    	this.address = address;
    	this.title = title;
    	this.message = message;
    }
}

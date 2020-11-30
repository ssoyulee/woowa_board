package com.woowa.board.hacker.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Hacker {

	private String by;
	
	private Integer descendants;
	
	private Integer id;
	
	private List<Integer> kids;
	
	private Integer score;
	
	private Long time;
	
	private String title;
	
	private String type;
	
	private String url;
	
}

package com.woowa.board.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/board")
public class BoardContoller {

	@RequestMapping(path = "/index")
	public String index(Model model) throws Exception {
		return "index";
	}
	
}

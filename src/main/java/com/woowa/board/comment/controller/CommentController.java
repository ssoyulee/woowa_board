package com.woowa.board.comment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/comment")
public class CommentController {

	@RequestMapping(path = "/index")
	public String index(Model model) throws Exception {
		return "index";
	}
	
}

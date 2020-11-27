package com.woowa.board.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/comment")
public class CommentRestController {

	@Autowired
	private CommentService commentService;
	
	@RequestMapping(path = "/select")
	public String select(Model model) throws Exception {
		
		List<Comment> listComment = commentService.select();
		
		return "index";
	}
	
}

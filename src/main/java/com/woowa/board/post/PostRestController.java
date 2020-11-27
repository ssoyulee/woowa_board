package com.woowa.board.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/post")
public class PostRestController {

	@Autowired
	private PostService postService;
	
	@RequestMapping(path = "/select")
	public String select(Model model) throws Exception {
		
		List<Post> listPost = postService.select();
		
		return "index";
	}
}

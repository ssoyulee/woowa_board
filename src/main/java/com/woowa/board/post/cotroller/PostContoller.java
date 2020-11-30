package com.woowa.board.post.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/post")
public class PostContoller {

	@RequestMapping(path = "/detail")
	public String index(@RequestParam String postId, Model model) throws Exception {

		model.addAttribute("postId", postId);
		return "post/post_detail";
	}

	@RequestMapping(path = "/register")
	public String index(Model model) throws Exception {
		return "post/post_register";
	}
	
}

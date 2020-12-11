
package com.woowa.board.post.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(path = "/post")
public class PostContoller {

	@RequestMapping(path = "/detail")
	public String detail(@RequestParam String boardId, @RequestParam String postId, Model model) throws Exception {
		
		log.info("detail ::: boardId = > {} postId => {}", boardId, postId);
		
		model.addAttribute("boardId", boardId);
		model.addAttribute("postId", postId);
		return "post/postDetail";
	}

	@RequestMapping(path = "/form")
	public String form(@RequestParam String boardId, @RequestParam(required=false) String postId, Model model) throws Exception {
		
		log.info("form ::: boardId = > {} postId => {}", boardId, postId);
		
		model.addAttribute("boardId", boardId);
		model.addAttribute("postId", postId);
		return "post/postForm";
	}
	
}

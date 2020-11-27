package com.woowa.board.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/post")
public class PostContoller {

	@RequestMapping(path = "/index")
	public String index(Model model) throws Exception {
		return "index";
	}
	
}

package com.woowa.board.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@RequestMapping("/index")
	public String index(Model model) {
		
		model.addAttribute("test", "이원진");
		
		return "index";
	}
}

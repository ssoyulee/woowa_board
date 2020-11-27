package com.woowa.board.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(path = "/index")
	public String index(Model model) throws Exception {
		return "index";
	}
}

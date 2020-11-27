package com.woowa.board.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "/select")
	public String select(Model model) throws Exception {
		
		List<User> listUser = userService.select();
		
		return "index";
	}
	
	@RequestMapping(path = "/get/{userId}")
	public String selectUserId(@PathVariable String userId, Model model) throws Exception {
		
		String user = userService.selectUserId(userId);
		
		return userId;
	}
	
	
}

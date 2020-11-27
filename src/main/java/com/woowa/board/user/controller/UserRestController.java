package com.woowa.board.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woowa.board.user.dao.User;
import com.woowa.board.user.service.UserService;

@RestController
@RequestMapping(path = "/user/api")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/select")
	public String select() throws Exception {
		
		List<User> listUser = userService.select();
		
		return "index";
	}
	
	@GetMapping(path = "/get/{userId}")
	public String selectUserId(@PathVariable String userId) throws Exception {
		
		String user = userService.selectUserId(userId);
		
		return userId;
	}
	
	
}

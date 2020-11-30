package com.woowa.board.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.woowa.board.user.vo.UserRequest;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(path = "/index")
	public String index() throws Exception {
		return "user/login_popup";
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView index(@RequestBody UserRequest user, HttpServletResponse response) throws Exception {

		ModelAndView mv = new ModelAndView("jsonView");
		
		Cookie userCookie = new Cookie("userId", user.getUserId());
		userCookie.setPath("/");
        userCookie.setMaxAge(60*60*24*30);
        
        response.addCookie(userCookie);

        mv.addObject("resultCode","00");
        mv.addObject("resultMsg","SUCCESS");
        
		return mv;
	}
}

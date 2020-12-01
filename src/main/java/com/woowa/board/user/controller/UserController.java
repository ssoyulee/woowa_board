package com.woowa.board.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.woowa.board.user.service.UserService;
import com.woowa.board.user.vo.UserRequest;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "/index")
	public String index() throws Exception {
		return "user/login_popup";
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView login(@RequestBody UserRequest user, HttpServletResponse response) throws Exception {

		ModelAndView mv = new ModelAndView("jsonView");
		
		Cookie cookieUserId = new Cookie("userId", user.getUserId());
		cookieUserId.setPath("/");
		cookieUserId.setMaxAge(60*60*24*30);
        
        response.addCookie(cookieUserId);

		Cookie cookieRole= new Cookie("role", user.getUserId());
		cookieRole.setPath("/");
		cookieRole.setMaxAge(60*60*24*30);
        
		response.addCookie(cookieRole);
		
        mv.addObject("resultCode","00");
        mv.addObject("resultMsg","SUCCESS");
        
		return mv;
	}
	
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mv = new ModelAndView("jsonView");
		
		boolean isUserId = false;
		
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if ( cookie.getName().equals("userId")) {
				logger.info(cookie.getValue());
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				isUserId=true;
			}
		}

		if (isUserId) {
	        mv.addObject("resultCode","00");
	        mv.addObject("resultMsg","SUCCESS");
		}else{
	        mv.addObject("resultCode","99");
	        mv.addObject("resultMsg","로그인 정보가 존재하지 않습니다.");			
		}
		return mv;
	}
}

package com.woowa.board.user.controller;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.woowa.board.user.dao.UserAccount;
import com.woowa.board.user.service.UserService;
import com.woowa.board.user.vo.UserRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "/index")
	public String index() throws Exception {
		return "user/loginPopup";
	}
	
	
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mv = new ModelAndView("jsonView");
		
		boolean isUserId = false;
		String userId = null;
		
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if ( cookie.getName().equals("userId") || cookie.getName().equals("role") || cookie.getName().equals("ssId")) {
				log.info(cookie.getValue());
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				isUserId=true;
			}
			
			if ( cookie.getName().equals("userId") ) {
				userId = cookie.getValue();
			}
		}

		if (isUserId) {
			
			userService.updateSession(userId, null);
			
	        mv.addObject("resultCode","00");
	        mv.addObject("resultMsg","SUCCESS");
		}else{
	        mv.addObject("resultCode","99");
	        mv.addObject("resultMsg","로그인 정보가 존재하지 않습니다.");			
		}
		return mv;
	}
	
	@RequestMapping(path = "/error", method = RequestMethod.GET)
	public String error(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "error";
	}
}

package com.woowa.board.user.controller;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.woowa.board.user.dao.User;
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
		return "user/loginPopup";
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView login(@RequestBody UserRequest user, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    
		ModelAndView mv = new ModelAndView("jsonView");
		
		try {
			
			Optional<User> result = userService.getUserByUserIdAndPassword(user.getUserId(), user.getPassword());
			
			if ( !result.isPresent() ) {
				throw new Exception("회원 정보가 존재하지 않습니다.");
			}
			HttpSession session = request.getSession(true);
			
			User loginUser = result.get();
			Cookie cookieUserId = new Cookie("userId", loginUser.getUserId());
			cookieUserId.setPath("/");
			cookieUserId.setMaxAge(60*60*1);
	        
	        response.addCookie(cookieUserId);
	
			Cookie cookieRole= new Cookie("role", loginUser.getRole().name());
			cookieRole.setPath("/");
			cookieRole.setMaxAge(60*60*1);
	        
			response.addCookie(cookieRole);
	
			Cookie cookieSsid= new Cookie("ssId", session.getId());
			cookieSsid.setPath("/");
			cookieSsid.setMaxAge(60*60*1);
	        
			response.addCookie(cookieSsid);
			
			userService.updateSession(user.getUserId(), session.getId());
	        
	        mv.addObject("resultCode","00");
	        mv.addObject("resultMsg","SUCCESS");
	        
		} catch (Exception e) {
	        mv.addObject("resultCode","99");
	        mv.addObject("resultMsg",e.getMessage());
		}

		return mv;
	}
	
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mv = new ModelAndView("jsonView");
		
		boolean isUserId = false;
		String userId = null;
		
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if ( cookie.getName().equals("userId") || cookie.getName().equals("role") || cookie.getName().equals("ssId")) {
				logger.info(cookie.getValue());
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
}

package com.woowa.board.user.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.woowa.board.user.dao.UserAccount;
import com.woowa.board.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		try {
			Optional<UserAccount> result = userService.getUserById(authentication.getName());
			UserAccount loginUser = result.get();
			
			HttpSession session = request.getSession(true);
			
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
			
			userService.updateSession(authentication.getName(), session.getId());

			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("resultCode", "00");
			resultMap.put("resultMsg", "SUCCESS");
	        		
		    String json = new Gson().toJson(resultMap);
		    
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		    
		}catch (Exception e) {
			e.printStackTrace();
			log.error("오류");
		}
	}

}

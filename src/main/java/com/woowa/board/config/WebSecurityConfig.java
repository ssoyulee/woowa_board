package com.woowa.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.woowa.board.user.handler.LoginFailureHandler;
import com.woowa.board.user.handler.LoginSuccessHandler;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	
	@Autowired
	private LoginFailureHandler loginFailureHandler;
	
	private static final String[] AUTH_LIST = {
	    "/h2-console/**","/index","/static/**","/fragment/**","/layout/**","/user/**","/board/**","/post/**","/comment/**",
	    "/swagger-ui.html","/v2/api-docs", "/swagger-resources/**","/webjars/**","/swagger/**",
	    "/mail/**", "/hacker/**", "/favicon.ico"
	};
	
	private static final String[] ADMIN_AUTH_LIST = {
		    "/admin/**"
		};	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .antMatchers(AUTH_LIST).permitAll()
                    .antMatchers(ADMIN_AUTH_LIST).hasAuthority("ROLE_ADMIN")
                    .anyRequest().authenticated()
                .and()
                .csrf()
                	.ignoringAntMatchers(AUTH_LIST)
	            .and()
	            .headers()
	                .frameOptions()
	                .sameOrigin()
	            .and()
	            .formLogin()
	            	.loginPage("/user/defaultLogin")
	            	.loginProcessingUrl("/user/login")
	            	.usernameParameter("userId")
	            	.passwordParameter("password")
	            	.successHandler(loginSuccessHandler)
	            	.failureHandler(loginFailureHandler)
	            .and()
	            .logout()
	            	.logoutUrl("/user/logout")
	            	;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}

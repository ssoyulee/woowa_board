package com.woowa.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] AUTH_LIST = {
	    "/h2-console/**","/index","/static/**","/fragment/**","/layout/**","/user/**","/board/**","/post/**","/comment/**",
	    "/swagger-ui.html","/v2/api-docs", "/swagger-resources/**","/webjars/**","/swagger/**",
	    "/mail/**", "/hacker/**", "/favicon.ico",
	    "/admin/**"
	};
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .antMatchers(
                    		AUTH_LIST
                    ).permitAll()
//                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
	            .csrf()
	                .ignoringAntMatchers(AUTH_LIST)
	            .and()
	            .headers()
	                .frameOptions()
	                .sameOrigin();
//                    .addHeaderWriter(
//                        new XFrameOptionsHeaderWriter(
//                            new WhiteListedAllowFromStrategy(Arrays.asList("localhost"))    // 여기!
//                        )
//                    );
//                .formLogin()
//                    .loginPage("어쩌구")
//                    .defaultSuccessUrl("저쩌구")
//                    .permitAll()
//                .and()
//                .logout()
//                    .logoutSuccessUrl("얼씨구")
//                    .permitAll();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}

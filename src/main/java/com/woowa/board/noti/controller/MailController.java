package com.woowa.board.noti.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woowa.board.common.code.ResponseCode;
import com.woowa.board.common.dto.ResponseDto;
import com.woowa.board.noti.service.MailService;
import com.woowa.board.noti.vo.RequestMail;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 이원진
 *
 * 비밀번호 설정
 * https://velog.io/@max9106/Spring-Boot-Gmail-SMTP-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0%EB%A9%94%EC%9D%BC%EB%B3%B4%EB%82%B4%EA%B8%B0
 */
@Slf4j
@RestController
@RequestMapping("/mail/api")
public class MailController {

	@Autowired
	private MailService mailService;
	
	@ApiOperation(value = "메일 전송", notes = "메일을 전송하는 API")
    @ApiResponses({@ApiResponse(response = ResponseDto.class, code = 200, message = "OK")})
	@GetMapping("/send")
	public ResponseDto sendMain(@RequestParam String address, @RequestParam String title, @RequestParam String message) {
		
		ResponseDto response = new ResponseDto();

		try {
			RequestMail requestMail = RequestMail.builder()
					.address(address)
					.title(title)
					.message(message)
					.build();

			log.info("requestMail => " + requestMail);
			mailService.sendMail(requestMail);

    		response.setResponseCode(ResponseCode.SUCCESS);
	    	
		} catch (MessagingException e) {
			response.setResponseCode(ResponseCode.ERROR);
			response.setResultMessage(e.getMessage());
		}
		
		
		return response;
	}
}

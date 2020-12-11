package com.woowa.board.hacker.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woowa.board.common.code.ResponseCode;
import com.woowa.board.hacker.service.HacekerService;
import com.woowa.board.hacker.vo.Hacker;
import com.woowa.board.hacker.vo.ResponseHacker;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/hacker/api")
public class HacekerController {

	@Autowired
	private HacekerService hackerService;

	@ApiOperation(value = "해커스 최신 뉴스 조회", notes = "해커스 최신 뉴스를 조회 해오는 API")
    @ApiResponses({@ApiResponse(response = ResponseHacker.class, code = 200, message = "OK")})
	@GetMapping("/select")
	public ResponseHacker select() {
		
		ResponseHacker response = new ResponseHacker();
		List<Hacker> topNewsList = hackerService.selectNewstories();
		
    	if ( topNewsList.isEmpty() ) {
    		response.setResponseCode(ResponseCode.IS_EMPTY);
    	} else {
    		response.setResponseCode(ResponseCode.SUCCESS);
    		response.setListHacker(topNewsList);
    	}
		
		return response;
	}
}

package com.woowa.board.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woowa.board.common.code.ResponseCode;
import com.woowa.board.common.dto.ResponseDto;
import com.woowa.board.user.dao.User;
import com.woowa.board.user.service.UserService;
import com.woowa.board.user.vo.UserRequest;
import com.woowa.board.user.vo.UserResponse;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/user/api")
public class UserRestController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "사용자 정보 조회", notes = "사용자 정보를 조회 하는 기능")
    @ApiResponses({@ApiResponse(response = UserResponse.class, code = 200, message = "OK")})
	@GetMapping("/list")
	public UserResponse select() throws Exception {
		
		UserResponse response = new UserResponse();
		
		List<User> listUser = userService.select();
		
    	if ( listUser.isEmpty() ) {
    		response.setResponseCode(ResponseCode.IS_EMPTY);
    	} else {
    		response.setResponseCode(ResponseCode.SUCCESS);
    		response.setResultList(listUser);
    	}
		
		return response;
	}


	@ApiOperation(value = "사용자 정보 조회", notes = "사용자를 조회하는 기능")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId", value="사용자ID", required = true, dataTypeClass = String.class, paramType = "path", defaultValue = "admin"),
	})		
	@ApiResponses({@ApiResponse(response = UserResponse.class, code = 200, message = "OK")})
	@GetMapping(path = "/get/{userId}")
	public UserResponse getUser( @PathVariable String userId) throws Exception {

		log.info("userId =>" + userId);
		
		UserResponse response = new UserResponse();
		
		Optional<User> user = userService.getUserById(userId);
		
    	if ( user.isPresent() ) {
    		response.setResponseCode(ResponseCode.SUCCESS);
    		List<User> listUser = new ArrayList<User>();
    		listUser.add(user.get());
    		response.setResultList(listUser);
    	} else {
    		response.setResponseCode(ResponseCode.IS_EMPTY);
    	}

		return response;
	}
	

	@ApiOperation(value = "사용자 정보 입력", notes = "사용자 정보를 입력한다.")
	@ApiResponses({@ApiResponse(response = ResponseDto.class, code = 200, message = "OK")})
	@PostMapping(path = "/insert")
	public ResponseDto insertPost(@RequestBody UserRequest insertUser) throws Exception {

		log.info("insertUser =>" + insertUser);
		
		ResponseDto response = new ResponseDto();

		try {
			userService.insert(insertUser);
			response.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			response.setResponseCode(ResponseCode.ERROR);
			response.setResultMessage(e.getMessage());
		}

		return response;
	}

	
	@ApiOperation(value = "사용자 정보 수정", notes = "사용자를 수정한다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId", value="사용자ID", required = true, dataTypeClass = String.class, paramType = "path", defaultValue = "admin"),
	})		
	@ApiResponses({@ApiResponse(response = ResponseDto.class, code = 200, message = "OK")})
	@PutMapping(path = "/update/{userId}")
	public ResponseDto updateComment(@PathVariable String userId, @RequestBody UserRequest updateUser) throws Exception {

		log.info("userId =>" + userId);
		log.info("updateUser =>" + updateUser.toString());

		ResponseDto response = new ResponseDto();
		
		try {
			userService.update(userId, updateUser);
			response.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			response.setResponseCode(ResponseCode.ERROR);
			response.setResultMessage(e.getMessage());
		}
    	
		return response;
	}

	
	@ApiOperation(value = "사용자 정보 삭제", notes = "시용자를 삭제한다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId", value="사용자ID", required = true, dataTypeClass = String.class, paramType = "path", defaultValue = "admin"),
	})	
	@ApiResponses({@ApiResponse(response = ResponseDto.class, code = 200, message = "OK")})
	@DeleteMapping(path = "/delete/{userId}")
	public ResponseDto deleteUser(@PathVariable String userId) throws Exception {

		log.info("userId =>" + userId);
		
		ResponseDto response = new ResponseDto();
		
		try {
			userService.delete(userId);
			response.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			response.setResponseCode(ResponseCode.ERROR);
			response.setResultMessage(e.getMessage());
		}
    	
		return response;
	}
	
}

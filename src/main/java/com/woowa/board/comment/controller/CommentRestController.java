package com.woowa.board.comment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woowa.board.comment.dao.Comment;
import com.woowa.board.comment.service.CommentService;
import com.woowa.board.comment.vo.CommentRequest;
import com.woowa.board.comment.vo.CommentResponse;
import com.woowa.board.common.ResponseCode;
import com.woowa.board.common.ResponseDto;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/comment/api")
public class CommentRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommentService commentService;
	
	@ApiOperation(value = "댓글 정보 조회", notes = "댓글을 조회 하는 기능")
	@ApiImplicitParams({
		@ApiImplicitParam(name="delYn", value="삭제여부", required = false, dataTypeClass = String.class, paramType = "query"),
	})		
    @ApiResponses({@ApiResponse(response = CommentResponse.class, code = 200, message = "OK")})
	@GetMapping("/list")
	public CommentResponse select(@RequestParam(required = false) String delYn) throws Exception {
		
		CommentResponse response = new CommentResponse();
		
		List<Comment> listComment = commentService.select(delYn);
		
    	if ( listComment.isEmpty() ) {
    		response.setResponseCode(ResponseCode.IS_EMPTY);
    	} else {
    		response.setResponseCode(ResponseCode.SUCCESS);
    		response.setResultList(listComment);
    	}
		
		return response;
	}
	
	

	@ApiOperation(value = "댓글 정보 조회", notes = "게시물 번호로 댓글을 조회하는 기능")
	@ApiImplicitParams({
		@ApiImplicitParam(name="postId", value="게시물ID", required = true, dataTypeClass = Long.class, paramType = "path", defaultValue = "1"),
	})		
	@ApiResponses({@ApiResponse(response = CommentResponse.class, code = 200, message = "OK")})
	@GetMapping(path = "/get/{postId}")
	public CommentResponse selectPostId( @PathVariable Long postId) throws Exception {

		logger.info("postId =>" + postId);
		
		CommentResponse response = new CommentResponse();
		
		List<Comment> listComment = commentService.getCommentByPostId(postId);
		
    	if ( listComment.isEmpty() ) {
    		response.setResponseCode(ResponseCode.IS_EMPTY);
    	} else {
    		response.setResponseCode(ResponseCode.SUCCESS);
    		response.setResultList(listComment);
    	}

		return response;
	}


	@ApiOperation(value = "댓글 정보 입력", notes = "댓글을 신규로 생성할때 사용한다.")
	@ApiResponses({@ApiResponse(response = ResponseDto.class, code = 200, message = "OK")})
	@PostMapping(path = "/insert")
	public ResponseDto insertPost(@RequestBody CommentRequest insertComment) throws Exception {

		logger.info("insertComment =>" + insertComment);
		
		ResponseDto response = new ResponseDto();

		try {
			commentService.insert(insertComment);
			response.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			response.setResponseCode(ResponseCode.ERROR);
			response.setResultMessage(e.getMessage());
		}

		return response;
	}

	
	@ApiOperation(value = "댓글 정보 수정", notes = "댓글을 수정한다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name="commentId", value="댓글ID", required = true, dataTypeClass = Long.class, paramType = "path", defaultValue = "1"),
	})		
	@ApiResponses({@ApiResponse(response = ResponseDto.class, code = 200, message = "OK")})
	@PutMapping(path = "/update/{commentId}")
	public ResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequest updateComment) throws Exception {

		logger.info("commentId =>" + commentId);
		logger.info("updateComment =>" + updateComment.toString());

		ResponseDto response = new ResponseDto();
		
		try {
			commentService.update(commentId, updateComment);
			response.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			response.setResponseCode(ResponseCode.ERROR);
			response.setResultMessage(e.getMessage());
		}
    	
		return response;
	}

	
	@ApiOperation(value = "댓글 정보 삭제", notes = "댓글을 삭제한다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name="commentId", value="댓글ID", required = true, dataTypeClass = Long.class, paramType = "path", defaultValue = "1"),
	})	
	@ApiResponses({@ApiResponse(response = ResponseDto.class, code = 200, message = "OK")})
	@DeleteMapping(path = "/delete/{commentId}")
	public ResponseDto deleteComment(@PathVariable Long commentId) throws Exception {

		logger.info("commentId =>" + commentId);
		
		ResponseDto response = new ResponseDto();
		
		try {
			commentService.delete(commentId);
			response.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			response.setResponseCode(ResponseCode.ERROR);
			response.setResultMessage(e.getMessage());
		}
    	
		return response;
	}
	
}

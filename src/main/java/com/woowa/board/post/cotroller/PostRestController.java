package com.woowa.board.post.cotroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woowa.board.common.code.ResponseCode;
import com.woowa.board.common.dto.ResponseDto;
import com.woowa.board.post.dao.Post;
import com.woowa.board.post.service.PostService;
import com.woowa.board.post.vo.PostRequest;
import com.woowa.board.post.vo.PostResponse;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/post/api")
public class PostRestController {

	@Autowired
	private PostService postService;
	
	@ApiOperation(value = "게시물 정보 조회", notes = "게시물을 조회 하는 기능")
	@ApiImplicitParams({
		@ApiImplicitParam(name="delYn", value="삭제여부", required = false, dataTypeClass = String.class, paramType = "query"),
	})		
    @ApiResponses({@ApiResponse(response = PostResponse.class, code = 200, message = "OK")})
	@GetMapping("/list")
	public PostResponse select(@RequestParam(required = false) String delYn) throws Exception {
		
		PostResponse response = new PostResponse();
		
		List<Post> listPost = postService.select(delYn);
		
    	if ( listPost.isEmpty() ) {
    		response.setResponseCode(ResponseCode.IS_EMPTY);
    	} else {
    		response.setResponseCode(ResponseCode.SUCCESS);
    		response.setResultList(listPost);
    	}
		
		return response;
	}
	
	
	@ApiOperation(value = "게시물 정보 조회", notes = "게시판 번호로 게시물을 조회하는 기능")
	@ApiImplicitParams({
		@ApiImplicitParam(name="boardId", value="게시판ID", required = true, dataTypeClass = Long.class, paramType = "path", defaultValue = "1"),
	})		
	@ApiResponses({@ApiResponse(response = PostResponse.class, code = 200, message = "OK")})
	@GetMapping(path = "/select/{boardId}")
	public PostResponse selectPostByBoardId( @PathVariable Long boardId) throws Exception {

		log.info("boardId =>" + boardId);
		
		PostResponse response = new PostResponse();
		
		List<Post> listPost = postService.selectPostByBoardId(boardId);
		
    	if ( listPost.isEmpty() ) {
    		response.setResponseCode(ResponseCode.IS_EMPTY);
    	} else {
    		response.setResponseCode(ResponseCode.SUCCESS);
    		response.setResultList(listPost);
    	}

		return response;
	}


    @ApiOperation(value = "게시물 정보 조회(페이징)", notes = "게시물 정보 조회하는 기능(페이징)")
	@ApiImplicitParams({
		@ApiImplicitParam(name="boardId", value="게시판ID", required = true, dataTypeClass = Long.class, paramType = "path", defaultValue = "1"),
		@ApiImplicitParam(name="page", value="페이지", required = false, dataTypeClass = Integer.class, paramType = "query"),
		@ApiImplicitParam(name="pageCount", value="페이지게시물수", required = false, dataTypeClass = Integer.class, paramType = "query"),
	})		
    @ApiResponses({@ApiResponse(response = PostResponse.class, code = 200, message = "OK")})
    @GetMapping(path = "/page/{boardId}")
	public PostResponse page(
			@PathVariable Long boardId,
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer pageCount
			) throws Exception {
    		
    	log.info("boardId => "+boardId+" page => "+page+" pageCount =>"+pageCount);
    	PostResponse response = new PostResponse();
		
    	if ( page == null ) {
    		page = 1;
    	}
    	if ( pageCount == null ) {
    		pageCount = 1;
    	}
    	Page<Post> pagePost = postService.findAllByBoardIdAndDelYn(boardId, page, pageCount);
    	
    	if ( pagePost.isEmpty() ) {
    		response.setResponseCode(ResponseCode.IS_EMPTY);
    	} else {
    		response.setTotalCount(pagePost.getTotalElements());
    		response.setTotalPage(pagePost.getTotalPages());
    		response.setResponseCode(ResponseCode.SUCCESS);
    		response.setResultList(pagePost.getContent());
    	}
		
		return response;
	}
    
    
	@ApiOperation(value = "게시물 정보 조회", notes = "게시물을 조회하는 기능")
	@ApiImplicitParams({
		@ApiImplicitParam(name="postId", value="게시글ID", required = true, dataTypeClass = Long.class, paramType = "path", defaultValue = "1"),
	})		
	@ApiResponses({@ApiResponse(response = PostResponse.class, code = 200, message = "OK")})
	@GetMapping(path = "/get/{postId}")
	public PostResponse getPost( @PathVariable Long postId) throws Exception {

		log.info("boardId =>" + postId);
		
		PostResponse response = new PostResponse();
		
		Optional<Post> post = postService.selectPostById(postId);
		
    	if ( post.isPresent() ) {
    		List<Post> listPost = new ArrayList<Post>();
    		listPost.add(post.get());
    		response.setResponseCode(ResponseCode.SUCCESS);
    		response.setResultList(listPost);
    	} else {
    		response.setResponseCode(ResponseCode.IS_EMPTY);
    	}

		return response;
	}


	@ApiOperation(value = "게시물 정보 입력", notes = "게시물을 신규로 생성할때 사용한다.")
	@ApiResponses({@ApiResponse(response = ResponseDto.class, code = 200, message = "OK")})
	@PostMapping(path = "/insert")
	public ResponseDto insertPost(@RequestBody PostRequest insertPost) throws Exception {

		log.info("insertPost =>" + insertPost);
		
		ResponseDto response = new ResponseDto();

		try {
			postService.insert(insertPost);
			response.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			response.setResponseCode(ResponseCode.ERROR);
			response.setResultMessage(e.getMessage());
		}

		return response;
	}

	
	@ApiOperation(value = "게시물 정보 수정", notes = "게시물을 수정한다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name="postId", value="게시물ID", required = true, dataTypeClass = Long.class, paramType = "path", defaultValue = "1"),
	})		
	@ApiResponses({@ApiResponse(response = ResponseDto.class, code = 200, message = "OK")})
	@PutMapping(path = "/update/{postId}")
	public ResponseDto updatePost(@PathVariable Long postId, @RequestBody PostRequest updatePost) throws Exception {

		log.info("postId =>" + postId);
		log.info("updatePost =>" + updatePost.toString());

		ResponseDto response = new ResponseDto();
		
		try {
			postService.update(postId, updatePost);
			response.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			response.setResponseCode(ResponseCode.ERROR);
			response.setResultMessage(e.getMessage());
		}
    	
		return response;
	}

	
	@ApiOperation(value = "게시물 정보 삭제", notes = "게시물을 삭제한다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name="postId", value="게시물ID", required = true, dataTypeClass = Long.class, paramType = "path", defaultValue = "1"),
	})	
	@ApiResponses({@ApiResponse(response = ResponseDto.class, code = 200, message = "OK")})
	@DeleteMapping(path = "/delete/{postId}")
	public ResponseDto deletePost(@PathVariable Long postId) throws Exception {

		log.info("postId =>" + postId);
		
		ResponseDto response = new ResponseDto();
		
		try {
			postService.delete(postId);
			response.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			response.setResponseCode(ResponseCode.ERROR);
			response.setResultMessage(e.getMessage());
		}
    	
		return response;
	}
	
}

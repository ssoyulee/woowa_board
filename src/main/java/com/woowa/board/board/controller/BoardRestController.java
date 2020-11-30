package com.woowa.board.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.woowa.board.board.dao.Board;
import com.woowa.board.board.service.BoardService;
import com.woowa.board.board.vo.BoardRequest;
import com.woowa.board.board.vo.BoardResponse;
import com.woowa.board.common.code.ResponseCode;
import com.woowa.board.common.dto.ResponseDto;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/board/api")
public class BoardRestController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardService boardService;

    @ApiOperation(value = "전체 게시판 정보 조회", notes = "전체 게시판 정보 조회하는 기능")
	@ApiImplicitParams({
		@ApiImplicitParam(name="delYn", value="삭제여부", required = false, dataTypeClass = String.class, paramType = "query"),
	})		
    @ApiResponses({@ApiResponse(response = BoardResponse.class, code = 200, message = "OK")})
	@GetMapping("/list")
	public BoardResponse select(@RequestParam(required = false) String delYn) throws Exception {

    	BoardResponse response = new BoardResponse();
		
    	List<Board> listBoard = boardService.select(delYn);
    	
    	if ( listBoard.isEmpty() ) {
    		response.setResponseCode(ResponseCode.IS_EMPTY);
    	} else {
    		response.setResponseCode(ResponseCode.SUCCESS);
    		response.setResultList(listBoard);
    	}
		
		return response;
	}
    
	
	@ApiOperation(value = "게시판 정보 조회", notes = "게시판 번호로 게시판을 조회하는 기능")
	@ApiImplicitParams({
		@ApiImplicitParam(name="boardId", value="게시판ID", required = true, dataTypeClass = Long.class, paramType = "path", defaultValue = "1"),
	})		
	@ApiResponses({@ApiResponse(response = BoardResponse.class, code = 200, message = "OK")})
	@GetMapping(path = "/get/{boardId}")
	public BoardResponse selectBoardId( @PathVariable Long boardId) throws Exception {

		logger.info("boardId =>" + boardId);
		
		BoardResponse response = new BoardResponse();
		
		Optional<Board> board = boardService.getBoardById(boardId);
    	if ( board.isPresent() ) {
    		response.setResponseCode(ResponseCode.SUCCESS);
        	List<Board> resultList = new ArrayList<Board>();
        	resultList.add(board.get());
        	response.setResultList(resultList);
    	} else {
    		response.setResponseCode(ResponseCode.IS_EMPTY);
    	}

		return response;
	}


	@ApiOperation(value = "게시판 정보 입력", notes = "게시판을 신규로 생성할때 사용한다.")
	@ApiResponses({@ApiResponse(response = ResponseDto.class, code = 200, message = "OK")})
	@PostMapping(path = "/insert")
	public ResponseDto insertBoard(@RequestBody BoardRequest insertBoard) throws Exception {

		logger.info("insertBoard =>" + insertBoard);
		
		ResponseDto response = new ResponseDto();

		try {
			boardService.insert(insertBoard);
			response.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			response.setResponseCode(ResponseCode.ERROR);
			response.setResultMessage(e.getMessage());
		}

		return response;
	}

	
	@ApiOperation(value = "게시판 정보 수정", notes = "게시판을 수정한다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name="boardId", value="게시판ID", required = true, dataTypeClass = Long.class, paramType = "path", defaultValue = "1"),
	})		
	@ApiResponses({@ApiResponse(response = ResponseDto.class, code = 200, message = "OK")})
	@PutMapping(path = "/update/{boardId}")
	public ResponseDto updateBoard(@PathVariable Long boardId, @RequestBody BoardRequest updateBoard) throws Exception {

		logger.info("boardId =>" + boardId);
		logger.info("updateBoard =>" + updateBoard.toString());

		ResponseDto response = new ResponseDto();
		
		try {
			boardService.update(boardId, updateBoard);
			response.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			response.setResponseCode(ResponseCode.ERROR);
			response.setResultMessage(e.getMessage());
		}
    	
		return response;
	}

	
	@ApiOperation(value = "게시판 정보 삭제", notes = "게시판을 삭제한다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name="boardId", value="게시판ID", required = true, dataTypeClass = Long.class, paramType = "path", defaultValue = "1"),
	})		
	@ApiResponses({@ApiResponse(response = ResponseDto.class, code = 200, message = "OK")})
	@DeleteMapping(path = "/delete/{boardId}")
	public ResponseDto deleteBoard(@PathVariable Long boardId) throws Exception {

		logger.info("boardId =>" + boardId);
		
		ResponseDto response = new ResponseDto();
		
		try {
			boardService.delete(boardId);
			response.setResponseCode(ResponseCode.SUCCESS);
		}catch (Exception e) {
			response.setResponseCode(ResponseCode.ERROR);
			response.setResultMessage(e.getMessage());
		}
    	
		return response;
	}
	
    
}

package com.woowa.board.board.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woowa.board.board.dao.Board;
import com.woowa.board.board.dao.BoardRepository;
import com.woowa.board.board.vo.BoardRequest;

@Service
public class BoardService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardRepository boardRepository;
	
	public List<Board> select(String delYn) throws Exception {
		
		if ( delYn != null && !delYn.isEmpty() ) {
			return boardRepository.findAllByDelYn(delYn);	
		}
		
		return boardRepository.findAll();
	}
	
	public Optional<Board> getBoardById(Long boardId) throws Exception {
		
		Optional<Board> board = boardRepository.findById(boardId);

		return board;
	}
	
	@Transactional
	public void insert(BoardRequest insertBoard) throws Exception {
		
		Board board = Board.builder()
						.boardName(insertBoard.getBoardName())
						.explanation(insertBoard.getExplanation())
						.delYn("N")
						.regpeId(insertBoard.getUserId())
						.modpeId(insertBoard.getUserId())
						.build();

		boardRepository.save(board);

		logger.info("success insertBoard => " + insertBoard);
		
	}
	
	@Transactional
	public void update(Long boardId, BoardRequest updateBoard) throws Exception {
		
		Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 게시판이 없습니다. boardId=" + boardId));
		
		board.setBoardName(updateBoard.getBoardName());
		board.setExplanation(updateBoard.getExplanation());
		board.setModpeId(updateBoard.getUserId());

		boardRepository.save(board);
		
		logger.info("success updateBoard => " + board);
		
	}
	
	@Transactional
	public void delete(Long boardId) throws Exception {
		
		Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 게시판이 없습니다. boardId=" + boardId));
		
		board.setDelYn("Y");
		
		boardRepository.save(board);
		
		logger.info("success deleteBoard => " + boardId);

	}
	
	
}

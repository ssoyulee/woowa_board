package com.woowa.board.board.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woowa.board.board.dao.Board;
import com.woowa.board.board.dao.BoardRepository;
import com.woowa.board.board.vo.BoardRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public List<Board> select(String delYn) throws Exception {
		
		log.info("select delYn => " + delYn);
		
		if ( delYn != null && !delYn.isEmpty() ) {
			return boardRepository.findAllByDelYn(delYn);	
		}
		
		return boardRepository.findAll();
	}
	
	public Optional<Board> getBoardById(Long boardId) throws Exception {
		
		log.info("getBoardById boardId => " + boardId);
		
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

		log.info("success insertBoard => " + insertBoard);
		
	}
	
	@Transactional
	public void update(Long boardId, BoardRequest updateBoard) throws Exception {
		
		Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 게시판이 없습니다. boardId=" + boardId));
		
		board.setBoardName(updateBoard.getBoardName());
		board.setExplanation(updateBoard.getExplanation());
		board.setModpeId(updateBoard.getUserId());

		boardRepository.save(board);
		
		log.info("success updateBoard => " + board);
		
	}
	
	@Transactional
	public void delete(Long boardId) throws Exception {
		
		Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 게시판이 없습니다. boardId=" + boardId));
		
		board.setDelYn("Y");
		
		boardRepository.save(board);
		
		log.info("success deleteBoard => " + boardId);

	}
	
	
}

package com.woowa.board.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public List<Board> select() throws Exception {
		
		List<Board> listBoard = boardRepository.findAll();
		for ( Board board :  listBoard) {
			System.out.println(board.toString());
		}
		
		return listBoard;
	}
	
}

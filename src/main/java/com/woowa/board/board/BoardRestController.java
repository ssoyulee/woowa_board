package com.woowa.board.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/board")
public class BoardRestController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(path = "/select")
	public String select(Model model) throws Exception {

		List<Board> listBoard = boardService.select();
		
		return "index";
	}
}

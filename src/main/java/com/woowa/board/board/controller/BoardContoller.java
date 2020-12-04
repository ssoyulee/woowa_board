

package com.woowa.board.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/board")
public class BoardContoller {

	@RequestMapping(path = "/index")
	public String index(@RequestParam Long boardId, Model model) throws Exception {

		model.addAttribute("boardId", boardId);
		return "board/boardIndex";
	}
	
}

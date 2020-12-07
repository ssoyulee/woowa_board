package com.woowa.board.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

	@RequestMapping(path = "/index")
	public String index(Model model) throws Exception {
		return "admin/adminIndex";
	}

	@RequestMapping(path = "/manage/user")
	public String manageUser(Model model) throws Exception {
		return "admin/tab/manageUser";
	}

	@RequestMapping(path = "/manage/board")
	public String manageBoard(Model model) throws Exception {
		return "admin/tab/manageBoard";
	}

	@RequestMapping(path = "/manage/post")
	public String managePost(Model model) throws Exception {
		return "admin/tab/managePost";
	}

	@RequestMapping(path = "/manage/comment")
	public String manageComment(Model model) throws Exception {
		return "admin/tab/manageComment";
	}
}

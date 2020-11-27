package com.woowa.board.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	public List<Comment> select() throws Exception {
		
		List<Comment> listComment = commentRepository.findAll();
		for ( Comment comment : listComment ) {
			System.out.println(comment.toString());
		}
		
		return listComment;
	}
	
}

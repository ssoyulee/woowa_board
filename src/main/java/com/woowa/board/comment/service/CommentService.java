package com.woowa.board.comment.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woowa.board.comment.dao.Comment;
import com.woowa.board.comment.dao.CommentRepository;
import com.woowa.board.comment.vo.CommentRequest;

@Service
public class CommentService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommentRepository commentRepository;
	
	public List<Comment> select(String delYn) throws Exception {
		
		if ( delYn != null && !delYn.isEmpty() ) {
			return commentRepository.findAllByDelYn(delYn);	
		}
		
		return commentRepository.findAll();
	}
	
	public List<Comment> getCommentByPostId(Long postId) throws Exception {
		
		String delYn = "N";			// 지워지지 않은 댓글만 가져온다.
		List<Comment> list_comment = commentRepository.selectCommentByPostId(postId, delYn);

		logger.info("postId => "+ postId + " comment count => " + list_comment.size());
		
		return list_comment;
	}
	
	@Transactional
	public void insert(CommentRequest insertComment) throws Exception {
		
		// TODO : POSTID가 존재하는지 확인하자
		
		Comment comment = Comment.builder()
						.postId(insertComment.getPostId())		
						.commentContent(insertComment.getCommentContent())
						.delYn("N")
						.regpeId(insertComment.getUserId())
						.modpeId(insertComment.getUserId())
						.build();

		commentRepository.save(comment);

		logger.info("success insertComment => " + insertComment);
				
	}
	
	@Transactional
	public void update(Long commentId, CommentRequest updateComment) throws Exception {
		
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. commentId=" + commentId));
		
		comment.setCommentContent(updateComment.getCommentContent());
		comment.setModpeId(updateComment.getUserId());

		commentRepository.save(comment);
		
		logger.info("success updateComment => " + comment);
		
	}
	
	@Transactional
	public void delete(Long commentId) throws Exception {
		
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. commentId=" + commentId));
		
		comment.setDelYn("Y");
		
		commentRepository.save(comment);

		logger.info("success deleteComment => " + commentId);
		
	}
	
}

package com.woowa.board.comment.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woowa.board.comment.dao.Comment;
import com.woowa.board.comment.dao.CommentRepository;
import com.woowa.board.comment.vo.CommentRequest;
import com.woowa.board.noti.service.MailService;
import com.woowa.board.noti.vo.RequestMail;
import com.woowa.board.post.dao.Post;
import com.woowa.board.post.service.PostService;
import com.woowa.board.user.dao.UserAccount;
import com.woowa.board.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentService {

	@Autowired
	private MailService mailService; 
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentRepository commentRepository;
	
	public List<Comment> select(String delYn) throws Exception {
		
		if ( delYn != null && !delYn.isEmpty() ) {
			return commentRepository.findAllByDelYn(delYn);	
		}
		
		return commentRepository.findAll();
	}
	
	public List<Comment> getCommentByPostId(Long postId, String delYn) throws Exception {
		
		if ( delYn == null || delYn.isEmpty() ) {
			delYn = "N";			// 지워지지 않은 댓글만 가져온다.
		}
		
		List<Comment> list_comment = commentRepository.findAllByPostIdAndDelYnOrderByCommentIdDesc(postId, delYn);

		log.info("postId => "+ postId + " comment count => " + list_comment.size());
		
		return list_comment;
	}
	
	@Transactional
	public void insert(CommentRequest insertComment) throws Exception {
		
		// 이메일을 전송한다.
		Post post = postService.selectPostById(insertComment.getPostId()).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. postId =" + insertComment.getPostId()));
		UserAccount user = userService.getUserById(post.getRegpeId()).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. userId =" + post.getRegpeId()));
		
		RequestMail requestMail = RequestMail.builder()
									.address(user.getEmail())
									.title("[알림] 댓글 등록 알림")
									.message("작성하신 게시글에 댓글이 등록되었습니다")
									.build();
		mailService.sendMail(requestMail);
		
		Comment comment = Comment.builder()
						.postId(insertComment.getPostId())		
						.commentContent(insertComment.getCommentContent())
						.delYn("N")
						.regpeId(insertComment.getUserId())
						.modpeId(insertComment.getUserId())
						.build();

		commentRepository.save(comment);

		log.info("success insertComment => " + insertComment);
				
	}
	
	@Transactional
	public void update(Long commentId, CommentRequest updateComment) throws Exception {
		
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. commentId=" + commentId));
		
		comment.setCommentContent(updateComment.getCommentContent());
		comment.setModpeId(updateComment.getUserId());

		commentRepository.save(comment);
		
		log.info("success updateComment => " + comment);
		
	}
	
	@Transactional
	public void delete(Long commentId) throws Exception {
		
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. commentId=" + commentId));
		
		comment.setDelYn("Y");
		
		commentRepository.save(comment);

		log.info("success deleteComment => " + commentId);
		
	}
	
}

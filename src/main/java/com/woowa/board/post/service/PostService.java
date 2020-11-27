package com.woowa.board.post.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woowa.board.post.dao.Post;
import com.woowa.board.post.dao.PostRepository;
import com.woowa.board.post.vo.PostRequest;

@Service
public class PostService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostRepository postRepository;
	
	public List<Post> select(String delYn) throws Exception {
		
		if ( delYn != null && !delYn.isEmpty() ) {
			return postRepository.findAllByDelYn(delYn);	
		}
		
		return postRepository.findAll();
		
	}
	
	public List<Post> selectPostByBoardId(Long boardId) throws Exception {
		
		String delYn = "N";			// 지워지지 않은 게시물만 가져온다.
		List<Post> list_post = postRepository.selectPostByBoardId(boardId, delYn);

		logger.info("boardId => "+ boardId + " post count => " + list_post.size());
		
		return list_post;
	}
	
	public Optional<Post> selectPostById(Long postId) throws Exception {
		
		Optional<Post> post = postRepository.findById(postId);

		return post;
	}
	
	@Transactional
	public void insert(PostRequest insertPost) throws Exception {
		
		// TODO : BOARDID가 존재하는지 확인하자
		
		Post post = Post.builder()
						.boardId(insertPost.getBoardId())		
						.postTitle(insertPost.getPostTitle())
						.postContent(insertPost.getPostContent())
						.delYn("N")
						.regpeId(insertPost.getUserId())
						.modpeId(insertPost.getUserId())
						.build();

		postRepository.save(post);

		logger.info("success insertPost => " + insertPost);
				
	}
	
	@Transactional
	public void update(Long postId, PostRequest updatePost) throws Exception {
		
		Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. commentId=" + postId));
		
		post.setPostTitle(updatePost.getPostTitle());
		post.setPostContent(updatePost.getPostContent());
		post.setModpeId(updatePost.getUserId());

		postRepository.save(post);
		
		logger.info("success updatePost => " + post);
		
	}
	
	@Transactional
	public void delete(Long postId) throws Exception {
		
		Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. commentId=" + postId));
		
		post.setDelYn("Y");
		
		postRepository.save(post);

		logger.info("success deletePost => " + postId);
		
	}
	
	
}

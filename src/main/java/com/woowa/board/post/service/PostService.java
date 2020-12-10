package com.woowa.board.post.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.woowa.board.post.dao.Post;
import com.woowa.board.post.dao.PostRepository;
import com.woowa.board.post.vo.PostRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public List<Post> select(String delYn) throws Exception {
		
		List<Post> list_post = null;
		if ( delYn != null && !delYn.isEmpty() ) {
			list_post =postRepository.findAllByDelYn(delYn);	
		}else {
			list_post = postRepository.findAll();
		}

		log.info("select delYn => {} post count => ", delYn, list_post.size());
		
		return list_post;
		
	}
	
	public List<Post> selectPostByBoardId(Long boardId) throws Exception {
		
		List<Post> list_post = postRepository.findAllByBoardId(boardId);

		log.info("selectPostByBoardId boardId => {} post count => {}",boardId, list_post.size());
		
		return list_post;
	}

	public Page<Post> findAllByBoardIdAndDelYn(Long boardId, Integer page, Integer pageCount) throws Exception {
		
		String delYn = "N";			// 지워지지 않은 게시물만 가져온다.
		Pageable pa = PageRequest.of(page-1, pageCount, Direction.DESC, "postId");
		Page<Post> page_post = postRepository.findAllByBoardIdAndDelYn(boardId, delYn, pa);
		
		log.info("selectPostByBoardId boardId => {} page => {} pageCount => {} post count => {}",boardId, page, pageCount, page_post.getSize());
		
		return page_post;
		
	}

	public List<Post> findByRegpeIdAndRegDtsBetween(String regpeId) throws Exception {
		
		LocalDateTime startDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
		LocalDateTime endDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
		
		List<Post> list_post = postRepository.findByRegpeIdAndRegDtsBetween(regpeId, startDate, endDate);
		
		log.info("findByRegpeIdAndRegDtsBetween regpeId => {} startDate => {} endDate => {} list_post => {}",regpeId, startDate, endDate, list_post.size());
		
		return list_post;
		
	}
	
	public Optional<Post> selectPostById(Long postId) throws Exception {
		
		Optional<Post> post = postRepository.findById(postId);
		return post;
	}
	
	public boolean stopword(String content) {

        Pattern p = Pattern.compile("배달통|요기요|쿠팡잇츠|위메프오|배고파", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(content);
        
        return m.find();
        
	}
	
	public boolean stopurl(String content) {

//        Pattern p = Pattern.compile("(href)+[\\s*(=)\"]+(https?://)*(www.)*([naver|daum])+([.com|co.kr])?", Pattern.CASE_INSENSITIVE);
		Pattern p = Pattern.compile("(href)+[\\s*(=)\"]+(https?://)*(www.)*(\\S)*(naver|daum)+(.com|co.kr)", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(content);
        return m.find();
        
	}
	
	@Transactional
	public void insert(PostRequest insertPost) throws Exception {
		
		// 게시글을 하루(당일, 00시 넘ㅇ너가면 초기화)에 5개 초과로 작성할 수 없습니다.
		List<Post> list_post = findByRegpeIdAndRegDtsBetween(insertPost.getUserId());
		log.info("insert regpe_id = > {} count_post => {}", insertPost.getUserId(), list_post.size());
		if (list_post.size() >= 5) throw new Exception("게시글은 하루 최대 5개를 초과하여 작성할 수 없습니다.");
		
		// 금칙어 존재
		boolean isStopword = stopword(insertPost.getPostContent());
		if (isStopword) throw new Exception("게시글에 금칙어가 존재합니다.");
		
		// 금칙어 존재
		boolean isStopurl = stopurl(insertPost.getPostContent());
		if (isStopurl) throw new Exception("게시글에 네이버 다음 URL 존재합니다.");
		
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

		log.info("success insertPost => " + insertPost);
				
	}
	
	@Transactional
	public void update(Long postId, PostRequest updatePost) throws Exception {
		
		Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. commentId=" + postId));

		// 금칙어 존재
		boolean isStopword = stopword(updatePost.getPostContent());
		if (isStopword) throw new Exception("게시글에 금칙어가 존재합니다.");
		
		// 금칙어 존재
		boolean isStopurl = stopurl(updatePost.getPostContent());
		if (isStopurl) throw new Exception("게시글에 네이버 다음 URL 존재합니다.");
		
		post.setPostTitle(updatePost.getPostTitle());
		post.setPostContent(updatePost.getPostContent());
		post.setModpeId(updatePost.getUserId());

		postRepository.save(post);
		
		log.info("success updatePost => " + post);
		
	}
	
	@Transactional
	public void delete(Long postId) throws Exception {
		
		Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. commentId=" + postId));
		
		post.setDelYn("Y");
		
		postRepository.save(post);

		log.info("success deletePost => " + postId);
		
	}
	
	
}

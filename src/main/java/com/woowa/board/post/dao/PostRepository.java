package com.woowa.board.post.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>{

	List<Post> findAllByDelYn(String delYn);
	
	List<Post> findAllByBoardId(Long boardId);
	
	Page<Post> findAllByBoardIdAndDelYn(Long boardId, String delYn, Pageable page);
	
	List<Post> findByRegpeIdAndRegDtsBetween(String regpeId, LocalDateTime startDt, LocalDateTime endDt);
}

package com.woowa.board.post.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long>{

	List<Post> findAllByDelYn(String delYn);
	
	@Query("SELECT p from post p " + 
		    "WHERE p.boardId = :boardId " +
		    "AND p.delYn = :delYn")
	List<Post> selectPostByBoardId(@Param("boardId") Long boardId, @Param("delYn") String delYn);
	
}

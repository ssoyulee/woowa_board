package com.woowa.board.comment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface CommentRepository extends JpaRepository<Comment, Long>{

	List<Comment> findAllByDelYn(String delYn); 
	
	@Query("SELECT c from comment c " + 
		    "WHERE c.postId = :postId " +
		    "AND c.delYn = :delYn")
	List<Comment> selectCommentByPostId(@Param("postId") Long postId, @Param("delYn") String delYn);
}

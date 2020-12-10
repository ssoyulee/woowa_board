package com.woowa.board.comment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

	List<Comment> findAllByDelYn(String delYn); 

	List<Comment> findAllByPostIdAndDelYnOrderByCommentIdDesc(Long postId, String delYn);
}

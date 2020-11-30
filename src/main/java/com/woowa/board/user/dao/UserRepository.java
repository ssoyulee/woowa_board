package com.woowa.board.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.woowa.board.comment.dao.Comment;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}

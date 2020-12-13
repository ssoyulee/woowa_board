package com.woowa.board.board.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{

	List<Board> findAllByOrderByBoardIdDesc();
	
	List<Board> findAllByDelYn(String delYn);
	
	Page<Board> findAllByDelYn(String delYn, Pageable pageable); 
}

package com.woowa.board.user.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, String>{

	public Optional<UserAccount> findByUserIdAndPassword(String userId, String password);
	
}

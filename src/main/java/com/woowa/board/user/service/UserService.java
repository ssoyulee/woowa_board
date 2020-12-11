package com.woowa.board.user.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woowa.board.user.dao.UserAccount;
import com.woowa.board.user.dao.UserRepository;
import com.woowa.board.user.vo.UserRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<UserAccount> select() throws Exception {
		
		return userRepository.findAll();
		
	}
	
	public Optional<UserAccount> getUserById(String userId) throws Exception {
		
		Optional<UserAccount> user = userRepository.findById(userId);
		return user;
	}
	
	public Optional<UserAccount> getUserByUserIdAndPassword(String userId, String password) throws Exception {
		
		Optional<UserAccount> user = userRepository.findByUserIdAndPassword(userId,password);
		return user;
	}
	
	@Transactional
	public void insert(UserRequest userRequest) throws Exception {
		
		UserAccount user= UserAccount.builder()
						.userId(userRequest.getUserId())		
						.password(userRequest.getPassword())
						.name(userRequest.getName())
						.email(userRequest.getEmail())
						.role(userRequest.getRole())
						.delYn("N")
						.regpeId(userRequest.getWriteId())
						.modpeId(userRequest.getWriteId())
						.build();

		userRepository.save(user);

		log.info("insert ::: user insert success ::: user = > {}", user);
				
	}
	
	@Transactional
	public void update(String userId, UserRequest userRequest) throws Exception {
		
		UserAccount user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. userId=" + userId));
		
		user.setPassword(userRequest.getPassword());
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		user.setRole(userRequest.getRole());
		user.setModpeId(userRequest.getWriteId());
		
		userRepository.save(user);
		
		log.info("update ::: user update success ::: user = > {}", user);
		
	}
	
	@Transactional
	public void updateSession(String userId, String sessionId) throws Exception {
		
		UserAccount user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. userId=" + userId));
		
		user.setSessionId(sessionId);
		userRepository.save(user);
		
		log.info("updateSession ::: user update success ::: user = > {}", user);
		
	}
	
	@Transactional
	public void delete(String userId) throws Exception {
		
		UserAccount user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. userId=" + userId));
		
		user.setDelYn("Y");
		
		userRepository.save(user);

		log.info("delete ::: user delete success ::: userId = > {}", userId);
		
	}
}

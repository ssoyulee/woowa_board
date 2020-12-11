package com.woowa.board.user.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woowa.board.user.dao.User;
import com.woowa.board.user.dao.UserRepository;
import com.woowa.board.user.vo.UserRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> select() throws Exception {
		
		return userRepository.findAll();
		
	}
	
	public Optional<User> getUserById(String userId) throws Exception {
		
		Optional<User> user = userRepository.findById(userId);
		return user;
	}
	
	public Optional<User> getUserByUserIdAndPassword(String userId, String password) throws Exception {
		
		Optional<User> user = userRepository.findByUserIdAndPassword(userId,password);
		return user;
	}
	
	@Transactional
	public void insert(UserRequest userRequest) throws Exception {
		
		User user= User.builder()
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

		log.info("success insertUser => " + user);
				
	}
	
	@Transactional
	public void update(String userId, UserRequest userRequest) throws Exception {
		
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. userId=" + userId));
		
		user.setPassword(userRequest.getPassword());
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		user.setRole(userRequest.getRole());
		user.setModpeId(userRequest.getWriteId());
		
		userRepository.save(user);
		
		log.info("success updateUser => " + user);
		
	}
	
	@Transactional
	public void updateSession(String userId, String sessionId) throws Exception {
		
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. userId=" + userId));
		
		user.setSessionId(sessionId);
		userRepository.save(user);
		
		log.info("success updateUser => " + user);
		
	}
	
	@Transactional
	public void delete(String userId) throws Exception {
		
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. userId=" + userId));
		
		user.setDelYn("Y");
		
		userRepository.save(user);

		log.info("success deleteUser => " + userId);
		
	}
}

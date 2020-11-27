package com.woowa.board.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> select() throws Exception {
		
		List<User> listUser = userRepository.findAll();
		
		for ( User user : listUser ) {
			System.out.println(user);
		}
		return listUser;
	}
	
	public String selectUserId(String userId) throws Exception {
		
		return userId;
	}
}

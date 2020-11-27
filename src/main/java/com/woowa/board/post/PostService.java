package com.woowa.board.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public List<Post> select() throws Exception {
		
		List<Post> listPost = postRepository.findAll();
		for ( Post post : listPost ) {
			System.out.println(post.toString());
		}
		
		return listPost;
	}
	
}

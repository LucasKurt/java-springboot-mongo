package com.lucaslearning.Workshop_Mongo.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucaslearning.Workshop_Mongo.entities.Post;
import com.lucaslearning.Workshop_Mongo.entities.User;
import com.lucaslearning.Workshop_Mongo.repositories.PostRepository;
import com.lucaslearning.Workshop_Mongo.repositories.UserRepository;
import com.lucaslearning.Workshop_Mongo.services.exception.ResourceNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Post findById(String id) {
		Optional<Post> post = postRepository.findById(id);
		return post.orElseThrow(() -> new ResourceNotFoundException("Post id: " + id + " not found"));
	}
	
	public Post insertPost(Post post) {
		Optional<User> user = userRepository.findById(post.getAuthor().getId());
		user.orElseThrow(() -> new ResourceNotFoundException("Author id: " + user.get().getId() + " not found"));
		Post resultPost = postRepository.insert(post);
		user.get().getPosts().add(post);
		userRepository.save(user.get());
		return resultPost;
	}
	
	public void delete(String id) {
		findById(id);
		postRepository.deleteById(id);
	}
	
	public Post update(Post post, String id) {
		Post bdPost = findById(id);
		updatePost(bdPost, post);
		return postRepository.save(bdPost);
	}
	
	private void updatePost(Post bdPost, Post post) {
		bdPost.setBody(post.getBody());
		bdPost.setTitle(post.getTitle());
		bdPost.setDate(new Date(System.currentTimeMillis()));
	}
}

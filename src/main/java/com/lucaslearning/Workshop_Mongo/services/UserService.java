package com.lucaslearning.Workshop_Mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucaslearning.Workshop_Mongo.dto.UserDTO;
import com.lucaslearning.Workshop_Mongo.entities.User;
import com.lucaslearning.Workshop_Mongo.repositories.UserRepository;
import com.lucaslearning.Workshop_Mongo.services.exception.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ResourceNotFoundException("User id: " + id + " not found"));
	}
	
	public User insertUser(User user) {
		return userRepository.insert(user);
	}
	
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User update(User user, String id) {
		User bdUser = findById(id);
		updateUser(bdUser, user);
		return userRepository.save(bdUser);
	}
	
	private void updateUser(User bdUser, User user) {
		bdUser.setEmail(user.getEmail());
		bdUser.setName(user.getName());
	}
	
	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail()); 
	}
}

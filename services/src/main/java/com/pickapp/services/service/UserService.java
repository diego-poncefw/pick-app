package com.pickapp.services.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pickapp.services.model.User;
import com.pickapp.services.persistence.UserRepository;

@Service
public class UserService {

	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void saveNewUser(User user) {
		userRepository.save(user);
	}

	
}

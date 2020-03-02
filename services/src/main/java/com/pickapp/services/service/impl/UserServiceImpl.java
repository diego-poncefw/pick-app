package com.pickapp.services.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pickapp.services.model.User;
import com.pickapp.services.persistence.UserRepository;
import com.pickapp.services.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void saveNewUser(User user) {
		userRepository.save(user);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findByCode(BigDecimal code) {
		return userRepository.findByCode(code);
	}

}

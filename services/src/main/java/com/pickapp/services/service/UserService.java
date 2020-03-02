package com.pickapp.services.service;

import java.math.BigDecimal;
import java.util.List;

import com.pickapp.services.model.User;

public interface UserService {

	void saveNewUser(User user);

	User findByEmail(String email);

	List<User> findByCode(BigDecimal code);

}

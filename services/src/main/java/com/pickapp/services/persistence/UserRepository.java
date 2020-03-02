package com.pickapp.services.persistence;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.pickapp.services.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmail(@Param("email") String email);
	
	public List<User> findByCode(@Param("code") BigDecimal code);

}

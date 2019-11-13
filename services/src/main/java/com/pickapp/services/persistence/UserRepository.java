package com.pickapp.services.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pickapp.services.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}

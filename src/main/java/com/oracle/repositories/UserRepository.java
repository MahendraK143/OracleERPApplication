package com.oracle.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oracle.model.User;


public interface UserRepository extends JpaRepository<User, Integer>{

	List<User> findByUsername(String username);

}

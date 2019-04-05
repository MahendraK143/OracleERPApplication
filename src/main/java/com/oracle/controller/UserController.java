package com.oracle.controller;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.model.User;
import com.oracle.repositories.UserRepository;

@RestController
public class UserController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	private UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@RequestMapping(value = "/validateUser", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean validateUser(@RequestParam("username")String username,@RequestParam("password")String password,Model model) {
		LOG.info("user:"+username);
		List<User> us = userRepository.findByUsername(username);
		LOG.info("US:"+us);
		if (us != null && us.size()==1 && us.get(0).getPassword().equals(password)) {
			return true;
		}
		return false;
	}

}

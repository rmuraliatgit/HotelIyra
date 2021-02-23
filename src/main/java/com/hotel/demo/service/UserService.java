package com.hotel.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hotel.demo.model.User;

public interface UserService {

	 User addUser(User user);
	
	 User login(User user);

	 User findUserById(int id);

	 User findByEmail(String email);

	 List<User> findAll();

	 User updateUser(User user);

	 User deleteUser(int id);
}

package com.hotel.demo.serviceimpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.hotel.demo.exception.UserAlreadyExistsException;
import com.hotel.demo.exception.UserNotFoundException;
import com.hotel.demo.exception.ValidationException;
import com.hotel.demo.model.Role;
import com.hotel.demo.model.User;
import com.hotel.demo.respository.UserRepository;
import com.hotel.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Override
	public User addUser(User user){
		final String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		String password = user.getPassword();
		String email = user.getEmail();
		final String emailPattern = "^(.+)@(.+)$";

		if (email.matches(emailPattern)) {
			if (password.matches(passwordPattern)) {
				User existingUser = userRepository.findByEmail(user.getEmail());
				if (existingUser != null) {
					logger.warn("User already Exists");
					throw new UserAlreadyExistsException("User Already Exists");
				}
				Role role = user.getRole();
				Role existingRole = userRepository.findRole(user.getRole().getRoleName());
				if (existingRole.getRoleName().equals(role.getRoleName())) {
					user.setRole(existingRole);
					userRepository.save(user);
				}
			} else {
				logger.warn("use correct password");
				throw new ValidationException("use correct password");
			}
		} else {
			logger.warn("use correct Email");
			throw new ValidationException("use correct Email");
		}
		return user;
	}
	
	@Override
	@Transactional
	public User login(@RequestBody User user){
		String email = user.getEmail();
		String password = user.getPassword();
		return userRepository.findByEmailAndPassword(email, password);
		
	}

	@Override
	public Optional<User> findUserById(int id){
		Optional<User> existingUser = userRepository.findById(id);
		if (!existingUser.isPresent()) {
			logger.warn("User Not Found");
			throw new UserNotFoundException("User Not Found");
		} else {
			return existingUser;
		}
	}

	@Override
	public User findByEmail(String email){
		User existingUser = userRepository.findByEmail(email);
		if (existingUser != null) {
			return existingUser;
		} else {
			logger.warn("User Not Found");
			throw new UserNotFoundException("User Not Found");
		}
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> deleteUser(int userId){
		Optional<User> existingUser = userRepository.findById(userId);
		if (!existingUser.isPresent()) {
			logger.warn("User does not exists!!");
			throw new UserNotFoundException("User does not exists!!");
		} else {
			userRepository.deleteById(userId);
			return existingUser;
		}
	}

	@Override
	@Transactional
	public User updateUser(@RequestBody User user){
		User updatedUser = userRepository.findByEmail(user.getEmail());
		
		if(Objects.nonNull(user.getPhoneNo())) {
			updatedUser.setPhoneNo(user.getPhoneNo());
		}
		if (user.getPassword() != null) {
			updatedUser.setPassword(user.getPassword());
		}
		
		return updatedUser;
	}

}

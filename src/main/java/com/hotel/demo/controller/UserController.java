package com.hotel.demo.controller;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hotel.demo.exception.UserNotFoundException;
import com.hotel.demo.model.User;
import com.hotel.demo.respository.UserRepository;
import com.hotel.demo.serviceimpl.UserServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired(required = true)
	private UserServiceImpl userService;

	@Autowired
	private UserRepository userRepository;

	private static Logger logger = LogManager.getLogger(UserController.class);

	/**
	 * This  method is used to Added the User
	 * @param user
	 * @returns string
	 */
	
	@PostMapping("/addusers")
	public ResponseEntity<Message> addUser(@RequestBody User user) {
		User users = userService.addUser(user);
		Message message = new Message();
		if (users != null) {
			message.setUser(users);
			message.setText("Registration Successfull");
			return new ResponseEntity<>(message, HttpStatus.CREATED);
		} else {
			message.setText("Registration Unsuccessful");
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * This method is used for users to Login
	 * @param userObj
	 * @returns string	 
	 */

	@PostMapping("/userlogin")
	public ResponseEntity<Message> login(@RequestBody User userObj){
		User user = userService.login(userObj);
		Message message = new Message();
		if (user != null) {
			message.setAuth(true);
			message.setText("Successfully Logged in!!");
			message.setUser(user);
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			message.setAuth(false);
			message.setText("Unsuccessfull Logged in!!");
			return new ResponseEntity<>(message, HttpStatus.OK);
		}
	}

	/**
	 * This method is used to get the User by Id   
	 * @param id
	 * @returns user	
	 */
	
	@GetMapping("/usersById/{id}")
	public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") int id){
		ResponseEntity<Optional<User>> response;
		logger.info("Recieved id on path: ");
		Optional<User> user = userService.findUserById(id);
		if (user.isPresent()) {
			response = new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			throw new UserNotFoundException("No user has been found");
		}
		return response;
	}

	/**
	 * This method is used to get the User by Email
	 * @param email
	 * @returns	user
	 */
	
	@GetMapping("/users/email")
	public ResponseEntity<User> getUserByEmail(@RequestParam("email") String email){
		User existingUser = userService.findByEmail(email);
		if (existingUser == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(existingUser, HttpStatus.OK);
	}

	/**
	 * This method is used to view All Users
	 * @return List of users
	 */
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.findAll();
		if (!users.isEmpty()) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * This method is used to Delete the User by Id
	 * @param id	 
	 */
	
	@DeleteMapping("/users/{id}")
	public Message deleteUser( @PathVariable("id") int id){
		userService.deleteUser(id);
		Message message = new Message("Successfully Deleted");
        List<User> users=userService.findAll();
        message.setUsers(users);
        return message;
	}

	/**
	 * This method is used to update the User
	 * @param userObj
	 * @returns	updated user
	 */
	
	@PutMapping("/user")
	public ResponseEntity<Message> updateUser( @RequestBody User userObj){
		User user = userService.updateUser(userObj);
		Message message = new Message();
		message.setText("User Updated");
		message.setUser(user);
		message.setUsers(userService.findAll());
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}	
}

class Message {

	String text;
	private List<User> users;
	boolean auth;
	User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Message() {
		super();
	}

	public Message(String text) {
		super();
		this.text = text;
	}

	public Message(String text, boolean auth) {
		super();
		this.text = text;
		this.auth = auth;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}
}

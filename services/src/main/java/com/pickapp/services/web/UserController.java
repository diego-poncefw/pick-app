package com.pickapp.services.web;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pickapp.services.model.User;
import com.pickapp.services.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public HttpEntity add(@RequestBody User user) {
		userService.saveNewUser(user);
		return ok(new String("se guardo"));
	}
	
	@GetMapping("/test")
	public HttpEntity<User> hello() {
		User user = new User();
		user.setName("diego");
		return ok(user);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/hello")
    public HttpEntity<String> home() {
        return ok(new String("Hello World!"));
    }
	
}

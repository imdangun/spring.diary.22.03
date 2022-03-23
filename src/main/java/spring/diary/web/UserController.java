package spring.diary.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.diary.domain.User;
import spring.diary.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired private UserService userService;

	@PostMapping("signin")
	public User signin(@RequestBody User user) {		
		return userService.authenticate(user.getEmail(), user.getPassword());
	}
	
	@PostMapping("signup")
	public void signup(@RequestBody User user) {	
		userService.register(user);
	}
}

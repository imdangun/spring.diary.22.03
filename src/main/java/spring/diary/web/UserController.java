package spring.diary.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("signin")
	public ResponseEntity<User> signin(@RequestBody User user) {
		ResponseEntity<User> response = null;
		user = userService.authenticate(user.getEmail(), user.getPassword());
		
		if(user != null) response = ResponseEntity.ok().body(user);
		else response = ResponseEntity.badRequest().body(user);
		
		return response;
	}
	
	@PostMapping("signup")
	public void signup(@RequestBody User user) {	
		userService.register(user);
	}
}

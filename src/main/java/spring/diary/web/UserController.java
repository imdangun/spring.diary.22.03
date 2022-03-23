package spring.diary.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import spring.diary.domain.User;
import spring.diary.domain.UserDto;
import spring.diary.service.UserService;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
	@Autowired private UserService userService;
	
	@PostMapping("signin")
	public boolean signin(@RequestBody UserDto userDto) {
		return userService.authenticate(userDto.getEmail(), userDto.getPassword());
	}
	
	@PostMapping("signup")
	public void signup(@RequestBody UserDto userDto) {
		User user = User.builder()
				.userName(userDto.getUserName())
				.email(userDto.getEmail())				
				.password(userDto.getPassword())
				.build();
		userService.register(user);
	}
}

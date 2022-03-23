package spring.diary.service;

import spring.diary.domain.User;

public interface UserService {	
	User authenticate(String email, String password);
	void register(User user);
}

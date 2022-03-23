package spring.diary.service;

import spring.diary.domain.User;

public interface UserService {	
	boolean authenticate(String email, String password);
	void register(User user);
}

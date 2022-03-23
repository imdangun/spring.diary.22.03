package spring.diary.dao;

import spring.diary.domain.User;

public interface UserDao {
	User selectUser(String email, String password);
	void insertUser(User user);
}

package spring.diary.dao;

import spring.diary.domain.User;

public interface UserDao {
	User selectUser(String email);
	void insertUser(User user);
}

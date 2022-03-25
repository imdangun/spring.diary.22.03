package spring.diary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.diary.dao.map.UserMap;
import spring.diary.domain.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired private UserMap userMap;
	
	@Override
	public User selectUser(String email) {
		return userMap.selectUser(email);
	}
	
	@Override
	public void insertUser(User user) {
		userMap.insertUser(user);
	}
}

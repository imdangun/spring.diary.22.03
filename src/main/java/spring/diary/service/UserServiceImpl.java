package spring.diary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.diary.dao.UserDao;
import spring.diary.domain.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired private UserDao userDao;	
	
	@Override
	public boolean authenticate(String email, String password) {
		return userDao.selectUser(email, password) != null;
	}
	
	@Override
	public void register(User user) {
		userDao.insertUser(user);
	}
}

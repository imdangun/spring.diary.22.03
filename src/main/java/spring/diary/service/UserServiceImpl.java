package spring.diary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.diary.dao.UserDao;
import spring.diary.domain.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired private UserDao userDao;	
	@Autowired private TokenProvider tokenProvider;	
	
	@Override
	public User authenticate(String email, String password) {				
		User user = userDao.selectUser(email, password);
		if(user != null) user.setToken(tokenProvider.createToken(user.getUserId()));
		return user;		
	}
	
	@Override
	public void register(User user) {
		userDao.insertUser(user);
	}
}

package spring.diary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import spring.diary.dao.UserDao;
import spring.diary.domain.User;
import spring.diary.security.TokenProvider;

@Service
public class UserServiceImpl implements UserService {
	@Autowired private UserDao userDao;	
	@Autowired private TokenProvider tokenProvider;
	@Autowired private PasswordEncoder passwordEncoder;	

	@Override
	public User authenticate(final String email, final String password) {
		User user = userDao.selectUser(email);
		if(user != null && passwordEncoder.matches(password, user.getPassword()))
			user.setToken(tokenProvider.getToken(user.getUserId()));
		else user = null;
		
		return user;
	}
	
	@Override
	public void register(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.insertUser(user);
	}
}


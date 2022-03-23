package spring.diary.dao.map;

import org.apache.ibatis.annotations.Param;

import spring.diary.domain.User;

public interface UserMap {	
	User selectUser(@Param("email") String email, 
					@Param("password") String password);
	void insertUser(User user);
}

package spring.diary.dao.map;

import org.apache.ibatis.annotations.Param;

import spring.diary.domain.User;

public interface UserMap {	
	User selectUser(@Param("email") String email);
	void insertUser(User user);
}

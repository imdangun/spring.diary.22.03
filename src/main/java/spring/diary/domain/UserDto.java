package spring.diary.domain;

import lombok.Getter;

@Getter
public class UserDto {
	private String userId;
	private String userName;
	private String email;
	private String password;
	private String token;
}

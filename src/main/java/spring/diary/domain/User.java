package spring.diary.domain;

import lombok.Builder;

@Builder
public class User {
	private String userId;
	private String userName;
	private String email;
	private String password;
}

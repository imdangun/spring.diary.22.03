package spring.diary.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
	private String userId;
	private String userName;
	private String email;
	private String password;
	private String token;
}
package spring.diary.domain;

import lombok.Getter;

@Getter
public class Todo {
	private String todoId;
	private String title;
	private boolean done;
	private String userId; 
}
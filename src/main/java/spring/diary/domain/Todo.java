package spring.diary.domain;

import lombok.Data;

@Data
public class Todo {
	private String todoId;
	private String title;
	private boolean done;
	private String userId; 
}
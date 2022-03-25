package spring.diary.domain;

import lombok.Data;

@Data
public class Todo {
	private String todoId;
	private String title;
	private boolean done;
	private String userId; 
	
	public String toString() {
		return String.format("%s %s %b %s", todoId, title, done, userId); 
	}
}
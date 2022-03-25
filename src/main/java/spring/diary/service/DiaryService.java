package spring.diary.service;

import java.util.List;

import spring.diary.domain.Todo;

public interface DiaryService {
	List<Todo> getTodos(String userId);
	Todo getTodo(int todoId);
	void addTodo(Todo todo);
	void fixTodo(Todo todo);
	void delTodo(int todoId);
}

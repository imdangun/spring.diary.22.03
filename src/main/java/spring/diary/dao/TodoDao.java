package spring.diary.dao;

import java.util.List;

import spring.diary.domain.Todo;

public interface TodoDao {
	List<Todo> selectTodos();
	Todo selectTodo(int todoId);
	void insertTodo(Todo todo);
	void updateTodo(Todo todo);
	void deleteTodo(int todoId);
}

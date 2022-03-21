package spring.diary.dao.map;

import java.util.List;
import spring.diary.domain.Todo;

public interface TodoMap {
	List<Todo> selectTodos();
	Todo selectTodo(int todoId);
	void insertTodo(Todo todo);
	void updateTodo(Todo todo);
	void deleteTodo(int todoId);
}

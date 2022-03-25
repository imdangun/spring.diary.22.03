package spring.diary.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.diary.dao.map.TodoMap;
import spring.diary.domain.Todo;

@Repository
public class TodoDaoImpl implements TodoDao {
	@Autowired private TodoMap todoMap;
	
	@Override
	public List<Todo> selectTodos(String userId) {
		return todoMap.selectTodos(userId);
	}
	
	@Override
	public Todo selectTodo(int todoId) {
		return todoMap.selectTodo(todoId);
	}
	
	@Override
	public void insertTodo(Todo todo) {
		todoMap.insertTodo(todo);
	}
	
	@Override
	public void updateTodo(Todo todo) {
		todoMap.updateTodo(todo);
	}
	
	@Override
	public void deleteTodo(int todoId) {
		todoMap.deleteTodo(todoId);
	}
}

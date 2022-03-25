package spring.diary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.diary.dao.TodoDao;
import spring.diary.domain.Todo;

@Service
public class DiaryServiceImpl implements DiaryService {
	@Autowired private TodoDao todoDao;
		
	@Override
	public List<Todo> getTodos(String userId) {
		return todoDao.selectTodos(userId);
	}
	
	@Override
	public Todo getTodo(int todoId) {
		return todoDao.selectTodo(todoId);
	}
	
	@Override
	public void addTodo(Todo todo) {
		todoDao.insertTodo(todo);
	}
	
	@Override
	public void fixTodo(Todo todo) {
		todoDao.updateTodo(todo);
	}
	
	@Override
	public void delTodo(int todoId) {
		todoDao.deleteTodo(todoId);
	}
}

package spring.diary.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import spring.diary.domain.Todo;
import spring.diary.service.DiaryService;

@RestController
@RequestMapping("diary") 
public class DiaryController {
	@Autowired private DiaryService diaryService;
	
	@GetMapping("todos")
	public List<Todo> getTodos(@AuthenticationPrincipal String userId) {
		return diaryService.getTodos(userId);
	}
	
	@GetMapping("todo")
	public Todo getTodo(@RequestParam int todoId) {
		return diaryService.getTodo(todoId);
	}
	
	@PostMapping("todo/add")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addTodo(@RequestBody Todo todo, @AuthenticationPrincipal String userId) {
		todo.setUserId(userId);
		diaryService.addTodo(todo);		
	}
	
	@PatchMapping("todo/fix")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fixTodo(@RequestBody Todo todo) {		
		diaryService.fixTodo(todo);
	}
	
	@DeleteMapping("todo/del/{todoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delTodo(@PathVariable int todoId) {
		diaryService.delTodo(todoId);
	}	
}

/*
@ResponseStatus(HttpStatus.NO_CONTENT) 에 대응해서,

client 에선,
if(response.ok && response.status != 204) {                 
            return response.json()
로 처리하면 된다.
*/

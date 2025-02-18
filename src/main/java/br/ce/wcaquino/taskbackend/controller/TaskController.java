package br.ce.wcaquino.taskbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.DateUtils;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

@RestController
@RequestMapping(value ="/todo")
public class TaskController {

	@Autowired
	private TaskRepo todoRepo;
	public static final String VALIDATION_EXCEPTION_TO_DESCRIPTION_EMPTY = "Fill the task description";
	public static final String VALIDATION_EXCEPTION_TO_DUE_DATE_FILL = "Fill the due date";
	public static final String VALIDATION_EXCEPTION_TO_DUE_DATE_NOT_BE_IN_PAST = "Due date must not be in past";

	@GetMapping
	public List<Task> findAll() {
		return todoRepo.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Task> save(@RequestBody Task todo) throws ValidationException {
		if(todo.getTask() == null || todo.getTask() == "") {
			throw new ValidationException(VALIDATION_EXCEPTION_TO_DESCRIPTION_EMPTY);
		}
		if(todo.getDueDate() == null) {
			throw new ValidationException(VALIDATION_EXCEPTION_TO_DUE_DATE_FILL);
		}
		if(!DateUtils.isEqualOrFutureDate(todo.getDueDate())) {
			throw new ValidationException(VALIDATION_EXCEPTION_TO_DUE_DATE_NOT_BE_IN_PAST);
		}
		Task saved = todoRepo.save(todo);
		return new ResponseEntity<Task>(saved, HttpStatus.CREATED);
	}
}

package br.com.mailtodo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mailtodo.dto.TaskForm;
import br.com.mailtodo.model.Task;
import br.com.mailtodo.model.User;
import br.com.mailtodo.repository.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;

	@GetMapping
	public List<Task> index() {
		return taskRepository.findByOwner(getLoggedUser());
	}

	@GetMapping("{id}")
	public ResponseEntity<Task> show(@PathVariable("id") Integer id) {
		Optional<Task> oTask = taskRepository.findById(id);
		if (oTask.isPresent()) {
			Task task = oTask.get();

			if (!isLoggedUserOwnerOf(task))
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

			return ResponseEntity.ok(task);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Task> create(@RequestBody TaskForm form, UriComponentsBuilder uriBuilder) {
		Task task = new Task();
		form.transferDataTo(task);
		task.setOwner(getLoggedUser());
		taskRepository.save(task);
		URI uri = uriBuilder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri();
		return ResponseEntity.created(uri).body(task);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Task> update(@PathVariable("id") Integer id, @RequestBody TaskForm form) {
		Optional<Task> oTask = taskRepository.findById(id);
		if (oTask.isPresent()) {
			Task task = oTask.get();

			if (!isLoggedUserOwnerOf(task))
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

			form.transferDataTo(task);
			taskRepository.save(task);
			return ResponseEntity.ok(task);
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}/{done}")
	@Transactional
	public ResponseEntity<Task> setDone(@PathVariable("id") Integer id, @PathVariable("done") boolean done) {
		Optional<Task> oTask = taskRepository.findById(id);
		if (oTask.isPresent()) {
			Task task = oTask.get();

			if (!isLoggedUserOwnerOf(task))
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

			task.setDone(done);
			return ResponseEntity.ok(task);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Task> delete(@PathVariable("id") Integer id) {
		Optional<Task> oTask = taskRepository.findById(id);

		if (oTask.isPresent()) {
			Task task = oTask.get();

			if (!isLoggedUserOwnerOf(task))
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

			taskRepository.delete(task);
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}

	private boolean isLoggedUserOwnerOf(Task task) {
		return task.getOwner().equals(getLoggedUser());
	}

	private User getLoggedUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}

package br.com.mailtodo.controller;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mailtodo.dto.UserDTO;
import br.com.mailtodo.dto.UserForm;
import br.com.mailtodo.model.Priority;
import br.com.mailtodo.model.Task;
import br.com.mailtodo.model.User;
import br.com.mailtodo.repository.UserRepository;

@RestController
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<UserDTO> register(@RequestBody UserForm form) {
		if (userRepository.findByEmail(form.getEmail()).isPresent()) {
			return ResponseEntity.badRequest().build();
		}

		System.out.println(form.getName());
		User user = form.converter();
		User savedUser = userRepository.save(user);
		attachWelcomeTasks(savedUser);

		return ResponseEntity.created(null).body(new UserDTO(user));
	}

	private void attachWelcomeTasks(User user) {
		Task task = new Task("Welcome, " + user.getName() + "!",
				"MailToDo++ is here to make sure you'll do everything you have to. We will send emails periodically if there are High Priority Tasks you haven't done yet!",
				Priority.HIGH, user);
		Task task2 = new Task("Sample Task",
				"Clicking in options you'll be able to update or delete the Task. Once it's done you can click 'Done!' and off it goes!",
				Priority.LOW, user);

		user.setTasks(Arrays.asList(task, task2));
	}

}

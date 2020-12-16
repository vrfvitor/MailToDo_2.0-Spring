package br.com.mailtodo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mailtodo.model.Priority;
import br.com.mailtodo.model.Task;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@GetMapping
	public List<Task> index() {
		ArrayList<Task> list = new ArrayList<Task>();

		list.add(new Task("Sopa", "Macaco", Priority.HIGH));
		list.add(new Task("Feijão", "Arroz", Priority.LOW));

		return list;
	}

}

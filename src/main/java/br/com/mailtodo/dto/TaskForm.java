package br.com.mailtodo.dto;

import br.com.mailtodo.model.Priority;
import br.com.mailtodo.model.Task;

public class TaskForm {

	private String title;
	private String description;
	private Priority priority;

	public void transferDataTo(Task task) {
		task.setTitle(this.title);
		task.setDescription(this.description);
		task.setPriority(this.priority);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

}

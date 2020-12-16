package br.com.mailtodo.model;

public class Task {

	private String title;
	private String description;
	private Priority priority;

	public Task(String title, String description, Priority priority) {
		this.title = title;
		this.description = description;
		this.priority = priority;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

}

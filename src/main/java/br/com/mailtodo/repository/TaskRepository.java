package br.com.mailtodo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mailtodo.model.Task;
import br.com.mailtodo.model.User;

public interface TaskRepository extends JpaRepository<Task, Integer> {
	
	List<Task> findByOwner(User user);
	
}

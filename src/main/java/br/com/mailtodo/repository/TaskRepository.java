package br.com.mailtodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mailtodo.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}

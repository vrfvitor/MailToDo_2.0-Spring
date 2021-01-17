package br.com.mailtodo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mailtodo.model.Priority;
import br.com.mailtodo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	@Query("FROM tb_user u JOIN FETCH u.tasks t WHERE t.owner.id = u.id AND t.done = :done AND t.priority = :priority")
	List<User> findByHasTaskWithStatus(@Param("done") boolean done, @Param("priority") Priority priority);

}

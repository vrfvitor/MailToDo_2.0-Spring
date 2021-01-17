package br.com.mailtodo.mailing;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.mailtodo.model.Priority;
import br.com.mailtodo.model.Task;
import br.com.mailtodo.model.User;
import br.com.mailtodo.repository.UserRepository;

@Component
public class TaskEmailNotifier {

	private static final Logger log = LoggerFactory.getLogger(TaskEmailNotifier.class);
	private static final String HIGH_TASK_UNDONE = "You Have High Priority Tasks Undone";

	@Autowired
	private EmailSender emailSender;

	@Autowired
	private HtmlMailContentBuilder htmlMailContentBuilder;

	@Autowired
	private UserRepository userRepository;

	@Scheduled(cron = "0 0/2 * * * *")
	private void notifyHighPriorityTasksUndone() {
		List<User> usersWithHighPriorityTasksUndone = userRepository.findByHasTaskWithStatus(false, Priority.HIGH);

		usersWithHighPriorityTasksUndone.stream().forEach(user -> {
			List<Task> tasks = user.getTasks().stream().filter(t -> t.getPriority() == Priority.HIGH)
					.filter(t -> t.isDone() == false).collect(Collectors.toList());

			String taskEmailContent = htmlMailContentBuilder.buildTaskEmailContent(tasks);
			emailSender.sendMessage(user.getEmail(), HIGH_TASK_UNDONE, taskEmailContent, true);
		});

		log.info("Scheduled job notifyHighPriorityTasksUndone() has finished successfully");
	}

}

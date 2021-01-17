package br.com.mailtodo.mailing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.mailtodo.model.Task;
import br.com.mailtodo.util.FileUtil;

/**
 * Provides the HTML content that is going to be the body of the specified email
 * type
 * 
 * @author vrfvitor
 *
 */
@Service
public class HtmlMailContentBuilder {

	@Value("${app.spa.url}")
	private String appUrl;

	@Autowired
	private FileUtil fileUtil;

	public String buildTaskEmailContent(List<Task> tasks) {
		String emailTemplate = fileUtil.fetchResourceAsString("assets/email-template.html");
		String emailWithLinkToApp = emailTemplate.replace("link-to-app", appUrl);
		String taskHtmlElements = getTasksAsHtmlElements(tasks);
		return emailWithLinkToApp.replace("replace-with-tasks", taskHtmlElements);
	}

	private String getTasksAsHtmlElements(List<Task> tasks) {
		String taskHtmlTemplate = fileUtil.fetchResourceAsString("assets/task-element.html");
		StringBuilder htmlContent = new StringBuilder();

		tasks.stream().forEach(task -> {
			String taskElementWithTitle = taskHtmlTemplate.replace("fill-task-title", task.getTitle());
			String taskElementFilledOut = taskElementWithTitle.replace("fill-task-description", task.getDescription());
			htmlContent.append(taskElementFilledOut);
		});

		return htmlContent.toString();
	}

}

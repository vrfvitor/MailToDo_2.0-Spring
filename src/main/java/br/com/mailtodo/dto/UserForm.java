package br.com.mailtodo.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.mailtodo.model.User;

public class UserForm {

	private String name;
	private String email;
	private String password;

	public User converter() {
		User user = new User();
		user.setEmail(this.email);
		user.setName(this.name);
		user.setPassword(new BCryptPasswordEncoder().encode(this.password));
		return user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

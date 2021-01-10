package br.com.mailtodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mailtodo.dto.UserDTO;
import br.com.mailtodo.dto.UserForm;
import br.com.mailtodo.model.User;
import br.com.mailtodo.repository.UserRepository;

@RestController
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping
	public ResponseEntity<UserDTO> register(@RequestBody UserForm form) {
		if (userRepository.findByEmail(form.getEmail()).isPresent()) {
			return ResponseEntity.badRequest().build();
		}

		User user = form.converter();
		userRepository.save(user);
		return ResponseEntity.created(null).body(new UserDTO(user));
	}

}
